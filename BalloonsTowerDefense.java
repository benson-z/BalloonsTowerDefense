import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import balloonstowerdefense.*;

public class BalloonsTowerDefense {
    public static void main(String args[]) {
        Board game = new Board();
        new BalloonsTowerDefense(game, 750);
    }
    public BalloonsTowerDefense(Board game, int money) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                JFrame frame = new JFrame("Balloons Tower Defense");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                TestPane display = new TestPane(game, money);
                frame.add(display);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                display.addBalloon(new Balloon(-20, 200));
                display.addBalloon(new Balloon(-80, 200));
                display.addBalloon(new Balloon(-140, 200));
                display.addBalloon(new Balloon(-200, 200));
            }
        });
    }
}
