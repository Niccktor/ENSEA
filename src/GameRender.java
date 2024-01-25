import java.awt.*;

public class GameRender {
    public Dungeon dungeon;
    public Hero hero;
    public int mouseX = 0;
    public int mouseY = 0;

    public GameRender(Dungeon dungeon) {
        this.dungeon = dungeon;
        this.hero = Hero.getInstance();
    }
    static int i = 0;
    public void action()
    {
        i++;
        i %= 8;
        this.hero.setImg(i);

        this.hero.angle =  Math.atan2(mouseY - (this.hero.y * 32), mouseX - (this.hero.x * 32) );
        System.out.format("Angle = %f\n",  this.hero.angle);
        this.hero.move(this.hero.getSpeedX(), 0, this.dungeon);
        this.hero.move(0, this.hero.getSpeedY() ,this.dungeon);
    }
}
