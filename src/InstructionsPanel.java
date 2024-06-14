import javax.swing.*;
import java.awt.*;
public class InstructionsPanel  extends JPanel {



        private Image backroundOpenening;
        private JButton exit;
        public InstructionsPanel(int x, int y, int width, int height) {
            this.setBounds(x, y, width, height);
            this.setBackground(Color.BLACK);
//        this.backroundOpenening = new ImageIcon(getClass().getResource("resources/angryBirdsStartPage.png")).getImage();
            this.backroundOpenening = new ImageIcon(getClass().getResource("resources/angryBirdsInstructions.png")).getImage();
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
            //playB.setIcon( new ImageIcon(getClass().getResource("resources/angryBirdsInstructions.png")));

            this.add(exit);
//        this.setVisible(true);
//        this.setFocusable(true);
//        this.requestFocus();

        }

    public JButton getExitButton() {
        return exit;
    }

    protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(this.backroundOpenening,0,0,Constans.WINDOW_WIDTH,Constans.WINDOW_HIGHT,this);


        }



}
