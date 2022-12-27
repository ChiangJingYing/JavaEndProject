package Scenes;

import GameKernel.utils.core.CommandSolver;
import GameKernel.utils.core.Scene;
import GameObject.Setting.GlobalParameter;

import java.awt.*;

public class EndScene extends Scene {
    @Override
    public void sceneBegin() {

    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("標楷體", Font.PLAIN, 56));
        g.drawString("GameOver", (int) (GlobalParameter.SCREEN_WIDTH / 3.5), GlobalParameter.SCREEN_HEIGHT / 2);
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
