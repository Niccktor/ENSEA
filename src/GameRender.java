import java.awt.*;
import java.util.ArrayList;

public class GameRender {
    public int play;
    public Dungeon dungeon;
    public Hero hero;
    public int mouseX = 0;
    public int mouseY = 0;
    public int mouseClick = 0;

    public GameRender(Dungeon dungeon) {
        this.dungeon = dungeon;
        this.hero = Hero.getInstance();
        this.play = 1;
    }
    static int i = 0;
    public void action()
    {
        this.hero.test();
        this.dungeon.new_listOfThings = new ArrayList<Things>(this.dungeon.listOfThings);

        for (Things t : this.dungeon.getListOfThings())
        {
            if (t instanceof Explosion)
                for (Things b : this.dungeon.getListOfThings())
                {
                    if (b instanceof DestructionThings && t.box.intersect(b.getBox()))
                    {
                        //this.dungeon.new_listOfThings(new )
                        this.dungeon.new_listOfThings.add(new Things(b.x, b.y, this.dungeon.tile.getTile(0,0)));
                        this.dungeon.new_listOfThings.remove(b);
                    }
                }
        }
        this.dungeon.listOfThings.clear();
        this.dungeon.listOfThings = new ArrayList<Things>(this.dungeon.new_listOfThings);


        this.hero.angle = 180.0 / Math.PI * (Math.atan2((this.hero.y * 32) + (double) this.hero.height /2 +32  - mouseY, ((this.hero.x * 32) + (double) this.hero.width / 2) - mouseX)) - 90;
        int a = this.hero.move(this.hero.getSpeedX(), 0, this.dungeon);
        int b = this.hero.move(0, this.hero.getSpeedY() ,this.dungeon);
        if (a == 0) {
            this.hero.speedX = 0;
        }
        if (b == 0) {
            this.hero.speedY = 0;
        }
        if (a == 2 || b == 2)
        {
            this.play = 2;

        }
    }
    public void put_bomb(Bomb bomb)
    {
        this.dungeon.listOfThings.add(bomb);
    }
}
