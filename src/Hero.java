import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;


public final class Hero extends DynamicThings {
    private static Hero instance = null;
    public double angle = 0;

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public double speedY;
    public double speedX;
    public double max_speedY;
    public double max_speedX;

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
    private Hero(String file)
    {
        super(1,1, new TileManager(32, 32, file), 40000000);
        this.speedY = 0;
        this.speedX = 0;
    }

    public final static Hero getInstance() {
        if (Hero.instance == null) {
            synchronized (Hero.class) {
                if (Hero.instance == null) {
                   try {
                       Hero.instance = new Hero("hero_a.png");
                   } catch(Exception e){
                       e.printStackTrace();
                   }
                }
            }
        }
        return Hero.instance;
    }

    public void acceleration(int code)
    {
        switch (code) {
            case 1: // gauche
                this.max_speedX = -0.3;
                break;
            case 2: // Droite
                this.max_speedX =  0.3;
                break;
            case 3: // Bas
                this.max_speedY = 0.3;
                break;
            case 4: // Haut
                this.max_speedY= -0.3;
                break;
            case 5:
                this.max_speedX = 0;
                break;
            case 6:
                this.max_speedY = 0;
                break;
        }
    }

    public void test()
    {
        if (this.max_speedY == 0) {
            if (this.speedY != 0) {
                if (0 < this.speedY)
                    this.speedY -= 0.01;
                else
                    this.speedY += 0.01;
                this.speedY = Math.floor(this.speedY * 100) / 100;
            }
        }
        else if (max_speedY > this.speedY) {
            this.speedY+=0.005;
        }
        else {
            this.speedY-=0.005;
        }
        if (this.max_speedX == 0) {
            if (this.speedX != 0) {
                if (0 < this.speedX)
                    this.speedX -= 0.01;
                else
                    this.speedX += 0.01;
                this.speedX = Math.floor(this.speedX * 100) / 100;

            }
        }
        else if (max_speedX > this.speedX) {
            this.speedX+=0.005;
        }
        else {
            this.speedX-=0.005;
        }
        System.out.format("SpeedY : %.3f SpeedX : %.3f\nMaxSpeedY = %.3f MaxSpeedX = %.3f\n", this.speedY, this.speedX, this.max_speedY, this.max_speedX);

    }
}
