import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dungeon {
    private char[][]maps;
    private int height;
    private int width;
    private TileManager tile;

    public ArrayList<Things> getListOfThings() {
        return listOfThings;
    }

    private ArrayList<Things> listOfThings = new ArrayList<>();
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
                        this.listOfThings.add(new SolidThings(1, 1, j, i));
                        break;
                    case ' ':
                        this.listOfThings.add(new Things(1, 1, j, i));
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
