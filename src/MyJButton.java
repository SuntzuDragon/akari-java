import javax.swing.*;
import java.awt.*;

public class MyJButton extends JButton {

    public MyJButton() {
        this.setBackground(Color.WHITE);
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setFocusPainted(false);
        this.setEnabled(false);
    }
}
