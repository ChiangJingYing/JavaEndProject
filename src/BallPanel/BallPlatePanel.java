package BallPanel;

import GameObject.Attribute;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class BallPlatePanel extends JPanel implements ActionListener {
    ArrayList<JPanel> balls;
    Random random;
    Timer timer;
    JPanel control;

    public BallPlatePanel() {
        random = new Random();
        balls = new ArrayList<>();
        timer = new Timer(1, this);

        this.setBackground(new Color(0x643500));
        this.setMaximumSize(new Dimension(500, 600));
        this.addMouseListener(new MyMouseAdapter());
        this.addMouseMotionListener(new MyMouseAdapter());
        this.setLayout(new GridBagLayout());

        // TODO used to test
        //  should be delete
        allowMove();

        // init ball plate
        Init();
    }

    // Move ball Entrance
    public void allowMove(){
        timer.start();
    }

    public void Init() {
        balls.clear();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                Attribute randomAttribute = Attribute.values()[random.nextInt(6)];
                GridBagConstraints tmpConstrains = new GridBagConstraints();
                tmpConstrains.fill = GridBagConstraints.NONE;
                tmpConstrains.gridx = j;
                tmpConstrains.gridy = i;
                tmpConstrains.insets = new Insets(0, 0, -3, 0); // like padding
                JPanel tmp = new Ball(j, i, randomAttribute);
                balls.add(tmp);
                this.add(tmp, tmpConstrains);
            }
        }
    }

    public void reInit() {
        this.removeAll(); // remove all component on BallPanel.BallPlatePanel
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                GridBagConstraints tmpConstrains = new GridBagConstraints(); // setting gridBagLayout
                tmpConstrains.fill = GridBagConstraints.NONE;
                tmpConstrains.gridx = j;
                tmpConstrains.gridy = i;
                tmpConstrains.insets = new Insets(0, 0, -3, 0); // like padding
                Ball tmp = (Ball) balls.get(i * 5 + j); // resetting ball position
                tmp.x = j;
                tmp.y = i;
                balls.set(i * 5 + j, tmp);
                this.add(tmp, tmpConstrains);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        revalidate(); // reload GridBagLayout
        repaint();
    }

    class MyMouseAdapter extends MouseAdapter {
        int exchangeBallX;
        int exchangeBallY;

        @Override
        public void mouseClicked(MouseEvent e) {
            // used to test ball position
            // TODO Finished should delete it.
            System.out.println(((Ball) (e.getComponent().getComponentAt(e.getX(), e.getY()))).x);
            System.out.println(((Ball) (e.getComponent().getComponentAt(e.getX(), e.getY()))).y);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // after drag reset control ball
            control = null;
            exchangeBallX = -1;
            exchangeBallY = -1;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (control == null) {
                // find which ball is control.
                control = (JPanel) (e.getComponent().getComponentAt(e.getX(), e.getY()));
            } else if (exchangeBallX == -1 && ((Ball) (e.getComponent().getComponentAt(e.getX(), e.getY()))).x != ((Ball) control).x ||
                    ((Ball) (e.getComponent().getComponentAt(e.getX(), e.getY()))).y != ((Ball) control).y) {
                // find which ball will be exchange.
                exchangeBallX = ((Ball) (e.getComponent().getComponentAt(e.getX(), e.getY()))).x;
                exchangeBallY = ((Ball) (e.getComponent().getComponentAt(e.getX(), e.getY()))).y;


                JPanel second = balls.get(exchangeBallY * 5 + exchangeBallX);
                // warp two balls.
                balls.set(((Ball) control).y * 5 + ((Ball) control).x, second);
                balls.set(exchangeBallY * 5 + exchangeBallX, control);

                // reInit BallPanel.BallPlatePanel ( resetting ball position )
                reInit();

                exchangeBallX = -1;
                exchangeBallY = -1;
            }
        }
    }
}
