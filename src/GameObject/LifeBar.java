package GameObject;

import GameKernel.utils.gameobjects.GameObject;

import java.awt.*;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LifeBar extends GameObject {
    int life;
    int nowLife;
    int injuryValue;
    double minusRate;
    double afterWidth;
    ScheduledExecutorService animator;

    public LifeBar(double x, double y, double width, double height, int life) {
        super(x, y, width, height);
        this.nowLife = life;
        this.life = life;
    }

    @Override
    public void update() {
        if (animator == null) {
            animator = Executors.newSingleThreadScheduledExecutor();
            animator.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    collider().scaleX(collider().width() - minusRate);
                    System.out.println(collider().width());
                    if (collider().width() <= afterWidth) {
                        System.out.println("end");
                        animator.shutdown();
                        animator = null;
                    }
                }
            }, 0, ((long) 1000 / 34), TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void paintComponent(Graphics g) {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    public void decreaseLife(int value) {
        injuryValue = value;
        if (injuryValue > 0) {
            nowLife -= injuryValue;
            minusRate = ((float) injuryValue / life / GlobalParameter.FPS * 2) * painter().width();
            afterWidth = ((float) nowLife / life) * painter().width();
            update();
        }
    }
}
