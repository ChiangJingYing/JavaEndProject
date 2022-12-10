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

                EliminateBall();
            }
        };
    }

    // 消珠
    private void EliminateBall() {
        // vertical
        for (int i = 0; i < balls.get(0).size(); i++) {
            ArrayList<Ball> sames = new ArrayList<>();
            for (int j = 0; j < balls.size() - 1; j++) {
                sames.add(balls.get(j).get(i));
                if (balls.get(j).get(i).attribute == balls.get(j + 1).get(i).attribute) {
                    if (sames.contains(balls.get(j + 1).get(i)))
                        sames.add(balls.get(j + 1).get(i));
                } else {
                    if (sames.size() >= 3) {
                        for (Ball same : sames) {
                            balls.get(same.indexY).set(same.indexX, new Ball(same.x, same.y, same.indexX, same.indexY, Attribute.None));
                        }
                    }
                    sames.clear();
                }
                if (j + 1 == balls.size() - 1) {
                    if (sames.size() >= 2) {
                        if (balls.get(j + 1).get(i).attribute == sames.get(0).attribute)
                            sames.add(balls.get(j + 1).get(i));
                        for (Ball same : sames) {
                            balls.get(same.indexY).set(same.indexX, new Ball(same.x, same.y, same.indexX, same.indexY, Attribute.None));
                        }
                    }
                }
            }
        }

        // Horizontal
        for (ArrayList<Ball> list : balls) {
            ArrayList<Ball> sames = new ArrayList<>();
            for (int i = 0; i < list.size() - 1; i++) {
                sames.add(list.get(i));
                if (list.get(i).attribute == list.get(i + 1).attribute && sames.get(0).attribute != Attribute.None) {
                    if (sames.contains(list.get(i + 1)))
                        sames.add(list.get(i + 1));
                } else {
                    // Not same
                    if (sames.size() >= 3) {
                        for (Ball same : sames) {
                            list.set(same.indexX, new Ball(same.x, same.y, same.indexX, same.indexY, Attribute.None));
                        }
                    }
                    sames.clear();
                }
                // Hit wall
                if (i + 1 == list.size() - 1) {
                    if (sames.size() >= 2) {
                        if (list.get(i + 1).attribute == sames.get(0).attribute && sames.get(0).attribute != Attribute.None)
                            sames.add(list.get(i + 1));
                        for (Ball same : sames) {
                            list.set(same.indexX, new Ball(same.x, same.y, same.indexX, same.indexY, Attribute.None));
                        }
                    }
                }
            }
        }
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
