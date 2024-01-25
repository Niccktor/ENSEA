import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

/*
*
*/

public final class Hero extends DynamicThings {
    private static Hero instance = null;
    private double speedX;
    private TileManager tile;
    public double angle = 0;
    /*
     *  IDDLE   = 0;
     *  Gauche  = 1;
     *  Droite  = 2;
     *  Haut    = 3;
     *  Bas     = 4;
     */

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    private double speedY;

    public void setImg(int i)
    {
        this.img = this.tile.getTile(0,i,this.angle);
    }
    private Hero() {
        super(32,32,1,1);
        this.speedX = 0;
        this.speedY = 0;
    }
    private Hero(Image img)
    {
        super(1,1, img);
        this.tile = new TileManager(32, 32, "hero_a.png");
        this.speedY = 0;
        this.speedX = 0;
    }

    public final static Hero getInstance() {
        if (Hero.instance == null) {
            synchronized (Hero.class) {
                if (Hero.instance == null) {
                   try {
                       Hero.instance = new Hero(ImageIO.read(new File("./hero_a.png")).getSubimage(0,0,32,32));
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
            this.getBox().move(-dx, -dy);
    }
    public void move(Dungeon dungeon)
    {
        boolean isPossible = true;
        this.getBox().move(this.speedX, this.speedY);
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
            this.x += this.speedX;
            this.y += this.speedY;
        }
        else
            this.getBox().move(-this.speedX, -this.speedY);

    }
    public void acceleration(int code)
    {
        switch (code) {
            case 1: // gauche
                if (this.speedX >= 0)
                    this.speedX = -0.1;
                this.speedX *= 1.4;
                break;
            case 2: // Droite
                if (this.speedX <= 0)
                    this.speedX = 0.1;
                this.speedX *= 2;
                break;
            case 3: // Bas
                if (this.speedY <= 0)
                    this.speedY = 0.1;
                this.speedY *= 2;
                break;
            case 4: // Haut
                if (this.speedY <= 0)
                    this.speedY = -0.1;
                this.speedY *= 1.4;
                break;
            case 5:
                this.speedX = 0;
                break;
            case 6:
                this.speedY = 0;
                break;
        }
        System.out.format("SpeedY : %f.2 SpeedX : %f.2\n", this.speedY, this.speedX);


    }
}
