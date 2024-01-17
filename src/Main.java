/*
*                 x
*            --------->
*           |
*         y |
*           |
*           v
* */



public class Main {
    public static void main(String[] args) {
        HitBox A = new HitBox(0, 0, "A", 0,0);
        HitBox B = new HitBox(2,4, "B", 1, 1);

        if (A.intersect(B))
            System.out.println("Intersection A B");
        else
            System.out.println("Pas intersection A B");

        Hero hero = Hero.getInstance();

        //Dungeon dungeon = new Dungeon(5, 10);
        Dungeon dungeon1 = new Dungeon("./test.txt");
        dungeon1.display_Dungeon(hero.getBox());
        int i = 0;
        while (i < 14)
        {
            hero.move(0.5, 0,dungeon1);
            dungeon1.display_Dungeon(hero.getBox());
            i++;
        }
        Hero tes = Hero.getInstance();
        System.out.println("é");

    }
}