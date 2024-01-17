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

        double xmin = this.x * this.width;
        double xmax = this.x * this.width + this.width;
        double ymin = this.y * this.height;
        double ymax = this.y * this.height + this.height;
        double xmin2 = B.x * B.width + 1;
        double xmax2 = B.x * B.width + B.width - 1;
        double ymin2 = B.y * B.height + 1;
        double ymax2 = B.y * B.height + B.height - 1;

        return xmin < xmax2 && xmax > xmin2 && ymin < ymax2 && ymax > ymin2;
    }

    public void move(double dX, double dY) {
        this.x += dX;
        this.y += dY;
    }
}
