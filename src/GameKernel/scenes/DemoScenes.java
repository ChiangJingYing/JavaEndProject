package GameKernel.scenes;

import GameKernel.utils.core.CommandSolver;
import GameKernel.utils.core.Scene;

import java.awt.*;
import java.awt.event.KeyEvent;

public class DemoScenes extends Scene {
    int x;
    int y;

    public DemoScenes() {
        x = 30;
        y = 30;
    }

    @Override
    public void sceneBegin() {

    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.drawOval(x, y, 100, 100);
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
        return new CommandSolver.KeyListener() {
            @Override
            public void keyPressed(int commandCode, long trigTime) {
                System.out.println("Pressed");
                switch (commandCode) {
                    case KeyEvent.VK_A -> x -= 10;
                    case KeyEvent.VK_S -> y += 10;
                    case KeyEvent.VK_D -> x += 10;
                    case KeyEvent.VK_W -> y -= 10;
                }
            }

            @Override
            public void keyReleased(int commandCode, long trigTime) {
                System.out.println("Release");
            }

            @Override
            public void keyTyped(char c, long trigTime) {

            }
        };
    }
}
