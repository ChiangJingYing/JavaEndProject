package GameKernel.utils.gameobjects;

import java.awt.*;

public class Rect {
    private double x;
    private double y;
    private double width;
    private double height;
    
    public Rect(double x, double y, double width, double height){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }
    
    public final Rect translate(double x, double y){
        this.x += x;
        this.y += y;
        return this;
    }
    public final Rect translateX(double x){
        this.x += x;
        return this;
    }
    public final Rect translateY(double y){
        this.y += y;
        return this;
    }
    
    public final Rect scale(double width, double height){
        this.width = width;
        this.height = height;
        return this;
    }
    public final Rect scaleX(double width){
        this.width = width;
        return this;
    }
    public final Rect scaleY(double height){
        this.height = height;
        return this;
    }
    
    public final double centerX(){
        return x + width /2;
    }
    public final double centerY(){
        return y + height /2;
    }
    
    public final void setCenter(double x, double y){
        this.x = x - width / 2;
        this.y = y - height / 2;
    }
    public final void set(double x, double y){
        this.x = x;
        this.y = y;
    }
    public final void setX(double x){
        this.x = x;
    }
    public final void setY( double y){
        this.y = y;
    }
    
    public final void setWidth(double width){
        this.width=width;
    }
    public final void setHeight(double height){
        this.height=height;
    }
    
    public double width(){
        return width;
    }
    public double height(){
        return height;
    }
    
    public double left(){
        return x;
    }
    public double right(){
        return x + width;
    }
    public double top(){
        return y;
    }
    public double bottom(){
        return y + height;
    }
    
    public boolean overlap(Rect obj){
        if(right() < obj.left()){
            return false;
        }
        if(left() > obj.right()){
            return false;
        }
        if(top() > obj.bottom()){
            return false;
        }
        if(bottom() < obj.top()){
            return false;
        }
        return true;
    }
    
    public final Rect clone(){
        return new Rect(left(), right(),width(), height());
    }
    
    public void paint(Graphics g) {
        g.fillRect((int)x, (int)y, (int)width, (int)height);
    }
    
    
}
