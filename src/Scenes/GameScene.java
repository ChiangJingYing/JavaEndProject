package Scenes;

import GameKernel.utils.core.CommandSolver;
import GameKernel.utils.core.Scene;
import GameObject.Attribute;
import GameObject.Ball;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static GameObject.GlobalParameter.*;

public class GameScene extends Scene {
    ArrayList<Ball> balls;
    Ball ControlBall;
    Random random;

    @Override
    public void sceneBegin() {
        random = new Random();
        balls = new ArrayList<>();
        // init Balls
        for (int i = 0; i < BALLPLATEHEIGHT; i++) {
            for (int j = 0; j < BALLPLATEWIDTH; j++) {
                balls.add(new Ball(j * BALLWIDTH, i * BALLHEIGHT, Attribute.values()[random.nextInt(6)]));
            }
        }
    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void paint(Graphics g) {
        // draw BallPlate
        for (Ball ball : balls) {
            g.drawImage(ball.image, ball.x, ball.y, 100, 100, null);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public CommandSolver.MouseCommandListener mouseListener() {
        return (e, state, trigTime) -> {
            if (state == CommandSolver.MouseState.DRAGGED) {
                Ball HitBall = checkMouseOnBall(e.getPoint());
                // get control ball
                if (HitBall != null) {
                    if (ControlBall == null) {
                        ControlBall = HitBall;
                    } else if (HitBall != ControlBall) {
                        // exchange two ball
                        int tmpx = HitBall.x;
                        int tmpy = HitBall.y;
                        int indexControlBall = ControlBall.y / BALLHEIGHT * BALLPLATEWIDTH + ControlBall.x / BALLWIDTH;
                        int indexHotBall = HitBall.y / BALLHEIGHT * BALLPLATEWIDTH + HitBall.x / BALLWIDTH;
                        HitBall.x = ControlBall.x;
                        HitBall.y = ControlBall.y;
                        ControlBall.x = tmpx;
                        ControlBall.y = tmpy;

                        balls.set(indexControlBall, HitBall);
                        balls.set(indexHotBall, ControlBall);
                        ControlBall = null;
                    }
                }
            }
            if (state == CommandSolver.MouseState.RELEASED) {
                ControlBall = null;
            }
        };
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return null;
    }

    public Ball checkMouseOnBall(Point e) {
        int x = e.x;
        int y = e.y;
        for (Ball ball : balls) {
            if (x >= ball.x && x < ball.x + BALLWIDTH && y >= ball.y && y < ball.y + BALLHEIGHT) return ball;
        }
        return null;
    }
}
