import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;

public class TileManager {
    private int height;
    private int width;
    protected Image[][] tiles;
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
    public Image getTile(int i, int j, double angle)
    {
        BufferedImage img = (BufferedImage) tiles[i][j];
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return rotated;
    }

    public TileManager(int height, int width, String file) {
        this.height = height;
        this.width = width;
        setTiles(height, width, file);

    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
}
