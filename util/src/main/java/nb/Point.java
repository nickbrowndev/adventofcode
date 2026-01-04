
package nb;

public record Point(int x,int y) {
    public Point move(Direction direction) {
        return new Point(this.x + direction.getX(), this.y + direction.getY());
    }
}
