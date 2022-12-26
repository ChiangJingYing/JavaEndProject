package GameObject;

import GameKernel.utils.gameobjects.GameObject;
import GameObject.Setting.GlobalParameter;

import java.awt.*;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LifeBar extends GameObject {
    int life;
    public int nowLife;
    int injuryValue;
    double changeRate;
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
                    collider().scaleX(collider().width() + changeRate);
                    if (changeRate < 0 && collider().width() <= afterWidth) {
                        System.out.println("end");
                        animator.shutdown();
                        animator = null;
                    }
                    if (changeRate > 0 && collider().width() >= afterWidth) {
                        System.out.println("end");
                        animator.shutdown();
                        animator = null;
                    }
                }
            }, 0, ((long) 1000 / 34), TimeUnit.MILLISECONDS);
        } else {
            animator.shutdown();
            animator = null;
            update();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.green);
        painter().paint(g);
        g.setColor(Color.red);
        collider().paint(g);
        g.setColor(Color.black);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    public void decreaseLife(int value) {
        injuryValue = value;
        if (injuryValue > 0) {
            nowLife -= injuryValue;
            changeRate = ((float) injuryValue / life / GlobalParameter.FPS * 2) * painter().width() * -1;
            afterWidth = ((float) nowLife / life) * painter().width();
            afterWidth = (afterWidth < 1) ? 5 : afterWidth;
            update();
        }
    }

    public void recoverLife(int value) {
        if (value > 0) {
            nowLife = Math.min(life, nowLife + value);
            changeRate = ((float) value / life / GlobalParameter.FPS * 2) * painter().width();
            afterWidth = ((float) nowLife / life) * painter().width();
            afterWidth = Math.min(afterWidth, painter().width());
            update();
        }
    }

    public String getLife() {
        return (nowLife + "/" + life);
    }
}
