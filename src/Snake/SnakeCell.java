package Snake;


public class SnakeCell extends HexCeil {

    private int x;

    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public SnakeCell(int x, int y, int globalX, int globalY) {
        super(globalX, globalY);
        setX(x);
        setY(y);
    }
}
