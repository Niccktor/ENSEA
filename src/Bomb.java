public class Bomb extends DynamicThings{

    public Bomb(int height, int width, double x, double y) {
        super(height, width, x, y);
    }

    public Bomb(double x, double y, String file)
    {
        super(x,y, new TileManager(32, 32, file), 400000000 );
    }

    public int sprit()
    {
        this.new_time = System.nanoTime();
        if(this.new_time - this.old_time > this.delay)
        {
            this.i++;
            this.old_time = System.nanoTime();
        }
        if (!(this.i < this.tile.tiles[0].length))
            return 1;
        this.img = this.tile.getTile(0,this.i);
        return 0;
    }
}
