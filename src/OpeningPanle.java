import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class OpeningPanle extends JPanel {

    private Image backroundOpenening;
    private JButton playB;
    private JButton instructionsB;
    Font f;
    public OpeningPanle(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setBackground(Color.BLACK);
        this.backroundOpenening = new ImageIcon(getClass().getResource("resources/angryBirdsOpeneing.jpg")).getImage();
        this.setLayout(null);
        this.playB =new JButton(Constans.PLAY_BUTTON_TEXT);
        playB.setBounds(Constans.OPENINIG_SCREEN_PLAY_BUTTON_X
                ,Constans.OPENINIG_SCREEN_PLAY_BUTTON_Y
                ,Constans.OPENINIG_SCREEN_BOTH_BUTTON_WIDTH
                ,Constans.OPENINIG_SCREEN_BOTH_BUTTON_HIGHT);
        this.instructionsB =new JButton(Constans.INSTRUCTIONS_BUTTON_TEXT);
        instructionsB.setBounds(Constans.OPENINIG_SCREEN_INSTRUCTIONS_BUTTON_X
                ,Constans.OPENINIG_SCREEN_INSTRUCTIONS_BUTTON_Y
                ,Constans.OPENINIG_SCREEN_BOTH_BUTTON_WIDTH
                ,Constans.OPENINIG_SCREEN_BOTH_BUTTON_HIGHT);

        playB.setForeground(Color.darkGray);
        playB.setFont(new Font("Algerian", Font.PLAIN, 50));
        playB.setContentAreaFilled(false);
        playB.setBorderPainted(false);
        playB.setFocusable(false);
        //playB.setIcon( new ImageIcon(getClass().getResource("resources/angryBirdsInstructions.png")));
        instructionsB.setFocusable(false);
        instructionsB.setBorderPainted(false);
        instructionsB.setContentAreaFilled(false);
        instructionsB.setFont(new Font("Algerian", Font.ROMAN_BASELINE, 50));
        this.add(playB);

        this.add(instructionsB);


//        this.setVisible(true);
//        this.setFocusable(true);
//        this.requestFocus();

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