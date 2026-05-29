package src;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class GameWorld extends JPanel implements Runnable {

    // IMPOSTAZIONI DELLO SCHERMO
    final int originalTileSize = 16; 
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 pixel
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; 
    public final int screenHeight = tileSize * maxScreenRow; 

    // FPS
    //int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // Costruttore
    public GameWorld() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.decode("#176a3a"));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // Qui andrà il tuo game loop (update e repaint)
    }
}