package GameObject.Setting;

import GameKernel.utils.controllers.ImageController;
import GameObject.Attribute;
import GameObject.LifeBar;
import GameObject.MainCharacter;

import java.awt.*;
import java.util.ArrayList;

import static GameObject.Setting.GlobalParameter.*;

public class Team {
    public ArrayList<MainCharacter> team;
    int life;
    public LifeBar teamLife;

    public Team() {
        team = new ArrayList<>();
        team.add(new MainCharacter(ImageController.instance().tryGetImage("../../../Image/Balls/None.png"),
                Attribute.DARK, 1000, 100, 500, 5, 150, 210));
        team.add(new MainCharacter(ImageController.instance().tryGetImage("../../../Image/Balls/None.png"),
                Attribute.DARK, 1000, 100, 500, 5, 250, 210));
        team.add(new MainCharacter(ImageController.instance().tryGetImage("../../../Image/Balls/None.png"),
                Attribute.DARK, 1000, 100, 500, 5, 350, 210));
        team.add(new MainCharacter(ImageController.instance().tryGetImage("../../../Image/Balls/None.png"),
                Attribute.DARK, 1000, 100, 500, 5, 450, 210));
        team.add(new MainCharacter(ImageController.instance().tryGetImage("../../../Image/Balls/None.png"),
                Attribute.DARK, 1000, 100, 500, 5, 550, 210));
        team.add(new MainCharacter(ImageController.instance().tryGetImage("../../../Image/Balls/None.png"),
                Attribute.DARK, 1000, 100, 500, 5, 650, 210));

        calculateLife();
        teamLife = new LifeBar(-1, -1, (BALL_WIDTH * BALLPLATE_WIDTH), 20, life);
        teamLife.painter().setCenter(GlobalParameter.SCREEN_WIDTH / 2.0, 200);
        teamLife.collider().setCenter(GlobalParameter.SCREEN_WIDTH / 2.0, 200);
    }

    private void calculateLife() {
        for (MainCharacter m : team) {
            life += m.healthValue;
        }
    }

    public void paint(Graphics g) {
        for (MainCharacter m : this.team) {
            g.drawImage(m.character_Image, m.x, m.y, TEAM_WIDTH, TEAM_HEIGHT, null);
        }
    }
}
