package balloonstowerdefense;

import java.util.*;
import java.lang.Math;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.*;

public class TestPane extends JPanel {
    private int money[] = new int[1];
    private int towerselection[] = new int[1];
    private int enemiesPassed[] = new int[1];
    private int enemiesKilled[] = new int[1];
    private Vector<Balloon> balloons = new Vector<Balloon>();
    private Vector<BadTower> badtowers = new Vector<BadTower>();
    private Vector<BetterTower> bettertowers = new Vector<BetterTower>();
    private Vector<Bullet> bullets = new Vector<Bullet>();
    private Board game;
    private BufferedImage balloonimg = null;
    private BufferedImage badtowerimg = null;
    private BufferedImage bettertowerimg = null;
    private BufferedImage grassimg = null;
    private BufferedImage pathimg = null;
    private int tickcounter=0;
    public TestPane(Board game, int mon) {
        this.game = game;
        this.money[0] = mon;
        try {
            this.balloonimg = ImageIO.read(new File("balloon.png"));
            this.badtowerimg = ImageIO.read(new File("BadTower.png"));
            this.bettertowerimg = ImageIO.read(new File("BetterTower.png"));
            this.grassimg = ImageIO.read(new File("grass.png"));
            this.pathimg = ImageIO.read(new File("path.png"));
        } catch (IOException e) {
        }
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                Point a = new Point(me.getPoint());
                int x = ((int) a.getX() - ((int) a.getX()%20))/20;
                int y = ((int) a.getY() - ((int) a.getY()%20))/20;
                if (x >= 31 && y >= 19) {
                    if (towerselection[0] == 1) {
                        towerselection[0] = 0;
                    }
                    else {
                        towerselection[0] = 1;
                    }
                }
                else {
                    if (towerselection[0] == 0 && money[0] >= 250 && game.getTile(x, y) != 1 && game.getTile(x-1, y) != 1 && game.getTile(x+1, y) != 1 && game.getTile(x, y-1) != 1 && game.getTile(x, y+1) != 1) {
                        money[0] -= 250;
                        badtowers.add(new BadTower(x, y));
                    }
                    else if (money[0] >= 500 && game.getTile(x, y) != 1 && game.getTile(x-1, y) != 1 && game.getTile(x+1, y) != 1 && game.getTile(x, y-1) != 1 && game.getTile(x, y+1) != 1) {
                        money[0] -= 500;
                        bettertowers.add(new BetterTower(x, y));
                    }
                }
            }
        });
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
        for (Bullet b : bullets) {
            b.advance();
        }
    }
    public void clearMem() {
        for (int a=0; a<balloons.size(); a++) {
            if (balloons.get(a).getX() > 800) {
                balloons.remove(a);
                balloons.add(new Balloon(-20, 200));
                enemiesPassed[0] ++;
            }
            else if (balloons.get(a).getHealth() <= 0) {
                balloons.remove(a);
                balloons.add(new Balloon(-20, 200));
                enemiesKilled[0] ++;
            }
        }
        for (int b=0; b<bullets.size(); b++) {
            if (bullets.get(b).hit() == 1) {
                bullets.remove(b);
            }
        }
    }
    public void setTarget() {
        for (BadTower a : badtowers) {
            for (Balloon b : balloons) {
                if (Math.abs(a.getX()*20-b.getX()) < 60 && Math.abs(a.getY()*20-b.getY()) < 60) {
                    bullets.add(new Bullet(a.getX(), a.getY(), 1, b));
                    break;
                }
            }
        }
        for (BetterTower a : bettertowers) {
            for (Balloon b : balloons) {
                if (Math.abs(a.getX()*20-b.getX()) < 60 && Math.abs(a.getY()*20-b.getY()) < 60) {
                    bullets.add(new Bullet(a.getX(), a.getY(), 2, b));
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
                    //g2d.setColor(Color.GREEN);
                    //g2d.fillRect(x*20, y*20, 20, 20);
                    g2d.drawImage(grassimg, x*20, y*20, null);
                }
                else if (game.getTile(x, y) == 1) {
                    //g2d.setColor(Color.ORANGE);
                    //g2d.fillRect(x*20, y*20, 20, 20);
                    g2d.drawImage(pathimg, x*20, y*20, null);
                }
            }
        }
        for (BadTower badtower : badtowers) {
            //g2d.setColor(Color.LIGHT_GRAY);
            //g2d.fillRect(badtower.getX()*20, badtower.getY()*20, 20, 20);
            g2d.drawImage(badtowerimg, badtower.getX()*20, badtower.getY()*20, null);
        }
        for (BetterTower bettertower : bettertowers) {
            //g2d.setColor(Color.GRAY);
            //g2d.fillRect(bettertower.getX()*20, bettertower.getY()*20, 20, 20);
            g2d.drawImage(bettertowerimg, bettertower.getX()*20, bettertower.getY()*20, null);
        }
        for (Bullet b : bullets) {
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillOval(b.getX()+5, b.getY()+5, 10, 10);
        }
        g2d.setColor(Color.RED);
        for (Balloon a : balloons) {
            g2d.setColor(Color.RED);
            //g2d.fillOval(a.getX()+2, a.getY()+2, 16, 16);
            g2d.drawImage(balloonimg, a.getX()+1, a.getY(), null);
            g2d.fillRect(a.getX()+2, a.getY()-5, 16, 4);
            g2d.setColor(Color.BLUE);
            g2d.fillRect(a.getX()+2, a.getY()-5, a.getHealth()*2, 4);
        }
        g2d.setColor(Color.BLACK);
        g2d.drawString("$ " + String.valueOf(money[0]), 10, 20);
        g2d.drawString("Balloons Killed: " + String.valueOf(enemiesKilled[0]), 10, 40);
        g2d.drawString("Passed Balloons " + String.valueOf(enemiesPassed[0]), 10, 60);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(630, 19*20, 180, 20);
        g2d.setColor(Color.BLACK);
        if (towerselection[0] == 0) {
            g2d.drawString("Switch to Level 2 Tower", 32*20, 20*20-3);
        }
        else {
            g2d.drawString("Switch to Level 1 Tower", 32*20, 20*20-3);
        }
        g2d.dispose();
    }
}