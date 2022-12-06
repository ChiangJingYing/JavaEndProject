package GameObject;

import GameKernel.utils.controllers.ImageController;

import java.awt.*;

public class Ball {
    Attribute attribute;
    public Image image;
    boolean isStrong = false;
    public int x;
    public int y;

    public Ball(int x, int y, Attribute attribute) {
        this.x = x;
        this.y = y;
        this.attribute = attribute;
        if (!isStrong)
            image = (ImageController.instance().tryGetImage("../../../Image/Balls/" + attribute + ".png"));
        else // When isStrong ball get other than default img
            image = (ImageController.instance().tryGetImage("../Image/Balls/" + attribute + ".png"));
    }
}
