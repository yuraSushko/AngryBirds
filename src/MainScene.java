import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class MainScene extends JPanel {

    private ArrayList<Character> pigs;
    private ArrayList<Character> birds;
    public static Terrein terrein;
    private GameActionListener gameActionListener;
    private Image backroundMainGame;
    private JButton exit;
    private JButton lost;
    private JLabel scoreLabel;
    private int score;
    private Music collisionSound;
    private Music strechSlingShotSound;
    public static boolean clikedRestart;


    public MainScene (int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        this.setLayout(null);
        createExitButton();
        initializeTerrein();
        this.backroundMainGame = new ImageIcon(Objects.requireNonNull(getClass().getResource(Constans.MAIN_SCENE_BACK_IMG))).getImage();
        addListner();
        createScoreLabel();
        this.collisionSound=new Music(Constans.COLLISION_SOUND_PATH);
        this.strechSlingShotSound=new Music(Constans.STERCH_SLING_SOUND_PATH);
        this.mainGameLoop();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backroundMainGame,0,0,Constans.WINDOW_WIDTH,Constans.WINDOW_HIGHT,this);
        try {
        for(Character c : this.pigs) {
            c.drawCharacter(g );
        }
        terrein.printPiller(g);
        terrein.printSlingshot(g);
        for(Character bird : this.birds) {
            bird.drawCharacter(g);
        }
        terrein.printRestBench(g);
        terrein.printBottom(g);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(gameActionListener.getCurrBird()!=null) {
            if(gameActionListener.getCurrBird().getX()<=Constans.PUT_BIRD_ON_SLIG_X) {
                terrein.paintSlingshotString(g, gameActionListener.getCurrBird().getX(), gameActionListener.getCurrBird().getY());
                if(gameActionListener.getCurrBird().getX()< Constans.PUT_BIRD_ON_SLIG_X){
                    strechSlingShotSound.play();
                }
            }
        }
    }


    private void addListner(){
        this.gameActionListener = new GameActionListener();
        gameActionListener.setBirdsList(this.birds);
        this.addMouseListener(gameActionListener);
        this.addMouseMotionListener(gameActionListener); // for drag
        this.addKeyListener(gameActionListener);

    }
    public void createScoreLabel(){
        this.scoreLabel= new JLabel(Constans.SCORE_LABEL_TEXT );
        this.scoreLabel.setVisible(true);
        this.scoreLabel.setBounds(Constans.SCORE_LABEL_X, Constans.SCORE_LABEL_Y, Constans.SCORE_LABEL_WIDTH, Constans.SCORE_LABEL_HIGHT);
        this.scoreLabel.setFont(new Font(Constans.SCORE_LABEL_FONT_NAME,Constans.SCORE_LABEL_FONT, Constans.SCORE_LABEL_FONT_SIZE));
        this.scoreLabel.setForeground(Constans.SCORE_LABEL_COLOR);
        this.add(scoreLabel);
        this.add(this.scoreLabel);
    }
    private void updateScore(int amount) {
        this.score+=amount;
        this.scoreLabel.setText("SCORE: " + this.score);
        this.scoreLabel.repaint();
    }

    private void createLostButton(){
        this.lost =new JButton(Constans.LOST_BUTTON_TEXT);
        lost.setBounds(Constans.LOST_BUTTON_X
                ,Constans.LOST_BUTTON_Y
                ,Constans.LOST_BUTTON_WIDTH
                ,Constans.LOST_BUTTON_HIGHT);
        lost.setFont(new Font(Constans.LOST_BUTTON_TEXT, Constans.EXIT_BUTTON_FONT, Constans.EXIT_BUTTON_FONT_SIZE));
        lost.setContentAreaFilled(false);
        lost.setBorderPainted(false);
        //lost.setFocusable(false);
        this.lost.addActionListener(gameActionListener);
        this.add(this.lost);

    }


    private void initializeTerrein(){
        terrein = new Terrein();
        terrein.randomTerreain();
        terrein.slingshot();
        terrein.restBench();
        terrein.bottom();
        this.addPigsOnPillers();
        this.addBirds();
    }
    private void createExitButton(){
        this.exit =new JButton(Constans.EXIT_BUTTON_TEXT);
        exit.setBounds(Constans.EXIT_BUTTON_X
                ,Constans.EXIT_BUTTON_Y
                ,Constans.EXIT_BUTTON_WIDTH
                ,Constans.EXIT_BUTTON_HIGHT);
        exit.setFont(new Font(Constans.EXIT_BUTTON_TEXT_FONT_NAME, Constans.EXIT_BUTTON_FONT, Constans.EXIT_BUTTON_FONT_SIZE));
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.setFocusable(false);
        this.add(this.exit);
    }

    public JButton getExitButton(){
        return this.exit;
    }

    private void restartGame(){
        this.score=0;
        this.scoreLabel.setText("SCORE : "+ this.score);
        this.gameActionListener.removeOneBird(this.gameActionListener.getCurrBird());
        this.birds.clear();
        restokBirds();
        this.pigs.clear();
        refreshPillersAndPigs();
    }


    void mainGameLoop() {

            new Thread(() -> {
                try {
                    Character birdToRemove=null;
                    ArrayList<Character> pigsToRemove= new ArrayList<>();
                    while (true) {
                        if (Window.latestClickedButton.equals(Constans.EXIT_BUTTON_TEXT)){
                            restartGame();

                        }

                        for (Character pig : this.pigs  )  {
                            for( Character bird : this.birds) {
                                boolean shotDown = pig.getCharacterAsRectangle().intersects(bird.getCharacterAsRectangle());
                                if(shotDown ){
                                    collisionSound.play();
                                    pigsToRemove.add(pig);
                                    updateScore(10);
                                }
                            }

                            boolean fell = pig.collisionWithBottom();
                            if(fell){
                                collisionSound.play();
                                pigsToRemove.add(pig);
                                updateScore(5);
                            }

                            pig.gravity();
                        }

                        for( Character b : this.birds){
                            b.gravity();
                            b.moveUp(b.getMoveUpIncremet());
                            b.moveRight(b.getMoveRightIncremet());
                            if(b.collisionWithBottom() || b.collisionWithPiller() || !b.withinBounds()){
                                birdToRemove=b; collisionSound.play();  break;
                            }
                        }
                        this.gameActionListener.removeOneBird(birdToRemove);
                        this.birds.remove(birdToRemove);
                        for(Character p : pigsToRemove) {
                            this.pigs.remove(p);
                        }
                        birdToRemove=null;pigsToRemove.clear();
                        if(this.birds.size()==0){
                            updateScore(-20);
                            restokBirds();
                        }
                        if(this.pigs.size()==0){
                            birdToRemove=gameActionListener.getCurrBird();
                            this.gameActionListener.removeOneBird(birdToRemove);
                            this.birds.remove(birdToRemove);
                            birdToRemove=null;
                            refreshPillersAndPigs();
                        }

                        if(this.score<0){
                            createLostButton();
                            this.birds.clear();
                            this.terrein.deleteAllPillers();
                            this.pigs.clear();
                            while(!clikedRestart){
                                Thread.sleep(Constans.MAIN_LOOP_SLEEP);
                                if(Window.latestClickedButton.equals(Constans.EXIT_BUTTON_TEXT)){break;}
                            }
                            if(clikedRestart || Window.latestClickedButton.equals(Constans.EXIT_BUTTON_TEXT) ) {
                                restartGame();
                                lost.setVisible(false);
                                clikedRestart = false;
                            }
                        }

                        Thread.sleep(Constans.MAIN_LOOP_SLEEP);
                    }
                } catch (Exception e) {throw new RuntimeException(e);}
            }).start();
    }



    public void refreshPillersAndPigs(){
        this.terrein.deleteAllPillers();
        this.terrein.randomTerreain();
        this.addPigsOnPillers();
    }

    public void restokBirds(){
        this.addBirds();
        gameActionListener.setBirdsList(this.birds);
    }


    public void addBirds(){
        this.birds = new ArrayList<>();
        for (int i = 0; i <Constans.NUMBER_OF_BIRDS; i++) {
            birds.add(new Character(Constans.WIDTH_CHARACTER,
                    Constans.HIGHT_CHARACTER,
                    (int) (i*Constans.HIGHT_CHARACTER *1.1 ),// BUFFER
                                0,Constans.BIRD_REGULAR_IMAGE_PATH));

        }
    }
    public void addPigsOnPillers() {
        this.pigs = new ArrayList<>();
        for (int i = 0; i < terrein.getPillers().size(); i++) {
            Rectangle curr = terrein.getPillers().get(i);
            pigs.add(new Character (Constans.WIDTH_CHARACTER,Constans.HIGHT_CHARACTER,
                            curr.x + (curr.width/2)- Constans.WIDTH_CHARACTER/2,
                               curr.y -(Constans.HIGHT_CHARACTER),
                            Constans.PIG_IMAGE_PATH)
                    );
        }
    }
}
