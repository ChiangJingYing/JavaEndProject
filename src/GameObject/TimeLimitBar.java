package GameObject;

import GameKernel.utils.gameobjects.GameObject;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeLimitBar extends GameObject {
    int turnTime;
    double minusRate;

    public ScheduledExecutorService animator;

    public TimeLimitBar(double x, double y, double width, double height, int turnTime) {
        super(x, y, width, height);
        this.turnTime = turnTime;

        minusRate = width / turnTime / GlobalParameter.FPS;
    }

    @Override
    public void update() {
        if (animator == null) {
            animator = Executors.newSingleThreadScheduledExecutor();
            animator.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    collider().scaleX(collider().width() - minusRate);
                    if(collider().width() <=0 ){
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
    }

    public void reset() {
        collider().scaleX(painter().width());
    }
}
