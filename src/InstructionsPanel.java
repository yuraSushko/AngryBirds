import javax.swing.*;
import java.awt.*;
public class InstructionsPanel  extends JPanel {



        private Image backroundOpenening;
        private JButton exit;
        public InstructionsPanel(int x, int y, int width, int height) {
            this.setBounds(x, y, width, height);
            this.setBackground(Color.BLACK);
            this.backroundOpenening = new ImageIcon(getClass().getResource(Constans.BACKROUND_INSTRUCTIONS_IMAGE_PATH)).getImage();
            this.setLayout(null);
            addExitButton();
        }

    private void addExitButton(){
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

    }

    public JButton getExitButton() {
        return exit;
    }

    protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(this.backroundOpenening,0,0,Constans.WINDOW_WIDTH,Constans.WINDOW_HIGHT,this);


        }



}
