package GameKernel.utils.controllers;

import GameKernel.utils.core.CommandSolver.KeyListener;
import GameKernel.utils.core.CommandSolver.MouseCommandListener;
import GameKernel.utils.core.Scene;

import java.awt.*;

public class SceneController {
    
    private static SceneController sceneController;
    
    private SceneController(){
    }
    
    public static SceneController instance(){
        if(sceneController == null){
            sceneController = new SceneController();
        }
        return sceneController;
    }
    
    private Scene lastScene;
    private Scene currentScene;
    private MouseCommandListener ml;
    private KeyListener kl;
    
    public void change (Scene scene){
        lastScene = currentScene;
        if(scene != null){
            scene.sceneBegin();
        }
        
        
        ml = scene.mouseListener();
        kl = scene.keyListener();
        currentScene = scene;
    }
    
    public void paint(Graphics g){
        if(currentScene != null){
            currentScene.paint(g);
        }
    }
    
    public void update(){
        if(lastScene != null){
            lastScene.sceneEnd();
            lastScene = null;
        }
        if(currentScene != null){
            currentScene.update();
        }
    }
    public MouseCommandListener mouseListener(){
        return ml;
    }
    
    public KeyListener keyListener(){
        return kl;
    }
}
