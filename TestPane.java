package balloonstowerdefence;

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

public class TestPane extends JPanel {
    private int x = 0;
    private int y = 0;
    private Vector<Balloon> balloons = new Vector<Balloon>();
    private Vector<BadTower> badtowers = new Vector<BadTower>();
    private Vector<BetterTower> bettertowers = new Vector<BetterTower>();
    private Board game;
    private int tickcounter=0;
    public TestPane(Board game) {
        this.game = game;
        Timer timer = new Timer(15, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
                clearMem();
                tickcounter ++;
                if (tickcounter % 40 == 0) {
                    setTarget();
                }
            }
        });
        timer.start();
    }
    public void addBalloon(Balloon balloon) {
        balloons.add(balloon);
    }
    public void addBadTower(BadTower badtower) {
        badtowers.add(badtower);
    }
    public void addBetterTower(BetterTower bettertower) {
        bettertowers.add(bettertower);
    }
    public void update() {
        for (Balloon a : balloons) {
            a.advance();
        }
    }
    public void clearMem() {
        for (int a=0; a<balloons.size(); a++) {
            if (balloons.get(a).getX() > 800) {
                balloons.remove(a);
                balloons.add(new Balloon(-20, 200));
            }
            else if (balloons.get(a).getHealth() <= 0) {
                balloons.remove(a);
                balloons.add(new Balloon(-20, 200));
            }
        }
    }
    public void setTarget() {
        for (BadTower a : badtowers) {
            for (Balloon b : balloons) {
                if (Math.abs(a.getX()*20-b.getX()) < 60 && Math.abs(a.getY()*20-b.getY()) < 60) {
                    a.attack(b);
                    break;
                }
            }
        }
        for (BetterTower a : bettertowers) {
            for (Balloon b : balloons) {
                if (Math.abs(a.getX()*20-b.getX()) < 60 && Math.abs(a.getY()*20-b.getY()) < 60) {
                    a.attack(b);
                    break;
                }
            }
        }
    }
    public Dimension getPreferredSize() {
        return new Dimension(800, 400);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        for (int y=0; y<20; y++) {
            for (int x=0; x<40; x++) {
                if (game.getTile(x, y) == 0) {
                    g2d.setColor(Color.GREEN);
                    g2d.fillRect(x*20, y*20, 20, 20);
                }
                else if (game.getTile(x, y) == 1) {
                    g2d.setColor(Color.ORANGE);
                    g2d.fillRect(x*20, y*20, 20, 20);
                }
            }
        }
        for (BadTower badtower : badtowers) {
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(badtower.getX()*20, badtower.getY()*20, 20, 20);
        }
        for (BetterTower bettertower : bettertowers) {
            g2d.setColor(Color.GRAY);
            g2d.fillRect(bettertower.getX()*20, bettertower.getY()*20, 20, 20);
        }
        g2d.setColor(Color.RED);
        for (Balloon a : balloons) {
            g2d.setColor(Color.RED);
            g2d.fillOval(a.getX()+2, a.getY()+2, 16, 16);
            g2d.fillRect(a.getX()+2, a.getY()-5, 16, 4);
            g2d.setColor(Color.BLUE);
            g2d.fillRect(a.getX()+2, a.getY()-5, a.getHealth()*2, 4);
        }
        g2d.dispose();
    }
}