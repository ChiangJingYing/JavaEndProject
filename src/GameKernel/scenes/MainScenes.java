package GameKernel.scenes;

import GameKernel.utils.controllers.SceneController;
import GameKernel.utils.core.CommandSolver;
import GameKernel.utils.core.Scene;

import java.awt.*;

public class MainScenes extends Scene {
    int x1;
    int y1;
    int width1;
    int height1;
    int x2;
    int y2;
    int width2;
    int height2;
    boolean isFocus1 = false;
    boolean isFocus2 = false;

    public MainScenes() {

    }

    @Override
    public void sceneBegin() {
        x1 = 30;
        y1 = 30;
        width1 = 300;
        height1 = 100;

        x2 = 30;
        y2 = 200;
        width2 = 300;
        height2 = 100;
    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void paint(Graphics g) {
        if (!isFocus1) {
            g.setColor(Color.red);
            g.drawRect(x1, y1, width1, height1);
        } else {
            g.setColor(Color.blue);
            g.drawRect(x1, y1, width1, height1);
        }

        if (!isFocus2) {
            g.setColor(Color.red);
            g.drawRect(x2, y2, width2, height2);
        } else {
            g.setColor(Color.blue);
            g.drawRect(x2, y2, width2, height2);
        }
    }

    @Override
    public void update() {

    }

    public boolean isCollision1(int x, int y) {
        return x > this.x1 && x < this.x1 + width1 && y > this.y1 && y < this.y1 + height1;
    }

    public boolean isCollision2(int x, int y) {
        return x > this.x2 && x < this.x2 + width2 && y > this.y2 && y < this.y2 + height2;
    }

    @Override
    public CommandSolver.MouseCommandListener mouseListener() {
        return (e, state, trigTime) -> {

            if (state != null) {
                // detect if mouse on rect1 then Color change.
                if (isCollision1(e.getX(), e.getY())) {
                    // e.getX() -> get mouse position, e.getY() -> get mouse position y
                    System.out.println("!");
                    if (state == CommandSolver.MouseState.RELEASED)
                        // detect if mouse in the rect.
                        SceneController.instance().change(new DemoScenes()); // this will change scene.
                    else if (state == CommandSolver.MouseState.MOVED)
                        isFocus1 = true;
                } else {
                    // detect mouse move
                    if (state == CommandSolver.MouseState.MOVED) isFocus1 = false;
                }
            }
        }; // <- notice here should have " ; ".
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return null;
    }
}
