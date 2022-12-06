import BallPanel.BallPlatePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GamePanel extends JPanel {
    ArrayList<MainCharacter> team;
    ArrayList<Enemy> enemies;

    int levelCount;
    int roundCount;
    int moveCountdown;
    int Combo;

    public GamePanel(int levelCount, int roundCount, int moveCountdown) {
        this.levelCount = levelCount;
        this.roundCount = roundCount;
        this.moveCountdown = moveCountdown;
        this.setBackground(new Color(0x643500));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        Random random = new Random();
        team = new ArrayList<>();
        ArrayList<Enemy> enemies = new ArrayList<>();


        BufferedImage testImage;
        try {
            testImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("./Image/balls/LIGHT.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        team.add(new MainCharacter(testImage, Attribute.DARK, 1, 1, 1, 1, 1, 1));
        team.add(new MainCharacter(testImage, Attribute.DARK, 1, 1, 1, 1, 1, 1));
        this.add(new TeamPanel(team));
        this.add(new BallPlatePanel());
    }

    // used to test
    public GamePanel() {
        JLabel label = new JLabel("Hello world");
        this.add(label);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}

