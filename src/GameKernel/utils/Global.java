/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameKernel.utils;

/**
 *
 * @author user1
 */
public class Global {
    //視窗大小
    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 960;
    public static final int SCREEN_X = WINDOW_WIDTH - 8 - 8;// 左右邊的邊界內建8
    public static final int SCREEN_Y = WINDOW_HEIGHT - 31 - 8;// 上面的bar佔28
    //遊戲世界大小
    public static final int BORDER_LEFT = 0;
    public static final int BORDER_TOP = 0;
    public static final int BORDER_RIGHT = 2560;
    public static final int BORDER_BOTTOM = 1920;
    
    //debug
    public static final boolean IS_DEBUG = true;
    
    //資料刷新
    public static final int UPDATE_TIMES_PER_SEC = 60;// 每秒更新60次遊戲邏輯
    //畫面更新
    public static final int FRAME_LIMIT = 60;// 60FPS
    
    //隨機快捷方法
    public static int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

}
