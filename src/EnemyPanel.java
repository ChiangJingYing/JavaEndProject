import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Random;

public class EnemyPanel extends JPanel implements ActionListener {
    Timer timer; // 不知道要不要
    Random random;

    int levelCount;
    ArrayList<ArrayList<Enemy>> enemies;

    public EnemyPanel(int levelCount) {
        this.levelCount = levelCount;
        timer = new Timer(300, this);
        random = new Random();

        enemies = new ArrayList<>(levelCount);

        //TODO
        // init level enemies


        //
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    class MyMouseAdapter extends MouseAdapter {
        //
    }
}
