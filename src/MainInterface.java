import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class MainInterface extends JFrame  implements KeyListener, MouseMotionListener {
    boolean test = false;

    GameRender gameRender = null;

    public MainInterface() throws HeadlessException, IOException {
        super("test");
        this.gameRender = new GameRender(new Dungeon("./test2.txt"));

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(new Dimension(1000, 390));
        this.setResizable(false);
        long old_time = System.nanoTime();
        long new_time;
        BufferedImage buffer =  new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);

        this.addMouseMotionListener(this);
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                gameRender.mouseClick = me.getButton();
            }
        });
        this.addKeyListener(this);
        BufferedImage title = ImageIO.read(new File("Tilte.png"));
        BufferedImage title_play = ImageIO.read(new File("Title-play.png"));
        BufferedImage GameOver1 = ImageIO.read(new File("GameOver1.png"));
        BufferedImage GameOver2 = ImageIO.read(new File("GameOver2.png"));
        long old = System.nanoTime();
        while (true)
        {

            long newTime = System.nanoTime();
            if (this.gameRender.play == 1)
            {
                if (this.gameRender.mouseX >= 420 && this.gameRender.mouseX <= 498 && this.gameRender.mouseY >= 196 && this.gameRender.mouseY <= 284)
                {
                    if (gameRender.mouseClick == 1)
                    {
                        gameRender.play = 0;
                        gameRender.mouseClick = 0;
                    }

                    this.getGraphics().drawImage(title_play, 0, 0, this);

                }

                else
                    this.getGraphics().drawImage(title, 0, 0, this);


                //System.out.format("x: %d, y: %d\n", this.gameRender.mouseX, this.gameRender.mouseY);
                //Sthis.getGraphics().drawImage(title, 0, 0, this);
            }
            else if (this.gameRender.play == 2)
            {
                if ((this.gameRender.mouseX >= 430 && this.gameRender.mouseX <= 482  && this.gameRender.mouseY >= 250 && this.gameRender.mouseY <= 298)
                    || (this.gameRender.mouseX >= 546  && this.gameRender.mouseX <= 595 && this.gameRender.mouseY >= 250 && this.gameRender.mouseY <= 298))
                {
                    this.getGraphics().drawImage(GameOver2, 0, 0, this);

                    if (gameRender.mouseClick == 1)
                        gameRender.play = 0;
                }
                else {
                    this.getGraphics().drawImage(GameOver1, 0, 0, this);
                }
                System.out.format("x: %d, y: %d\n", this.gameRender.mouseX, this.gameRender.mouseY);

                this.clearBomb();
            }
            else {
                new_time = System.nanoTime();
                if (new_time - old_time > 17000000) {
                    //System.out.format("%d - %d = %d\n", new_time, old_time , new_time - old_time);
                    old_time = System.nanoTime();
                    gameRender.action();
                    if (test) {
                        test = false;
                        this.gameRender.put_bomb(new Bomb(this.gameRender.hero.x, this.gameRender.hero.y, "Bomb.png"));
                    }
                    paintComponent(buffer.getGraphics());
                    this.getGraphics().drawImage(buffer, 0, 0, this);
                }
            }
        }
    }
    Set<Integer> pressedKeys = new TreeSet<Integer>();

    private void clearBomb()
    {
        for (Things t : gameRender.dungeon.getListOfThings()){
            if (t instanceof Bomb || t instanceof  Explosion){
                gameRender.dungeon.new_listOfThings.remove(t);
            }
        }
        gameRender.dungeon.listOfThings.clear();

        gameRender.dungeon.listOfThings = new ArrayList<Things>(gameRender.dungeon.new_listOfThings);
    }
    protected void paintComponent(Graphics g)
    {
        gameRender.dungeon.new_listOfThings = new ArrayList<Things>(gameRender.dungeon.listOfThings);
        int foo;
        for (Things t : gameRender.dungeon.getListOfThings()){
            if (t instanceof AnimatedThings){
                foo = ((AnimatedThings) t).sprit();
                switch (foo)
                {
                    case 0:
                        //gameRender.dungeon.listOfThings.add(new Bomb(t.x, t.y, "explosion_center.png"));
                        //gameRender.dungeon.listOfThings.remove(t);
                        break;
                    case 1:
                        gameRender.dungeon.new_listOfThings.add(new Explosion(t.x-1, t.y, "explosion_left.png"));
                        gameRender.dungeon.new_listOfThings.add(new Explosion(t.x+1, t.y, "explosion_right.png"));
                        gameRender.dungeon.new_listOfThings.add(new Explosion(t.x, t.y, "explosion_center.png"));
                        gameRender.dungeon.new_listOfThings.add(new Explosion(t.x, t.y + 1, "explosion_down.png"));
                        gameRender.dungeon.new_listOfThings.add(new Explosion(t.x, t.y-1, "explosion_up.png"));
                        gameRender.dungeon.new_listOfThings.remove(t);
                        break;
                    case 2:
                        gameRender.dungeon.new_listOfThings.remove(t);
                        break;
                }
            }
            t.draw(g);
            if (gameRender.hero.box.intersect(t.getBox())) {
                gameRender.hero.sprit(gameRender.hero.angle);
                gameRender.hero.draw(g);
            }

        }
        gameRender.dungeon.listOfThings.clear();

        gameRender.dungeon.listOfThings = new ArrayList<Things>(gameRender.dungeon.new_listOfThings);

    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e){
        pressedKeys.remove(e.getKeyCode());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A, KeyEvent.VK_Q, KeyEvent.VK_D:
                this.gameRender.hero.acceleration(5);
                break;
            case KeyEvent.VK_W, KeyEvent.VK_Z, KeyEvent.VK_S:
                this.gameRender.hero.acceleration(6);
                break;
        }
    }
    @Override
    synchronized public void keyPressed(KeyEvent e)
    {
        if (!pressedKeys.contains(e.getKeyCode()))
        {
            pressedKeys.add(e.getKeyCode());
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A, KeyEvent.VK_Q:
                    this.gameRender.hero.acceleration(1);
                    break;
                case KeyEvent.VK_D:
                    this.gameRender.hero.acceleration(2);
                    break;
                case KeyEvent.VK_W, KeyEvent.VK_Z:
                    this.gameRender.hero.acceleration(4);
                    break;
                case KeyEvent.VK_S:
                    this.gameRender.hero.acceleration(3);
                    break;
                case KeyEvent.VK_L:
                    //this.gameRender.put_bomb(new Bomb(this.gameRender.hero.x, this.gameRender.hero.y, "Bomb.png"));
                    test = true;
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        MainInterface mainInterface = new MainInterface();
        mainInterface.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.gameRender.mouseX = e.getX();
        this.gameRender.mouseY = e.getY();
        this.gameRender.mouseClick = e.getButton();
        //System.out.format("Move, X = %d, Y = %d\n", e.getX(), e.getY());
    }
}
