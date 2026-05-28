package scr;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Player extends Entity {
    
    public boolean up, down, left, right;

    public Player(int x, int y, int width, int height, int speed) {
        super(x, y, width, height, speed);
    }

    @Override
    public void update() {
        // Muove il giocatore in base ai tasti attivi
        if (up)    y -= speed;
        if (down)  y += speed;
        if (left)  x -= speed;
        if (right) x += speed;
    }

    @Override
    public void draw(Graphics g) {
        // Coloriamo il giocatore di blu per distinguerlo
        g.setColor(Color.BLUE);
        g.fillRect((int)x, (int)y, (int)width,(int) height);
    }

    // Gestione dell'input (chiamata dal GamePanel)
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP)    up = true;
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)  down = true;
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)  left = true;
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) right = true;
    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP)    up = false;
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)  down = false;
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)  left = false;
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) right = false;
    }
}