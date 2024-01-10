public class HitBox {
    private double height;
    private double width;
    private String name;
    private double x;
    private double y;

    public HitBox(double height, double width, String name, double x, double y) {
        this.height = height;
        this.width = width;
        this.name = name;
        this.x = x;
        this.y = y;
    }
    public double getY() {
        return y;
    }
    public double getX() {
        return x;
    }
    public boolean intersect(HitBox B) {
        if (this.y > B.y || (this.y == B.y && this.x > B.x)) {
            return (B.intersect(this));
        }
        else
            return (this.y + this.height > B.y && this.x + this.width > B.x);
    }
    public void move(double dX, double dY)
    {
        this.x += dX;
        this.y += dY;
    }
}
