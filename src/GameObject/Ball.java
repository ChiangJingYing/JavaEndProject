package GameObject;

import GameKernel.utils.controllers.ImageController;

import java.awt.*;

public class Ball {
    public Attribute attribute;
    public Image image;
    public boolean isStrong = false;
    public int x;
    public int y;
    public int indexX;
    public int indexY;

    public Ball(int x, int y, int indexX, int indexY, Attribute attribute) {
        this.x = x;
        this.y = y;
        this.indexX = indexX;
        this.indexY = indexY;
        this.attribute = attribute;
            image = (ImageController.instance().tryGetImage("../../../Image/Balls/" + attribute + ".png"));
    }
    public Ball(int x, int y, int indexX, int indexY, Attribute attribute,boolean isStrong) {
        this.x = x;
        this.y = y;
        this.indexX = indexX;
        this.indexY = indexY;
        this.attribute = attribute;
        this.isStrong = isStrong;
        image = (ImageController.instance().tryGetImage("../../../Image/Balls/" + attribute + ".png"));
    }
}
