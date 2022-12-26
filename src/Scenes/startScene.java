package Scenes;

import GameKernel.utils.Global;
import GameKernel.utils.controllers.AudioResourceController;
import GameKernel.utils.controllers.ImageController;
import GameKernel.utils.controllers.SceneController;
import GameKernel.utils.core.CommandSolver;
import GameKernel.utils.core.Scene;
import GameObject.Setting.GlobalParameter;

import javax.swing.*;
import java.awt.*;

public class startScene extends Scene {

    public  Image Start_Image;


    @Override
    public void sceneBegin() {
        Start_Image= ImageController.instance().tryGetImage("../../../Start_Image.jpg");
        AudioResourceController.getInstance().loop("../../../Audio/mainBGM.wav", Integer.MAX_VALUE);
    }

    @Override
    public void sceneEnd() {
        AudioResourceController.getInstance().stop("../../../Audio/mainBGM.wav");
    }

    @Override
    public void paint(Graphics g) {

    g.drawImage(Start_Image, GlobalParameter.SCREEN_WIDTH/2-270,0,null);

//        g.drawImage(Start_Image,0,0,900,1000,null);
    }

    @Override
    public void update() {

    }

    @Override
    public CommandSolver.MouseCommandListener mouseListener() {
        return (e, state, trigTime) -> {
            if (state== CommandSolver.MouseState.PRESSED){
                SceneController.instance().change(new GameScene());
            }
        };
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return null;
    }
}
