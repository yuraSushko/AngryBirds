import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Window extends JFrame {
    static String latestClickedButton;
    private OpeningPanle openningPanel;
    private MainScene mainScene;
    private InstructionsPanel instructionsPanel;
    private PanelChencherListner panelChencherListner;
    public Window() throws IOException {
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(Constans.WINDOW_WIDTH, Constans.WINDOW_HIGHT);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panelChencherListner = new PanelChencherListner();
        latestClickedButton= "EMPTY";
        this.openningPanel = new OpeningPanle(0,0,Constans.WINDOW_WIDTH, Constans.WINDOW_HIGHT);
        this.openningPanel.getPlayB().addActionListener(panelChencherListner);
        this.openningPanel.getInstructionsB().addActionListener(panelChencherListner);
        this.add(openningPanel);

        openningPanel.setVisible(true);
        this.mainScene = new MainScene(0,0,Constans.WINDOW_WIDTH, Constans.WINDOW_HIGHT);
        this.mainScene.getExitButton().addActionListener(panelChencherListner);
        this.add(mainScene);
        mainScene.setVisible(false);
        this.instructionsPanel = new InstructionsPanel(0,0,Constans.WINDOW_WIDTH, Constans.WINDOW_HIGHT);
        this.instructionsPanel.getExitButton().addActionListener(panelChencherListner);
        this.add(instructionsPanel);
        instructionsPanel.setVisible(false);
        this.repaint();
        changePannel();
        Insets insets = this.getInsets();

    }

    void changePannel(){
        new Thread(()->{
            while (true){
                if (latestClickedButton.equals(Constans.EXIT_BUTTON_TEXT) &&
                    this.openningPanel.isVisible()==false ){
                    this.mainScene.setVisible(false);
                    this.instructionsPanel.setVisible(false);
                    this.openningPanel.setVisible(true);
                   // repaint();
                }

                else if(latestClickedButton.equals(Constans.PLAY_BUTTON_TEXT) &&
                        this.mainScene.isVisible()==false){
                    this.instructionsPanel.setVisible(false);
                    this.openningPanel.setVisible(false);
                    this.mainScene.setVisible(true);
                    this.mainScene.setFocusable(true);
                    this.mainScene.requestFocusInWindow();
                    //this.mainScene.setFocusTraversalKeysEnabled(false);
                 //   repaint();
                }


                else if(latestClickedButton.equals(Constans.INSTRUCTIONS_BUTTON_TEXT) &&
                        this.instructionsPanel.isVisible()==false ){
                    this.openningPanel.setVisible(false);
                    this.mainScene.setVisible(false);
                    this.instructionsPanel.setVisible(true);
                   // repaint();
                }
                repaint();

            }
        }).start();
    }






}
