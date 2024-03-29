import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dungeon {
    private char[][]maps;
    private int height;
    private int width;
    public TileManager tile;

    public ArrayList<Things> getListOfThings() {
        return listOfThings;
    }

    protected ArrayList<Things> listOfThings = new ArrayList<>();
    protected ArrayList<Things> new_listOfThings = new ArrayList<>();
    private void createListOfThings(){
        this.listOfThings.clear();
        int i;
        int j;

        i = 0;
        while (this.height > i)
        {
            j = 0;
            while (this.width > j)
            {
                switch (this.maps[i][j]){
                    case 'W':
                        this.listOfThings.add(new SolidThings(j, i, tile.getTile(0,1)));
                        break;
                    case ' ':
                        this.listOfThings.add(new Things(j, i, tile.getTile(0,0)));
                        break;
                    case 'X':
                        this.listOfThings.add(new DestructionThings(j, i, tile.getTile(1,5)));
                        break;
                }
                j++;
            }
            i++;
        }
    }
    public Dungeon(String file)
    {
        int height = 0;
        int width = 0;
        BufferedReader fd = null;
        String line;

        this.tile = new TileManager(32, 32, "default_tiles_x.png");

        try {
            fd = new BufferedReader(new FileReader(file));
            while ((line = fd.readLine()) != null) {
                if(line.length() > width)
                    width = line.length();
                height++;
            }
        }
        catch (IOException e) {
            System.out.println("Reading : " + file);
            throw new RuntimeException(e);
        }
        try{
            fd.close();
        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Closing" + file);
        }
        this.height = height;
        this.width = width;
        this.maps = new char[this.height][this.width];
        int i = 0;
        try {
            fd = new BufferedReader(new FileReader(file));
            while ((line = fd.readLine()) != null) {
                this.maps[i++] = line.toCharArray();
            }
        }
        catch (IOException e) {
            System.out.println("Reading : " + file);
            throw new RuntimeException(e);
        }
        try{
            fd.close();
        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Closing" + file);
        }
        createListOfThings();
    }
    public Dungeon(int height, int width) {
        int i = 0;
        int j;

        this.height = height;
        this.width = width;
        this.maps = new char[height][width];
        this.tile = new TileManager(32, 32, "default_tiles_x.png");

        while (this.height > i)
        {
            j = 0;
            while (this.width > j)
            {
                if (i == 0 || i == this.height - 1 || j == 0 || j == this.width - 1)
                    this.maps[i][j] = 'W';
                else
                    this.maps[i][j] = ' ';
                j++;
            }
            i++;
        }
        createListOfThings();
    }

    public void display_Dungeon(HitBox hero) {
        int i;
        int j;

        i = 0;
        while (i < this.height)
        {
            j = 0;
            while (j < this.width) {
                if (hero != null && (((int)hero.getX()) == j && (int)hero.getY() == i) && this.maps[i][j] == ' ')
                    System.out.print('H');
                else
                    System.out.print(maps[i][j]);
                j++;
            }
            System.out.print("\n");
            i++;
        }

    }
}
