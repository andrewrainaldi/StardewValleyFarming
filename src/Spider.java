package src;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Spider extends Entity {

    // Lista di tutti i ragni
    private static ArrayList<Spider> spiders = new ArrayList<>();

    // Timer spawn
    private static long lastSpawnTime = System.currentTimeMillis();

    // Spawn ogni 20 secondi
    private static final long SPAWN_INTERVAL = 20000;

    // Movimento
    private int directionX = 1;
    private int moveRange = 100;
    private double startX;

    public Spider(double x, double y) {
        super(x, y, 32, 32, 1.5);

        this.startX = x;
    }

    @Override
    public void update() {

        // Movimento automatico destra/sinistra
        x += speed * directionX;

        // Cambio direzione
        if (x >= startX + moveRange) {
            directionX = -1;
        }

        if (x <= startX - moveRange) {
            directionX = 1;
        }
    }

    @Override
    public void draw(Graphics g) {

        // Corpo
        g.setColor(Color.BLACK);
        g.fillOval((int)x, (int)y, (int)width, (int)height);

        // Occhi
        g.setColor(Color.RED);
        g.fillOval((int)x + 8, (int)y + 8, 5, 5);
        g.fillOval((int)x + 18, (int)y + 8, 5, 5);

        // Zampe
        g.setColor(Color.DARK_GRAY);

        // Sinistra
        g.drawLine((int)x, (int)y + 10, (int)x - 10, (int)y);
        g.drawLine((int)x, (int)y + 16, (int)x - 10, (int)y + 10);
        g.drawLine((int)x, (int)y + 22, (int)x - 10, (int)y + 22);
        g.drawLine((int)x, (int)y + 28, (int)x - 10, (int)y + 32);

        // Destra
        g.drawLine((int)x + 32, (int)y + 10, (int)x + 42, (int)y);
        g.drawLine((int)x + 32, (int)y + 16, (int)x + 42, (int)y + 10);
        g.drawLine((int)x + 32, (int)y + 22, (int)x + 42, (int)y + 22);
        g.drawLine((int)x + 32, (int)y + 28, (int)x + 42, (int)y + 32);
    }

    // =========================
    // GESTIONE GLOBALE RAGNI
    // =========================

    public static void updateAll() {

        long currentTime = System.currentTimeMillis();

        // Spawn ogni 20 secondi
        if (currentTime - lastSpawnTime >= SPAWN_INTERVAL) {

            double randomX = Math.random() * 800;
            double randomY = Math.random() * 600;

            spiders.add(new Spider(randomX, randomY));

            lastSpawnTime = currentTime;

            System.out.println("Nuovo Spider spawnato!");
        }

        // Aggiorna tutti i ragni
        for (Spider spider : spiders) {
            spider.update();
        }
    }

    public static void drawAll(Graphics g) {

        for (Spider spider : spiders) {
            spider.draw(g);
        }
    }

    public static ArrayList<Spider> getSpiders() {
        return spiders;
    }
}