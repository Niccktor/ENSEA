import java.awt.*;

public class SolidThings extends Things {

    protected HitBox box;
    public SolidThings(int height, int width, int x, int y) {
        super(height, width, x, y);
        this.box = new HitBox(height, width, "", x, y);
    }
    public SolidThings(int x, int y, Image image)
    {
        super(x, y, image);
        this.box = new HitBox(image.getHeight(null), image.getWidth(null), "", x, y);
    }
    public HitBox getBox() {
        return box;
    }
}