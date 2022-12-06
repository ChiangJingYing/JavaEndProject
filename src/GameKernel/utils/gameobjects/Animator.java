/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameKernel.utils.gameobjects;

import GameKernel.utils.Delay;
import GameKernel.utils.controllers.ImageController;

import java.awt.*;

/**
 *
 * @author user1
 */
public class Animator {

    private Image img;//連續圖片
    private int count;//目前張數
    private final Delay delay;//控制切換速度
    
    int width;
    int height;
    int[] actions;
    
    public Animator(String imgPath, int delayFrame, int width, int height, int[] actions){
        count = 0;
        img = ImageController.instance().tryGetImage(imgPath);
        delay = new Delay(delayFrame);
        delay.loop();
        
        this.width = width;
        this.height = height;
        this.actions = actions;
    }
    
    public void paint(int left, int top, Graphics g) {
        if (count < actions.length) {
            g.drawImage(img,
                    left, top,
                    left + width, top + height,
                    width * actions[count], 0,
                    width + width * actions[count], height, null);
        }
    }

    public void update() {
        if (delay.count()) {
            count = ++count % actions.length;
        }
    }

}
