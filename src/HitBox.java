public class HitBox {
    protected double height;
    protected double width;
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

        double xmin = this.x * 32;
        double xmax = this.x * 32 + this.width;
        double ymin = this.y * 32;
        double ymax = this.y * 32 + this.height;
        double xmin2 = B.x * 32 + 1;
        double xmax2 = B.x * 32+ B.width - 1;
        double ymin2 = B.y * 32 + 1;
        double ymax2 = B.y * 32 + B.height - 1;

        return xmin < xmax2 && xmax > xmin2 && ymin < ymax2 && ymax > ymin2;
    }

    public void move(double dX, double dY) {
        this.x += dX;
        this.y += dY;
    }
}
