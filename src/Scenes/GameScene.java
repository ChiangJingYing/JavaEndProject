package Scenes;

import GameKernel.utils.core.CommandSolver;
import GameKernel.utils.core.Scene;
import GameObject.Attribute;
import GameObject.Ball;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static GameObject.GlobalParameter.*;

public class GameScene extends Scene {
    ArrayList<ArrayList<Ball>> balls;
    Ball ControlBall;
    Random random;
    int turnTime = 15;
    Timer turnBallTimer;
    boolean canTurning = true;

    @Override
    public void sceneBegin() {
        random = new Random();
        balls = new ArrayList<>();
        // init Balls
        for (int i = 0; i < BALLPLATE_HEIGHT; i++) {
            balls.add(new ArrayList<>());
            for (int j = 0; j < BALLPLATE_WIDTH; j++) {
                balls.get(i).add(new Ball(j * BALL_WIDTH + SCREEN_WIDTH / 4, i * BALL_HEIGHT +(int)(SCREEN_WIDTH * 0.3),
                        j, i, Attribute.values()[random.nextInt(6)]));
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
            if (state == CommandSolver.MouseState.DRAGGED && canTurning) {
                Ball HitBall = checkMouseOnBall(e.getPoint());
                if (HitBall != null) {
                    // 轉珠時間計時
                    turnBallTimer = new Timer();
                    turnBallTimer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            turnTime -= 1;
                            if (turnTime == -1) {
                                canTurning = false;
                                turnBallTimer.cancel();
                            }
                        }
                    }, 0, 1000);
                    // 移動的珠
                    if (ControlBall == null) {
                        ControlBall = HitBall;
                    // 被交換的珠
                    } else if (HitBall != ControlBall) {
                        // 交換兩珠子
                        exchangeBall(ControlBall, HitBall);
                        ControlBall = null;
                    }
                }
            }
            if (!canTurning || state == CommandSolver.MouseState.RELEASED) {
                ControlBall = null;
                int tmp = EliminateBall();
                System.out.println("Combo: " + tmp);

                // 回復成可以轉珠
                turnTime = 15;
                canTurning = true;
            }
        };
    }

    /**
     * 交換兩顆珠子
     * @param firstBall 第一顆要交換的珠子
     * @param secondBall 第二顆要交換的珠子
     */
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

    /**
     * 消除水平、垂直三顆以上相同屬性的珠子
     * 回傳每種屬性（包含強化珠）消除個數
     *
      * @return
     * eliminateBall: 前六個為個屬一般珠 後六為個屬強化珠 最後為Combo數，若無為-1
     */
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
