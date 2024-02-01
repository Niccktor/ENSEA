import java.awt.*;

public class DynamicThings extends AnimatedThings{

    public DynamicThings(int height, int width, double x, double y) {
        super(height, width, x, y);
    }

    public DynamicThings(double x, double y, Image img) {
        super( x, y, img);
    }
    public DynamicThings(double x, double y, TileManager tile)
    {
        super( x, y, tile);
    }
    public DynamicThings(double x, double y, TileManager tile, long delay)
    {
        super( x, y, tile, delay);
    }

    public void move(double dX, double dY) {
        this.x += dX;
        this.y += dY;
    }

    public int move(double dx, double dy, Dungeon dungeon){
        int isPossible = 1;
        this.getBox().move(dx, dy);
        if (this.getBox().getY() < 0 || this.getBox().getX() < 0)
            isPossible = 0;
        for (Things t : dungeon.getListOfThings()){
            if (t instanceof SolidThings) {
                if ((t instanceof Explosion) && ((SolidThings) t).getBox().intersect(this.getBox()))
                {
                    System.out.format("Fin du jeu !\n");
                    return (2);
                }
                if (!(t instanceof Bomb))
                {
                    if (((SolidThings) t).getBox().intersect(this.getBox()))
                    {
                        isPossible = 0;
                        break ;
                    }
                }
            }
        }
        if (isPossible == 1)
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
