import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MainScene extends JPanel {

    private ArrayList<Character> pigs;
    private ArrayList<Character> birds;
    public static Terrein terrein;
    private GameActionListener gameActionListener;
    private Image backroundMainGame;
    private JButton exit;
    public MainScene (int x, int y, int width, int height) throws IOException {
        setBounds(x, y, width, height);
        this.setLayout(null);
        this.exit =new JButton(Constans.EXIT_BUTTON_TEXT);
        exit.setBounds(Constans.EXIT_BUTTON_X
                ,Constans.EXIT_BUTTON_Y
                ,Constans.EXIT_BUTTON_WIDTH
                ,Constans.EXIT_BUTTON_HIGHT);
        exit.setFont(new Font("Tahoma", Font.PLAIN, 20));
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.setFocusable(false);
        this.add(exit);
        terrein = new Terrein();
        terrein.randomTerreain();
        terrein.slingshot();
        terrein.restBench();
        terrein.bottom();
        this.backroundMainGame = new ImageIcon(Objects.requireNonNull(getClass().getResource(Constans.MAIN_SCENE_BACK_IMG))).getImage();
        this.addPigsOnPillers();
        this.addBirds();

        this.gameActionListener = new GameActionListener();
        gameActionListener.setBirdsList(this.birds);
        this.addMouseListener(gameActionListener);
        this.addMouseMotionListener(gameActionListener); // for drag
        this.addKeyListener(gameActionListener);
        this.mainGameLoop();


    }
    public JButton getExitButton(){
        return this.exit;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backroundMainGame,0,0,Constans.WINDOW_WIDTH,Constans.WINDOW_HIGHT,this);

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
        if(gameActionListener.getCurrBird()!=null) {
            if(gameActionListener.getCurrBird().getX()<=Constans.PUT_BIRD_ON_SLIG_X) {
                terrein.paintSlingshotString(g, gameActionListener.getCurrBird().getX(), gameActionListener.getCurrBird().getY());
            }
        }
    }



    void mainGameLoop() {

            new Thread(() -> {
                try {
                    Character birdToRemove=null;
                    //Character pigToRemove=null;
                    ArrayList<Character> pigsToRemove= new ArrayList<>();
                    while (true) {
                        for (Character c : this.pigs  )  {

                            for( Character birdCol : this.birds) {
                                if(c.getCharacterAsRectangle().intersects(birdCol.getCharacterAsRectangle())
                                    || c.collisionWithBottom()
                                ){
                                    //pigToRemove=c;
                                    pigsToRemove.add(c);
                                    //break;
                                }
                            }


                                c.gravity();
                        }
                        for( Character b : this.birds){
                            b.gravity();
                            b.moveUp(b.getMoveUpIncremet());
                            b.moveRight(b.getMoveRightIncremet());
                            if(b.collisionWithBottom() || b.collisionWithPiller() || !b.withinBounds()){
                                birdToRemove=b; break;
                            }
                        }
                        this.gameActionListener.removeOneBird(birdToRemove);
                        this.birds.remove(birdToRemove);
                        for(Character p : pigsToRemove) {
                            this.pigs.remove(p);
                        }
                        // this.pigs.remove(pigToRemove);
                        birdToRemove=null;pigsToRemove.clear(); // pigToRemove=null;

                        if(this.birds.size()==0){
                            restokBirds();

                        }
                        if(this.pigs.size()==0){
                            birdToRemove=gameActionListener.getCurrBird();
                            this.gameActionListener.removeOneBird(birdToRemove);
                            this.birds.remove(birdToRemove);
                            birdToRemove=null;
                            refreshPillersAndPigs();
                            //TODO add points for level cpmplete
                        }


                        Thread.sleep(20);
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
                    (int) (i*Constans.HIGHT_CHARACTER *1.2 ),// BUFFER
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
