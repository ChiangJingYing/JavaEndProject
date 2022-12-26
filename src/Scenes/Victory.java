package Scenes;

import GameKernel.utils.controllers.ImageController;
import GameKernel.utils.core.CommandSolver;
import GameKernel.utils.core.Scene;
import GameObject.Setting.GlobalParameter;

import java.awt.*;

public class Victory extends Scene {
    Image Victory_Image;

    @Override
    public void sceneBegin() {
        Victory_Image = ImageController.instance().tryGetImage("../../../Victory.png");

    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(Victory_Image, GlobalParameter.SCREEN_WIDTH / 2 - 317, GlobalParameter.SCREEN_HEIGHT / 2 - 232, null);
    }

    @Override
    public void update() {

    }

    @Override
    public CommandSolver.MouseCommandListener mouseListener() {
        return null;
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return null;
    }
}
