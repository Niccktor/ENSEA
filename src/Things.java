import java.awt.*;

public class Things {
    protected int height;
    protected int width;
    protected double x;
    protected double y;
    protected Image img;
    protected HitBox box;


    public Things(double x, double y, Image img)
    {
        this.x = x;
        this.y = y;
        this.img = img;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.box = new HitBox(height, width, "", x, y);
    }
    public Things(int height, int width, double x, double y) {
        this.height = 0;
        this.width = 0;
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics graphics) {
        //System.out.format("X : %.2f Y : %.2f\n", this.x, this.y);
        graphics.drawImage(this.img , (int)(this.x * 32), (int)(this.y * 32) + 32, null);
    }
    public HitBox getBox() {
        return box;
    }
    public void setImg(Image img) {
        this.img = img;
    }
}