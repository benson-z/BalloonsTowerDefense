package balloonstowerdefence;

class Tower {
    private int x;
    private int y;
    private int damage;
    public Tower(int x, int y, int damage) {
        this.x = x;
        this.y = y;
        this.damage = damage;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void attack(Balloon target) {
        target.damage(damage);
    }
}