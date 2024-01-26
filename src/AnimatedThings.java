import java.awt.*;

public class AnimatedThings extends SolidThings {

    protected TileManager tile;
    protected int i;
    protected long delay;
    protected long old_time;
    protected long new_time;

    public AnimatedThings(double x, double y, TileManager tile, long delay)
    {
        super( x, y, tile.getTile(0,0));
        this.tile = tile;
        this.old_time = System.nanoTime();
        this.delay = 100000000;
    }
    public AnimatedThings(double x, double y, TileManager tile)
    {
        super( x, y, tile.getTile(0,0));
        this.tile = tile;
        this.old_time = System.nanoTime();
        this.delay = 40000000;
    }
    public AnimatedThings(int height, int width, double x, double y) {
        super(height, width, x, y);
    }

    public AnimatedThings(double x, double y, Image image) {
        super(x, y, image);
    }
   public void sprit()
    {
        this.new_time = System.nanoTime();
        if(this.new_time - this.old_time > this.delay)
        {
            this.i++;
            this.old_time = System.nanoTime();
        }
        this.i = (this.i < this.tile.tiles[0].length) ? this.i : 0;
        this.img = this.tile.getTile(0,this.i);
        System.out.format("i: %d\n\n", i);
    }
    public void sprit(double angle)
    {
        //this.i = (this.i < this.tile.tiles[0].length -1) ? this.i + 1: 0;
        this.new_time = System.nanoTime();
        if(this.new_time - this.old_time > this.delay)
        {
            this.i++;
            this.old_time = System.nanoTime();
        }
        this.i = (this.i < this.tile.tiles[0].length) ? this.i : 0;
        this.img = this.tile.getTile(0,this.i, angle);

    }
}
