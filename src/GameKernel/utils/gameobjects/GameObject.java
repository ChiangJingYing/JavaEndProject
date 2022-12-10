package GameKernel.utils.gameobjects;

import GameKernel.utils.Global;
import GameKernel.utils.core.GameKernel.GameInterface;

import java.awt.*;

public abstract class GameObject implements GameInterface{
    
    private final Rect collider;
    private final Rect painter;
    
    public GameObject(double x, double y, double width, double height){
        collider = new Rect(x, y, width, height);
        painter = new Rect(x, y, width, height);
    }
    
    public GameObject(Rect rect){
        collider = rect.clone();
        painter = rect.clone();  
    }
    
    public GameObject(double x, double y, double width, double height, double x2, double y2, double width2, double height2){
        collider = new Rect(x, y, width, height);
        painter = new Rect(x2, y2, width2, height2);
        painter.setCenter(collider.centerX(), collider.centerY());
    }
    
    public GameObject(Rect rect, Rect rect2){
        collider = rect.clone();
        painter = rect2.clone();
        painter.setCenter(rect.centerX(), rect.centerY());
    }
    
    public double getX(){
        return collider.centerX();
    }
    public double getY(){
        return collider.centerY();
    }
    
    public boolean touchTop(){
        return collider.top() <= Global.BORDER_TOP;
    }
    public boolean touchLeft(){
        return collider.left() <= Global.BORDER_LEFT;
    }
    public boolean touchRight(){
        return collider.right() >= Global.BORDER_RIGHT;
    }
    public boolean touchBottom(){
        return collider.bottom() >= Global.BORDER_BOTTOM;
    }
    
    public boolean outOfScreen(){
        return touchLeft() || touchRight() || touchTop() || touchBottom();
    }
    
    public boolean isCollision(GameObject obj){
        return collider.overlap(obj.collider);
    }
    
    public final void translate(double x, double y){
        collider.translate(x, y);
        painter.translate(x, y);
    }
    public final void translateX(double x){
        collider.translateX(x);
        painter.translateX(x);
    }
    public final void translateY(double y){
        collider.translateY(y);
        painter.translateY(y);
    }
    
    public final Rect collider(){
        return collider;
    }
    
    public final Rect painter(){
        return painter;
    }
    
    @Override
    public void paint(Graphics g) {
        paintComponent(g);
        if(Global.IS_DEBUG){
            g.setColor(Color.red);
            collider.paint(g);
            g.setColor(Color.green);
            painter.paint(g);
            g.setColor(Color.black);
        }
    }
    
    public abstract void paintComponent(Graphics g);
    
}
