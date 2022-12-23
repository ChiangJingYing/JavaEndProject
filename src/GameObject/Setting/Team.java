package GameObject.Setting;

import GameKernel.utils.controllers.ImageController;
import GameObject.Attribute;
import GameObject.MainCharacter;

import java.util.ArrayList;

public class Team {
    public ArrayList<MainCharacter> team;

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
    }
}
