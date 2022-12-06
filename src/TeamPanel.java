import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class TeamPanel extends JPanel {
    ArrayList<MainCharacter> team;

    public TeamPanel(ArrayList<MainCharacter> team) {
        this.team = team;
        this.setMaximumSize(new Dimension(100, 100));
        this.setLayout(new GridLayout(1, 5, 10, 10));
        this.setBackground(new Color(0, 0, 0, 0));

        for (int i = 0; i < team.size(); i++) {
            // TODO used to test
            //  should delete
            ImagePanel tmpPanel = new ImagePanel("./Image/balls/LIGHT.png");
            this.add(tmpPanel);
        }
    }
}

class ImagePanel extends JPanel {
    private BufferedImage image;
    MainCharacter character;

    public ImagePanel(String imgUrl) {
        // TODO used to test
        //  should delete
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource(imgUrl)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.setPreferredSize(new Dimension(60, 60));
        this.setLayout(null);
    }

    public ImagePanel(MainCharacter character) {
        this.character = character;
        this.setPreferredSize(new Dimension(60, 60));
        this.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, 100, 100, null);
    }
}
