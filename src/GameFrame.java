import javax.swing.*;
import java.awt.*;
public class GameFrame extends JFrame {
    public GameFrame(String title, int width, int height) throws HeadlessException {
        super(title);
        this.setPreferredSize(new Dimension(width, height));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel gamePanel = new GamePanel(1,1,1);
        this.add(gamePanel);

        this.pack();
        this.setVisible(true);
    }
}
