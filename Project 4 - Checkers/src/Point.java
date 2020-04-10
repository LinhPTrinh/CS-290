public class Point implements Comparable<Point>{
    private int x;
    private int y;

    Point() {
        this.x = 0;
        this.y = 0;
    }

    public void setX (int x) { this.x = x; }

    public void setY (int y) { this.y = y; }

    public int getX() { return x; }

    public int getY() { return y; }

    @Override
    public int compareTo(Point point) {
        if (this.x == point.x && this.y == point.y)
            return 1;
        else
            return 0;
    }

}
