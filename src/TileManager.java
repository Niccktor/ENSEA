import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class TileManager {
    private int height;
    private int width;
    private Image[][] tiles;
    private BufferedImage tileSheet;
    private void setTiles(int height, int width, String file) {
        try{
            tileSheet = ImageIO.read(new File(file));
        }catch(Exception e){
            e.printStackTrace();
        }
        tiles = new Image[tileSheet.getHeight() / height][tileSheet.getWidth() / width];
        int i = 0;
        int j;
        while (i < tileSheet.getHeight() / height)
        {
            j = 0;
            while (j < tileSheet.getWidth() / width)
            {
                tiles[i][j] = tileSheet.getSubimage(j * width, i * height, width, height);
                j++;
            }
            i++;
        }
    }
    public Image getTile(int i, int j) {
        return this.tiles[i][j];
    }

    public TileManager(int height, int width) {
        this.height = height;
        this.width = width;
        setTiles(height, width, "tileSetTest.png");
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
}
