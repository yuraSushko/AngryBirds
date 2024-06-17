import javax.swing.*;
import java.awt.*;
public class InstructionsPanel  extends JPanel {


        private JLabel instructions;
        private Image backroundOpenening;
        private JButton exit;
        public InstructionsPanel(int x, int y, int width, int height) {
            this.setBounds(x, y, width, height);
            this.setBackground(Color.BLACK);
            this.backroundOpenening = new ImageIcon(getClass().getResource(Constans.BACKROUND_INSTRUCTIONS_IMAGE_PATH)).getImage();
            this.setLayout(null);
            createExitButton();
            createInstrunctionLabel();
        }

    private void createInstrunctionLabel(){
        this.instructions= new JLabel(instructionsSring());
        this.instructions.setVisible(true);
        this.instructions.setBounds(Constans.INSTRUCTIONS_LABEL_X, Constans.INSTRUCTIONS_LABEL_Y, Constans.INSTRUCTIONS_LABEL_WIDTH, Constans.INSTRUCTIONS_LABEL_HIGHT);
        this.instructions.setFont(new Font(Constans.INSTRUCTIONS_LABEL_FONT_NAME, Constans.INSTRUCTIONS_LABEL_FONT, Constans.INSTRUCTIONS_LABEL_FONT_SIZE));
        this.instructions.setForeground(Constans.INSTRUCTIONS_LABEL_COLOR);
        this.add(instructions);


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
                        "<br>If that stage seems a bit too hard you can press 'B' on your keyboard and your chosen bird will turn into a bomb that can break the piller it hits and get 5 points for the pig on it." +
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
