package GameObject;

import java.awt.*;

public class MainCharacter {
    public Image character_Image;
    public Attribute attribute;
    int attackPower;
    int recoverValue;
    public int healthValue;
    int skillCountdown;

    public int nowAttack = 0;
    public int nowRecover = 0;
    public int x;
    public int y;

    public MainCharacter(Image character_Image, Attribute attribute,
                         int attackPower, int recoverValue, int healthValue,
                         int skillCountdown, int x, int y) {
        this.character_Image = character_Image;
        this.attribute = attribute;
        this.attackPower = attackPower;
        this.recoverValue = recoverValue;
        this.healthValue = healthValue;
        this.skillCountdown = skillCountdown;
        this.x = x;
        this.y = y;
    }

    /**
     * 計算攻擊
     *
     * @param numBall       相同屬性珠消除的個數
     * @param ComboAdditive Combo造成的加成量
     */
    public void calculateAttack(int numBall, double ComboAdditive) {
        nowAttack += numBall * attackPower * (1 + ComboAdditive);
    }

    /**
     * 計算回復量
     *
     * @param numBal        心珠消除的個數
     * @param ComboAdditive Combo造成的加成量
     */
    public void calculateRecover(int numBal, double ComboAdditive) {
        nowRecover += numBal * recoverValue * (1 + ComboAdditive);
    }
}
