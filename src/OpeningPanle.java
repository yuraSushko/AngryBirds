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
        createPlayButton();
        createInstrunctionButton();
        playMusic();
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