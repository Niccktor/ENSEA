import java.awt.*;

public class Things {
    protected int height;
    protected int width;
    protected double x;
    protected double y;
    protected Image img;

    public Things(int x, int y, Image img)
    {
        this.x = x;
        this .y = y;
        this.img = img;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }
    public Things(int height, int width, int x, int y) {
        this.height = 0;
        this.width = 0;
        this.x = x;
        this.y = y;
    }
}