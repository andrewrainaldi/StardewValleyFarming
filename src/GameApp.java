package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GameApp extends JPanel implements Runnable {

    private Player player;
    private final ArrayList<Entity> enemies = new ArrayList<>();
    private Thread gameThread;
    private boolean running = false;

    public GameApp () {
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.decode("#176a3a"));
        this.setFocusable(true);

        // Inizializza il giocatore
        player = new Player(400.0, 300.0, 50.0, 50.0, 5.0);

        enemies.add(new Path(100, 140, 600, 50));
        enemies.add(new House(300, 200, 100, 100));

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.keyReleased(e);
            }
        });
    }

    public void start() {
        if (running) return;
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                updateGame();
                delta--;
            }
            repaint();
        }
    }

    private void updateGame() {
        double vecchiaX = player.x;
        double vecchiaY = player.y;

        player.update();

        for (Entity enemy : enemies) {
            enemy.update();
            if (enemy instanceof House) {
                House h = (House) enemy;
                h.gestisciCollisione(player, vecchiaX, vecchiaY);
            }
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Entity enemy : enemies) {
            enemy.draw(g);
        }

        House casaTrovata = null;
        for (Entity enemy : enemies) {
            if (enemy instanceof House) {
                House h = (House) enemy;
                casaTrovata = h;
                if (casaTrovata.controllaSeDietroCasa(player)) {
                    player.draw(g);
                    casaTrovata.draw(g);
                } else {
                    casaTrovata.draw(g);
                    player.draw(g);
                }
            }
        }
        if (casaTrovata == null) {
            player.draw(g);
        }
    }
}