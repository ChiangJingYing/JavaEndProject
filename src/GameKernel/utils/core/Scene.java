package GameKernel.utils.core;

import GameKernel.utils.core.CommandSolver.KeyListener;
import GameKernel.utils.core.CommandSolver.MouseCommandListener;

import java.awt.*;

public abstract class Scene {
    
    public abstract void sceneBegin();// 在建構子之後呼叫，如果沒有複雜的應用場景可以忽略
    
    public abstract void sceneEnd();// 當場景結束時可以釋放不需要的內容，沒有複雜的應用場景可以忽略
    
    public abstract void paint(Graphics g);// 這個場景的每一幀要畫的內容
    
    public abstract void update();// 這個場景的每一幀要更新的內容
    
    public abstract MouseCommandListener mouseListener();// 這個場景的滑鼠事件要做什麼
    
    public abstract KeyListener keyListener();// 這個場景的鍵盤事件要做什麼
}
