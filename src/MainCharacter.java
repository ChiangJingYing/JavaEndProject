import java.awt.*;
import java.awt.image.BufferedImage;

public class MainCharacter {
    BufferedImage character_Image;
    Attribute attribute;
    int attackPower;
    int recoverValue;
    int healthValue;
    int skillCountdown;
    int x;
    int y;

    public MainCharacter(BufferedImage character_Image, Attribute attribute,
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

    public void paint(Graphics g){
        g.drawImage(character_Image,x,y,null);
    }
}
