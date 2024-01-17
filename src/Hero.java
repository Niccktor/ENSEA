import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public final class Hero extends DynamicThings {
    private static Hero instance = null;

    private Hero() {
        super(0,0,1,1);
    }
    private Hero(Image img)
    {
        super(1,1, img);
    }

    public final static Hero getInstance() {
        if (Hero.instance == null) {
            synchronized (Hero.class) {
                if (Hero.instance == null) {
                   try {
                       Hero.instance = new Hero(ImageIO.read(new File("./hero.png")));
                   } catch(Exception e){
                       e.printStackTrace();
                   }
                }
            }
        }
        return Hero.instance;
    }
    public void move(double dx, double dy, Dungeon dungeon){
        boolean isPossible = true;
        this.getBox().move(dx, dy);
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
            this.getBox().move(-dx, -dy);
    }
}
