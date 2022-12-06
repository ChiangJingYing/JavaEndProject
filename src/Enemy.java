import GameObject.Attribute;
import GameObject.Skill;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy {
    BufferedImage enemyImage;
    Attribute attribute;
    public int attackCountDown;
    int attackPower;
    int defencePower;
    int healthValue;
    boolean isSurvive = true;
    boolean haveSkill;
    Skill skill;

    int x;
    int y;


    public Enemy(BufferedImage enemyImage, Attribute attribute,
                 int attackCountDown, int attackPower, int defencePower,
                 int healthValue, int x, int y, Skill skill) {
        this.enemyImage = enemyImage;
        this.attribute = attribute;
        this.attackCountDown = attackCountDown;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.healthValue = healthValue;
        this.skill = skill;
        this.x = x;
        this.y = y;

        this.haveSkill = true;
    }

    public Enemy(BufferedImage enemyImage, Attribute attribute,
                 int attackCountDown, int attackPower, int defencePower,
                 int healthValue, int x, int y) {
        this.enemyImage = enemyImage;
        this.attribute = attribute;
        this.attackCountDown = attackCountDown;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.healthValue = healthValue;
        this.x = x;
        this.y = y;

        this.haveSkill = false;
    }

    public void paint(Graphics g) {
        g.drawImage(enemyImage, x, y, null);
    }

    public void beAttacked(int damage) {
        int finalDamage = (damage >= defencePower) ? (damage - defencePower) : 1;
        healthValue -= finalDamage;
        isSurvive = healthValue > 0;
    }

}
