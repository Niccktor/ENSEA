public final class Hero extends DynamicThings {
    private static Hero instance = null;

    private Hero() {
        super(0, 0, 1, 1);
    }

    public static Hero getInstance() {
        if (Hero.instance == null) {
            synchronized (Hero.class) {
                if (Hero.instance == null) {
                    Hero.instance = new Hero();
                }
            }
        }
        return Hero.instance;
    }
    public void move(double dx, double dy, Dungeon dungeon){
        boolean isPossible = true;
        this.getBox().move(dx, dy);
        for (Things t : dungeon.getListOfThings()){
            if (t instanceof SolidThings) {
                if (((SolidThings) t).getBox().intersect(this.getBox()))
                    isPossible = false;
            }
        }
        if (isPossible)
        {
            this.x += dx;
            this.y += dy;
        }
        else
            this.getBox().move(-dx, -dy);
    }
}
