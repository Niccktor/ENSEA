import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.sql.Timestamp;
import java.util.Set;
import java.util.TreeSet;


public class MainInterface extends JFrame  implements KeyListener, MouseMotionListener {

    GameRender gameRender = null;

    public MainInterface() throws HeadlessException{
        super("test");
        this.gameRender = new GameRender(new Dungeon("./test.txt"));

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(new Dimension(1080, 500));
        this.setResizable(false);
        long old_time = System.nanoTime();
        long new_time;
        BufferedImage buffer =  new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);

        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        while (true)
        {
            new_time = System.nanoTime();
            if (new_time - old_time > 17000000)
            {
                //System.out.format("%d - %d = %d\n", new_time, old_time , new_time - old_time);
                old_time = System.nanoTime();
                gameRender.action();
                paintComponent(buffer.getGraphics());
                this.getGraphics().drawImage(buffer,0,0,this);
            }
        }
    }
    Set<Integer> pressedKeys = new TreeSet<Integer>();

    protected void paintComponent(Graphics g)
    {
        for (Things t : gameRender.dungeon.getListOfThings()){
            if (t instanceof AnimatedThings){
                ((AnimatedThings) t).sprit();
            }
            t.draw(g);
            if (gameRender.hero.box.intersect(t.getBox())) {
                gameRender.hero.sprit(gameRender.hero.angle);
                gameRender.hero.draw(g);
            }
        }
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
    public void keyPressed(KeyEvent e)
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
                    this.gameRender.put_bomb(new Bomb(this.gameRender.hero.x, this.gameRender.hero.y, "Bomb.png"));
            }
        }
    }

    public static void main(String[] args) {
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
        //System.out.format("Move, X = %d, Y = %d\n", e.getX(), e.getY());
    }
}
