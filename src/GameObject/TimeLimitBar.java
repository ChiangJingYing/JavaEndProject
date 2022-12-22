package GameObject;

import GameKernel.utils.gameobjects.Animator;
import GameKernel.utils.gameobjects.GameObject;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeLimitBar extends GameObject {
    int turnTime;
    double minusRate;

    public ScheduledExecutorService animator;
    public Animator burning;

    public TimeLimitBar(double x, double y, double width, double height, int turnTime) {
        super(x, y, width, height);
        this.turnTime = turnTime;
        this.burning = new Animator("../../../Image/Burning.png", 10, 80, 80, new int[]{4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2});

        this.painter().setCenter(GlobalParameter.SCREEN_WIDTH / 2.0, 200);
        this.collider().setCenter(GlobalParameter.SCREEN_WIDTH / 2.0, 200);

        minusRate = width / turnTime / GlobalParameter.FPS;
    }

    @Override
    public void update() {
        if (animator == null) {
            animator = Executors.newSingleThreadScheduledExecutor();
            animator.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    burning.update();
                    collider().scaleX(collider().width() - minusRate);
                    if (collider().width() <= 0) {
                        animator.shutdown();
                        animator = null;
                    }
                }
            }, 0, ((long) 1000 / GlobalParameter.FPS), TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLUE);
        painter().paint(g);
        g.setColor(Color.PINK);
        collider().paint(g);

        if (animator != null)
            burning.paint((int) (collider().right() - 40), (int) collider().centerY() - 60, g);
    }

    public void reset() {
        collider().scaleX(painter().width());
    }
}
