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
    ArrayList<ArrayList<Ball>> balls;
    Ball ControlBall;
    Random random;

    @Override
    public void sceneBegin() {
        random = new Random();
        balls = new ArrayList<>();
        // init Balls
        for (int i = 0; i < BALLPLATE_HEIGHT; i++) {
            balls.add(new ArrayList<>());
            for (int j = 0; j < BALLPLATE_WIDTH; j++) {
                balls.get(i).add(new Ball(j * BALL_WIDTH, i * BALL_HEIGHT, j, i, Attribute.values()[random.nextInt(6)]));
            }
        }
    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void paint(Graphics g) {
        // draw BallPlate
        for (ArrayList<Ball> list : balls) {
            for (Ball ball : list) {
                g.drawImage(ball.image, ball.x, ball.y,
                        100, 100, null);
            }
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
                        int[] tmpPosition = {HitBall.y, HitBall.x};
                        int[] indexControlBall = {ControlBall.indexY, ControlBall.indexX};
                        int[] indexHotBall = {HitBall.indexY, HitBall.indexX};
                        HitBall.indexX = indexControlBall[1];
                        HitBall.indexY = indexControlBall[0];
                        HitBall.x = ControlBall.x;
                        HitBall.y = ControlBall.y;
                        ControlBall.indexX = indexHotBall[1];
                        ControlBall.indexY = indexHotBall[0];
                        ControlBall.x = tmpPosition[1];
                        ControlBall.y = tmpPosition[0];

                        balls.get(indexControlBall[0]).set(indexControlBall[1], HitBall);
                        balls.get(indexHotBall[0]).set(indexHotBall[1], ControlBall);
                        ControlBall = null;
                    }
                }
            }
            if (state == CommandSolver.MouseState.RELEASED) {
                ControlBall = null;
                for (ArrayList<Ball> list : balls) {
                    int same = 0;
                    for (int i = 0; i < list.size() - 1; i++) {
                        if (list.get(i).attribute == list.get(i + 1).attribute) same += 1;
                        else {
                            // Not same
                            if (same >= 2) {
                                for (int j = 0; j < same + 1; j++) list.remove(i - j);
                                same = 0;
                            }
                        }
                        // Hit wall
                        if (i + 1 == list.size() - 1) {
                            if (same >= 2) {
                                for (int j = 0; j < same + 1; j++) list.remove(i + 1 - j);
                                same = 0;
                            }
                        }
                    }
                }
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
        for (ArrayList<Ball> list : balls) {
            for (Ball ball : list) {
                if (x >= ball.x && x < ball.x + BALL_WIDTH && y >= ball.y && y < ball.y + BALL_HEIGHT) return ball;
            }
        }
        return null;
    }
}
