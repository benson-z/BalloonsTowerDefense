import java.util.*;
import java.io.*;
import java.lang.Math;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import balloonstowerdefence.*;

public class BalloonsTowerDefense {
    public static void main(String args[]) {
        Board game = new Board();
        new BalloonsTowerDefense(game);
    }
    public BalloonsTowerDefense(Board game) {
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
                TestPane display = new TestPane(game);
                frame.add(display);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                display.addBalloon(new Balloon(-20, 200));
                display.addBalloon(new Balloon(-80, 200));
                display.addBalloon(new Balloon(-140, 200));
                display.addBalloon(new Balloon(-200, 200));
                display.addBadTower(new BadTower(5, 8));
                display.addBadTower(new BadTower(10, 12));
                display.addBadTower(new BadTower(15, 8));
                display.addBadTower(new BadTower(19, 12));
            }
        });
    }
}