import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainInterface extends JFrame  implements KeyListener{

    GameRender gameRender = null;
    public MainInterface() throws HeadlessException{
        super();
        this.gameRender = new GameRender(new Dungeon("./test.txt"));

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(new Dimension(1080, 500));
        this.setResizable(false);
        ActionListener timerAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameRender.paintComponent(getGraphics());
            }
        };
        Timer clock = new Timer(17, timerAction);
        clock.start();
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                this.gameRender.hero.move(-0.1,0, this.gameRender.dungeon);
//                System.out.format("Gauche \n");
                break;
            case KeyEvent.VK_D:
                this.gameRender.hero.move(0.1,0, this.gameRender.dungeon);
//                System.out.format("Droite \n");
                break;
            case KeyEvent.VK_W:
                this.gameRender.hero.move(0,-0.1, this.gameRender.dungeon);
//                System.out.format("Haut \n");
                break;
            case KeyEvent.VK_S:
                this.gameRender.hero.move(0,0.1, this.gameRender.dungeon);
//                System.out.format("Bas \n");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {
        MainInterface mainInterface = new MainInterface();
    }
}
