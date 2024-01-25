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

    public boolean move(double dx, double dy, Dungeon dungeon){
        boolean isPossible = true;
        this.getBox().move(dx, dy);
        if (this.getBox().getY() < 0 || this.getBox().getX() < 0)
            isPossible = false;
        for (Things t : dungeon.getListOfThings()){
            if (t instanceof SolidThings) {
                if (((SolidThings) t).getBox().intersect(this.getBox()))
                {

                    isPossible = false;
                    break ;
                }
            }
        }
        if (isPossible)
        {
            this.x += dx;
            this.y += dy;
        }
        else
        {
            this.getBox().move(-dx, -dy);
        }
        return (isPossible);
    }
}
