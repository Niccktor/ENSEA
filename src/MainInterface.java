import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
        ActionListener timerAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameRender.action();
                paintComponent(getGraphics());
            }
        };
        Timer clock = new Timer(17, timerAction);
        clock.start();
        this.addMouseMotionListener(this);
        this.addKeyListener(this);

    }
    Set<Integer> pressedKeys = new TreeSet<Integer>();

    protected void paintComponent(Graphics g)
    {
        for (Things t : gameRender.dungeon.getListOfThings()){
            t.draw(g);
            g.drawLine(this.gameRender.mouseX,this.gameRender.mouseY, (int)(this.gameRender.hero.x * 32.0) +16, (int)(this.gameRender.hero.y *32.0) + 32 +16);
            g.drawLine(0,0, (int)(this.gameRender.hero.x * 32.0) +16, (int)(this.gameRender.hero.y *32.0) + 32 +16);
            g.drawLine(this.gameRender.mouseX,this.gameRender.mouseY, 0,0);
            if (gameRender.hero.box.intersect(t.getBox()))
                gameRender.hero.draw(g);
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
