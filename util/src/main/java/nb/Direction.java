
package nb;

public enum Direction {
    N(0, 1), NE(1,1), E(1,0), SE(1,-1),S(-1,0),SW(-1,-1),W(-1,0),NW(-1,1);

    private Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private final int x;
    private final int y;

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
