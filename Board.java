package balloonstowerdefence;

public class Board {
    private int[][] grid = new int[40][20];
    public Board() {
        for (int a=0; a<14; a++) {
            grid[a][10] = 1;
        }
        for (int b=6; b<11; b++) {
            grid[13][b] = 1;
        }
        for (int c=14; c<18; c++) {
            grid[c][6] = 1;
        }
        for (int d=7; d<15; d++) {
            grid[17][d] = 1;
        }
        for (int e=18; e<22; e++) {
            grid[e][14] = 1;
        }
        for (int f=14; f>9; f--) {
            grid[21][f] = 1;
        }
        for (int g=22; g<40; g++) {
            grid[g][10] = 1;
        }
    }
    public void changeTile(int x, int y, int tile) {
        grid[x][y] = tile;
    }
    public int getTile(int x, int y) {
        return grid[x][y];
    }
}