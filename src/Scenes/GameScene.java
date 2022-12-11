package Scenes;

import GameKernel.utils.controllers.ImageController;
import GameKernel.utils.core.CommandSolver;
import GameKernel.utils.core.Scene;
import GameObject.Attribute;
import GameObject.Ball;
import GameObject.Enemy;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static GameObject.GlobalParameter.*;

public class GameScene extends Scene {
    ArrayList<ArrayList<Ball>> balls;
    ArrayList<ArrayList<Enemy>> enemies;
    Ball ControlBall;
    Random random;
    int numLevel = 3;
    int nowLevel = 0;

    @Override
    public void sceneBegin() {
        random = new Random();
        balls = new ArrayList<>();
        enemies = new ArrayList();

        // init Balls
        for (int i = 0; i < BALLPLATE_HEIGHT; i++) {
            balls.add(new ArrayList<>());
            for (int j = 0; j < BALLPLATE_WIDTH; j++) {
                balls.get(i).add(new Ball(j * BALL_WIDTH + SCREEN_WIDTH / 4, i * BALL_HEIGHT + (int) (SCREEN_WIDTH * 0.3),
                        j, i, Attribute.values()[random.nextInt(6)]));
            }
        }

        // init enemies
        for (int i = 0; i < numLevel; i++) {
            enemies.add(new ArrayList<>());
            for (int j = 0; j < 3; j++) {
                enemies.get(i).add(new Enemy(ImageController.instance().tryGetImage("../../../Image/Balls/None.png"),
                        Attribute.None, 1, 1, 1, 1,
                        j * BALL_WIDTH + SCREEN_WIDTH / 2 - (int) (BALL_WIDTH * 1.5), 30));
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
                        BALL_WIDTH, BALL_HEIGHT, null);
            }
        }
        // draw enemies
        for (Enemy enemy : enemies.get(nowLevel)) {
            g.drawImage(enemy.enemyImage, enemy.x, enemy.y, 100, 100, null);
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
                        exchangeBall(ControlBall, HitBall);
                        ControlBall = null;
                    }
                }
            }
            if (state == CommandSolver.MouseState.RELEASED) {
                ControlBall = null;
                int tmp = EliminateBall();
                System.out.println("Combo: " + tmp);
            }
        };
    }

    private void exchangeBall(Ball firstBall, Ball secondBall) {
        int[] tmpPosition = {secondBall.y, secondBall.x};
        int[] indexFirstBall = {firstBall.indexY, firstBall.indexX};
        int[] indexSecondBall = {secondBall.indexY, secondBall.indexX};
        secondBall.indexX = indexFirstBall[1];
        secondBall.indexY = indexFirstBall[0];
        secondBall.x = firstBall.x;
        secondBall.y = firstBall.y;
        firstBall.indexX = indexSecondBall[1];
        firstBall.indexY = indexSecondBall[0];
        firstBall.x = tmpPosition[1];
        firstBall.y = tmpPosition[0];

        balls.get(indexFirstBall[0]).set(indexFirstBall[1], secondBall);
        balls.get(indexSecondBall[0]).set(indexSecondBall[1], firstBall);
    }

    // 消珠 return -1 if not eliminate any ball
    private int EliminateBall() {
        int countCombo = -1;
        // vertical
        for (int i = 0; i < balls.get(0).size(); i++) {
            ArrayList<Ball> sames = new ArrayList<>();
            for (int j = 0; j < balls.size() - 1; j++) {
                sames.add(balls.get(j).get(i));
                if (balls.get(j).get(i).attribute == balls.get(j + 1).get(i).attribute && sames.get(0).attribute != Attribute.None) {
                    if (sames.contains(balls.get(j + 1).get(i)))
                        sames.add(balls.get(j + 1).get(i));
                } else {
                    if (sames.size() >= 3) {
                        countCombo += 1;
                        for (Ball same : sames) {
                            balls.get(same.indexY).set(same.indexX, new Ball(same.x, same.y, same.indexX, same.indexY, Attribute.None));
                        }
                    }
                    sames.clear();
                }
                if (j + 1 == balls.size() - 1) {
                    if (sames.size() >= 2) {
                        if (balls.get(j + 1).get(i).attribute == sames.get(0).attribute && sames.get(0).attribute != Attribute.None)
                            sames.add(balls.get(j + 1).get(i));
                        countCombo += 1;
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
                        countCombo += 1;
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
                        countCombo += 1;
                        for (Ball same : sames) {
                            list.set(same.indexX, new Ball(same.x, same.y, same.indexX, same.indexY, Attribute.None));
                        }
                    }
                }
            }
        }

        return (countCombo == -1) ? -1 : countCombo + 1;
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
