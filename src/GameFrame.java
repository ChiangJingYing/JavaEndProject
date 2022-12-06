import BallPanel.Ball;
import GameKernel.utils.core.CommandSolver;
import GameKernel.utils.core.GameKernel;
import GameObject.Attribute;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameFrame extends JFrame {
    ArrayList<Ball> balls;
    Ball ControlBall;
    Random random;

    public GameFrame(String title, int width, int height) throws HeadlessException {
        super(title);
        random = new Random();
        balls = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                balls.add(new Ball(j * 100, i * 100, Attribute.values()[random.nextInt(6)]));
            }
        }

        this.setPreferredSize(new Dimension(width, height));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GameKernel.GameInterface gi = new GameKernel.GameInterface() {
            @Override
            public void paint(Graphics g) {
                for (Ball ball : balls) {
                    g.drawImage(ball.image, ball.x, ball.y, 100, 100, null);
                }
            }

            @Override
            public void update() {

            }
        };
        GameKernel gk = new GameKernel.Builder(gi, 60, 60)
                .initListener()
                .enableMouseTrack((e, state, trigTime) -> {
                    if (state == CommandSolver.MouseState.DRAGGED) {
                        Ball HitBall = checkMouseOnBall(e.getPoint());
                        if (HitBall != null) {
                            if (ControlBall == null) {
                                ControlBall = HitBall;
                            } else if (HitBall != ControlBall) {
                                int tmpx = HitBall.x;
                                int tmpy = HitBall.y;
                                int indexControlBall = ControlBall.y / 100 * 5 + ControlBall.x / 100;
                                int indexHotBall = HitBall.y / 100 * 5 + HitBall.x / 100;
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
                })
                .mouseForceRelease()
                .gen();

        this.add(gk);
        this.pack();
        this.setVisible(true);
        gk.run(true);
    }

    public Ball checkMouseOnBall(Point e) {
        int x = e.x;
        int y = e.y;
        for (Ball ball : balls) {
            if (x >= ball.x && x < ball.x + 100 && y >= ball.y && y < ball.y + 100) return ball;
        }
        return null;
    }
}
