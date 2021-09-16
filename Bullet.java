package balloonstowerdefence;

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
    public void advance() {
        x += target.getX() - x;
        y += target.getY() - y;
    }
}