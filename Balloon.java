package balloonstowerdefence;

public class Balloon {
    private int health = 8;
    private int x;
    private int y;
    public Balloon(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void advance() {
        if (x < 13*20) {
            x ++;
        }
        else if (x == 13*20 && y > 6*20) {
            y--;
        }
        else if (x >= 13*20 && y == 6*20 && x<17*20) {
            x++;
        }
        else if (x == 17*20 && y < 14*20) {
            y++;
        }
        else if (x >= 17*20 && y == 14*20 && x < 21*20) {
            x++;
        }
        else if (x == 21*20 && y > 10*20) {
            y--;
        }
        else if (x >= 21*20 && y == 10*20) {
            x++;
        }
    }
    public void damage(int damage) {
        health -= damage;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getHealth() {
        return health;
    }
}
