import java.awt.*;

public class SolidThings extends Things {

    public SolidThings(int height, int width, double x, double y) {
        super(height, width, x, y);
        //this.box = new HitBox(height, width, "", x, y);
    }
    public SolidThings(double x, double y, Image image)
    {
        super(x, y, image);
        this.box = new HitBox(image.getHeight(null), image.getWidth(null), "", x, y);
    }

}