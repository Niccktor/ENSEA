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
        this.hero.test();

        this.hero.angle = 180.0 / Math.PI * (Math.atan2((this.hero.y * 32) + (double) this.hero.height /2 +32  - mouseY, ((this.hero.x * 32) + (double) this.hero.width / 2) - mouseX)) - 90;
        if (!this.hero.move(this.hero.getSpeedX(), 0, this.dungeon)) {
            this.hero.speedX = 0;

        }
        if (!this.hero.move(0, this.hero.getSpeedY() ,this.dungeon)) {
            this.hero.speedY = 0;

        }

    }
}
