import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Ball extends JPanel {
    Attribute attribute;
    private BufferedImage image;
    boolean isStrong = false;
    int x;
    int y;

    public Ball(int x, int y, Attribute attribute) {
        this.x = x;
        this.y = y;
        this.attribute = attribute;
        try {
            if (!isStrong)
                image = ImageIO.read(Objects.requireNonNull(getClass().getResource("./Image/Balls/" + attribute + ".png")));
            else // When isStrong ball get other than default img
                image = ImageIO.read(Objects.requireNonNull(getClass().getResource(attribute + ".png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.setPreferredSize(new Dimension(80, 80));
        this.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, 80, 80, null);
    }
}
