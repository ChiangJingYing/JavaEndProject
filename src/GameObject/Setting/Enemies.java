package GameObject.Setting;

import GameKernel.utils.controllers.ImageController;
import GameObject.Attribute;
import GameObject.Enemy;

import java.util.ArrayList;

import static GameObject.Setting.GlobalParameter.BALL_WIDTH;
import static GameObject.Setting.GlobalParameter.SCREEN_WIDTH;

public class Enemies {
    public ArrayList<ArrayList<Enemy>> enemies = new ArrayList<>();

    public Enemies(int numLevel) {
        for (int i = 0; i < numLevel; i++) {
            enemies.add(new ArrayList<>());
        }
        enemies.get(0).add(new Enemy(ImageController.instance().tryGetImage("../../../boss/巨象.png"),
                Attribute.None, 1, 100, 1, 100000,
                SCREEN_WIDTH / 2 - (int) (BALL_WIDTH * 1.5), 30));
        enemies.get(0).add(new Enemy(ImageController.instance().tryGetImage("../../../boss/毒龍.png"),
                Attribute.None, 2, 400, 100000, 100000,
                BALL_WIDTH + SCREEN_WIDTH / 2 - (int) (BALL_WIDTH * 1.5), 30));
        enemies.get(0).add(new Enemy(ImageController.instance().tryGetImage("../../../boss/毒龍.png"),
                Attribute.None, 3, 600, 100000, 100000,
                2 * BALL_WIDTH + SCREEN_WIDTH / 2 - (int) (BALL_WIDTH * 1.5), 30));
    }
}
