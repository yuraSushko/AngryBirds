import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelChencherListner implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        Window.latestClickedButton=((JButton) e.getSource() ).getText();
    }
}
