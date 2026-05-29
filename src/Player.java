package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Player extends Entity {

    public boolean up, down, left, right;

    // ==========================================
    // NUOVE VARIABILI: VITA E INVULNERABILITÀ
    // ==========================================
    private int hp = 5;                           // Il giocatore parte con 5 punti vita
    private boolean invulnerabile = false;        // Diventa true subito dopo un colpo
    private long timerInvulnerabilita = 0;        
    private final long DURATA_INVULNERABILITA = 1_000_000_000L; // 1 secondo espresso in nanosecondi

    public Player(double x, double y, double width, double height, double speed) {
        super(x, y, width, height, speed);
    }
    @Override
    public void update() {
        // Se il giocatore ha finito la vita, non si muove più
        if (isMorto()) return;

        // Gestione del recupero dall'invulnerabilità
        if (invulnerabile && (System.nanoTime() - timerInvulnerabilita >= DURATA_INVULNERABILITA)) {
            invulnerabile = false;
        }

        // Movimento player
        if (up)    { y -= speed; }
        if (down)  { y += speed; }
        if (left)  { x -= speed; }
        if (right) { x += speed; }
    }

    // ==========================================
    // NUOVI METODI: GESTIONE DANNO E STATO
    // ==========================================
    public void riceviDanno(int quantitaDanno) {
        // Se è già invulnerabile o è morto, ignora il danno
        if (invulnerabile || isMorto()) return;

        this.hp -= quantitaDanno;
        this.invulnerabile = true;
        this.timerInvulnerabilita = System.nanoTime(); // Avvia il cronometro di 1 secondo

        System.out.println("Player colpito! HP rimasti: " + this.hp);
        if (this.hp <= 0) {
            System.out.println("GAME OVER! Il Player è morto.");
        }
    }

    public boolean isMorto() {
        return this.hp <= 0;
    }

    public int getHp() {
        return this.hp;
    }
    
    @Override
    public void draw(Graphics g) {
        if (isMorto()) {
            // Se il giocatore è morto, disegna una tomba grigia o una scritta di Game Over
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("GAME OVER", (int)x - 30, (int)y - 10);
            
            g.setColor(Color.DARK_GRAY);
            g.fillRect((int)x, (int)y, (int)width, (int)height);
            return;
        }

        // Se è invulnerabile, lo facciamo lampeggiare (disegnandolo solo a frame alterni)
        if (invulnerabile && (System.nanoTime() / 100_000_000 % 2 == 0)) {
            // Salta il disegno per questo frame per creare l'effetto lampeggiante
            return;
        }

        // Player normale (Blu)
        g.setColor(Color.BLUE);
        g.fillRect((int)x, (int)y, (int)width, (int)height);

        // Opzionale: Disegna una barretta della vita sopra la testa del giocatore
        g.setColor(Color.RED);
        g.fillRect((int)x, (int)y - 10, (int)width, 5);
        g.setColor(Color.GREEN);
        g.fillRect((int)x, (int)y - 10, (int)(width * ((double)hp / 5)), 5);
    }

    // =========================================
    // INPUT
    // =========================================
    public void keyPressed(KeyEvent e) {
        if (isMorto()) return; // Non accetta input se morto

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