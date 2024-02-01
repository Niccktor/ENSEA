public class Explosion extends DynamicThings{
    public Explosion(int height, int width, double x, double y) {
        super(height, width, x, y);
    }

    public Explosion(double x, double y, String file)
    {
        super(x,y, new TileManager(35, 35, file), 900000000 );
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
        {
            return 2;
            //this.tile = new TileManager(32,32, "explosion_center.png");
            //this.i = 0;
        }
        this.img = this.tile.getTile(0,this.tile.tiles[0].length - this.i - 1);
        //System.out.format("i: %d\n\n", i);
        return 0;
    }
}
