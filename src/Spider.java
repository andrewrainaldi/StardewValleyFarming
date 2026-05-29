package src;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Spider extends Entity {

    // Gestione globale della lista dei ragni e dello spawn
    private final ArrayList<Spider> spiders = new ArrayList<>();
    private final long lastSpawnTime = System.currentTimeMillis();
    private final long SPAWN_INTERVAL = 20000; // 20 secondi

    // Variabili per il movimento automatico
    private int directionX = 1;
    private final int moveRange = 100;
    private final double startX;

    // Statistiche del Ragno
    private int hp = 2;                      // Il ragno muore con 2 colpi di spada
    private final int danno = 1;             // DANNO PREDEFINITO: infligge 1 punto danno al contatto

    public Spider(double x, double y) {
        // Dimensioni: 32x32 pixel, Velocità: 1.5
        super(x, y, 32, 32, 1.5);
        this.startX = x;
    }

    @Override
    public void update() {
        // Movimento automatico sinistra/destra
        x += speed * directionX;

        // Inversione della marcia se supera il raggio d'azione (moveRange)
        if (x >= startX + moveRange) {
            directionX = -1;
        }
        if (x <= startX - moveRange) {
            directionX = 1;
        }
    }

    // =========================================================================
    // GESTIONE COMBATTIMENTO E STATO
    // =========================================================================

    /**
     * Riduce la vita del ragno quando viene colpito dalla spada del giocatore.
     */
    public void riceviDanno(int dannoSpada) {
        this.hp -= dannoSpada;
        System.out.println("Ragno colpito! HP rimanenti: " + this.hp);
    }

    /**
     * Verifica se il ragno ha esaurito i suoi punti vita.
     */
    public boolean isMorto() {
        return this.hp <= 0;
    }

    /**
     * Getter pubblico che permette al motore di gioco di scoprire 
     * il danno predefinito di questo ragno per applicarlo al Player.
     */
    public int getDanno() {
        return this.danno;
    }

    // =========================================================================
    // RENDERING GRAFICO (DISEGNO)
    // =========================================================================

    @Override
    public void draw(Graphics g) {
        // Se il ragno è ferito (ha 1 HP), cambia colore in grigio scuro, altrimenti nero
        if (hp == 1) {
            g.setColor(Color.DARK_GRAY); 
        } else {
            g.setColor(Color.BLACK);
        }
        
        // Corpo del ragno
        g.fillOval((int)x, (int)y, (int)width, (int)height);

        // Occhi rossi da mostro
        g.setColor(Color.RED);
        g.fillOval((int)x + 8, (int)y + 8, 5, 5);
        g.fillOval((int)x + 18, (int)y + 8, 5, 5);

        // Disegno delle zampe (Sinistra e Destra)
        g.setColor(Color.DARK_GRAY);
        // Zampe Sinistre
        g.drawLine((int)x, (int)y + 10, (int)x - 10, (int)y);
        g.drawLine((int)x, (int)y + 16, (int)x - 10, (int)y + 10);
        g.drawLine((int)x, (int)y + 22, (int)x - 10, (int)y + 22);
        g.drawLine((int)x, (int)y + 28, (int)x - 10, (int)y + 32);
        // Zampe Destre
        g.drawLine((int)x + 32, (int)y + 10, (int)x + 42, (int)y);
        g.drawLine((int)x + 32, (int)y + 16, (int)x + 42, (int)y + 10);
        g.drawLine((int)x + 32, (int)y + 22, (int)x + 42, (int)y + 22);
        g.drawLine((int)x + 32, (int)y + 28, (int)x + 42, (int)y + 32);

    }
}