import java.awt.*;

public class GameRender {
    public Dungeon dungeon;
    public Hero hero;


    public GameRender(Dungeon dungeon) {
        this.dungeon = dungeon;
        this.hero = Hero.getInstance();
    }
    public void paintComponent(Graphics g) {
        for (Things t : dungeon.getListOfThings()){
            t.draw(g);
            hero.draw(g);
        }
        hero.draw(g);
        //System.out.format("tile_printed : %d\n", tile_printed);
    }
}
