import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class OpeningPanle extends JPanel {

    private Image backroundOpenening;
    private JButton playB;
    private JButton instructionsB;

    //private Music backgroundMusic;
    Font f;

    public OpeningPanle(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setBackground(Color.BLACK);
        this.backroundOpenening = new ImageIcon(getClass().getResource(Constans.BACKROUND_OPENING_IMAGE_PATH)).getImage();
        this.setLayout(null);
        createPlayButton();
        createInstrunctionButton();
        playMusic();
    }


    private void createInstrunctionButton(){
        this.instructionsB =new JButton(Constans.INSTRUCTIONS_BUTTON_TEXT);
        instructionsB.setBounds(Constans.OPENINIG_SCREEN_INSTRUCTIONS_BUTTON_X
                ,Constans.OPENINIG_SCREEN_INSTRUCTIONS_BUTTON_Y
                ,Constans.OPENINIG_SCREEN_BOTH_BUTTON_WIDTH
                ,Constans.OPENINIG_SCREEN_BOTH_BUTTON_HIGHT);
        instructionsB.setFocusable(false);
        instructionsB.setBorderPainted(false);
        instructionsB.setContentAreaFilled(false);
        instructionsB.setFont(new Font(Constans.INSTRUCTIONS_BUTTON_FONT, Font.ROMAN_BASELINE,Constans.INSTRUCTIONS_BUTTON_FONT_SIZE));
        this.add(instructionsB);
    }

    private void createPlayButton(){
        this.playB =new JButton(Constans.PLAY_BUTTON_TEXT);
        playB.setBounds(Constans.OPENINIG_SCREEN_PLAY_BUTTON_X
                ,Constans.OPENINIG_SCREEN_PLAY_BUTTON_Y
                ,Constans.OPENINIG_SCREEN_BOTH_BUTTON_WIDTH
                ,Constans.OPENINIG_SCREEN_BOTH_BUTTON_HIGHT);
        playB.setForeground(Color.darkGray);
        playB.setFont(new Font(Constans.PLAY_BUTTON_TEXT_FONT, Font.PLAIN, Constans.PLAY_BUTTON_FONT_SIZE));
        playB.setContentAreaFilled(false);
        playB.setBorderPainted(false);
        playB.setFocusable(false);
        this.add(playB);
    }

    public void playMusic() {
        Music backGround = new Music(Constans.BACKGROUND_MUSIC_PATH);
        backGround.loop();
    }

    public JButton getPlayB() {
        return playB;
    }

    public JButton getInstructionsB() {
        return instructionsB;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backroundOpenening,0,0,Constans.WINDOW_WIDTH,Constans.WINDOW_HIGHT,this);


    }






}