package balloonstowerdefense;

import java.lang.Math;

public class Bullet {
    private int damage;
    private int x;
    private int y;
    private Balloon target;
    public Bullet(int x, int y, int damage, Balloon target) {
        this.x = x*20;
        this.y = y*20;
        this.damage = damage;
        this.target = target;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void advance() {
        if (target.getX() > x) {
            x += 3;
        }
        else if (target.getX() < x) {
            x -= 3;
        }
        if (target.getY() > y) {
            y += 3;
        }
        else if (target.getY() < y) {
            y -= 3;
        }
    }
    public int hit() {
        if (Math.abs(x-target.getX()) < 16 && Math.abs(y-target.getY()) < 16) {
            target.damage(damage);
            return 1;
        }
        else {
            return 0;
        }
    }
}