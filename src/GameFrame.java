import GameKernel.utils.core.GIImpl;
import GameKernel.utils.core.GameKernel;
import Scenes.GameScene;

import javax.swing.*;
import java.awt.*;

import static GameObject.GlobalParameter.FPS;
import static GameObject.GlobalParameter.UPS;

public class GameFrame extends JFrame {


    public GameFrame(String title, int width, int height) {
        super(title);

        this.setPreferredSize(new Dimension(width, height));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GIImpl gi = new GIImpl(new GameScene());


        GameKernel gk = new GameKernel.Builder(gi, FPS, UPS)
                .initListener()
                .enableMouseTrack(gi)
                .mouseForceRelease()
                .gen();

        this.add(gk);
        this.pack();
        this.setVisible(true);
        gk.run(true);
    }
}
