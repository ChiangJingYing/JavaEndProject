package GameKernel.utils.controllers;

import GameKernel.utils.Global;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class ImageController {

    private static ImageController Irc;

    public static ImageController instance() {
        if (Irc == null) {
            Irc = new ImageController();
        }
        return Irc;
    }

    //內容
    private final HashMap<String, Image> imgPairs;
    
    private ImageController() {
        imgPairs = new HashMap<>();
    }
    
    public Image tryGetImage(String path) {
        if (!imgPairs.containsKey(path)) {
            return addImage(path);
        }
        return imgPairs.get(path);
    }
    
    private Image addImage(String path) {
        try {
            if (Global.IS_DEBUG) {
                System.out.println("圖片來自:" + path);
            }
            Image img = ImageIO.read(getClass().getResource(path));
            imgPairs.put(path, img);
            return img;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public void clear() {
        imgPairs.clear();
    }
}
