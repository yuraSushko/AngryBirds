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

    private String  instructionsSring(){
        String instuctions=
                "<html>Once you click play your game begins,<br>take a bird from the rest bench on the" +
                        "top right by clicking on it, it will pop onto the slingshot" +
                        "<br>Now you can drag your bird and try to hit pigs." +
                        "<br>If that stage seems a bit too hard you can press \\\"B\\\" on your keyboard and your chosen bird will turn into a bomb that can break the piller it hits and get 5 points for the pig on it." +
                        "<br>If a pig is hit by a bird it's worth 10 points." +
                        "<br> If you run out of birds you pay 20 points" +
                        "<br>If your points get below 0 you lose." +
                        "<br>By clicking play again or exit your game will restart." +
                        "</html>"

                ;
             //   "Once you click play your game begins,\n take a bird from the rest bench on the";
                /* top right by clicking on it, it will pop onto the slingshot.\n
                Now you can drag your bird and try to hit pigs.
                If that stage seems a bit too hard you can press \"B\" on your keyboard and your chosen bird will turn into a bomb that can break the piller it hits and get 5 points for the pig on it.\n
                If a pig is hit by a bird it's worth 10 points.\n
                If you run out of birds you pay 20 points.\n
                If your points get below 0 you lose.\n
                By clicking play again or exit your game will restart. "*/;
    return instuctions;




    }


}
