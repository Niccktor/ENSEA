import java.awt.*;

public class AnimatedThings extends SolidThings {

    private TileManager tile;

    public AnimatedThings(int height, int width, double x, double y) {
        super(height, width, x, y);
    }

    public AnimatedThings(double x, double y, Image image) {
        super(x, y, image);
    }
}
