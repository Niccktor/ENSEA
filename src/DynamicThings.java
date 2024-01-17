import java.awt.*;

public class DynamicThings extends AnimatedThings{

    public DynamicThings(int height, int width, double x, double y) {
        super(height, width, x, y);
    }

    public DynamicThings(double x, double y, Image img) {
        super( x, y, img);
    }

    public void move(double dX, double dY) {
        this.x += dX;
        this.y += dY;
    }
}
