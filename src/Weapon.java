package src;

import java.awt.Graphics; // Import corretto con la S
import java.awt.Color;

public class Weapon extends Entity {

    private boolean attivo = false;
    private long timerAttivazione = 0;
    private final long DURATA_ATTACCO = 150_000_000L; // 0.15 secondi

    public Weapon() {
        // Usa il costruttore a 5 parametri richiesto dalla tua classe Entity
        super(0, 0, 30, 30, 0); 
    }

    public void setPotenziata() {
        this.width = 55;  
        this.height = 55;
    }

    public void attacca(double xGiocatore, double yGiocatore, double wGiocatore, double hGiocatore) {
        if (!attivo) {
            this.x = xGiocatore + wGiocatore; 
            this.y = yGiocatore + (hGiocatore / 2.0) - (this.height / 2.0);
            this.attivo = true;
            this.timerAttivazione = System.nanoTime();
        }
    }

    @Override
    public void update() {
        if (attivo && (System.nanoTime() - timerAttivazione >= DURATA_ATTACCO)) {
            attivo = false;
        }
    }

    // CORREZIONE RIGA 5: Ora il metodo è IDENTICO a quello astratto di Entity (con la S)
    @Override
    public void draw(Graphics g) {
        if (!attivo) return;

        // Convertiamo in interi per i metodi di disegno standard
        int ix = (int) this.x;
        int iy = (int) this.y;
        int iw = (int) this.width;
        int ih = (int) this.height;

        if (this.width > 40) {
            g.setColor(Color.LIGHT_GRAY); 
            g.fillRect(ix, iy, iw, ih / 3);
            g.setColor(Color.WHITE);
            g.drawRect(ix, iy, iw, ih / 3);
        } else {
            g.setColor(Color.GRAY);
            g.fillRect(ix, iy, iw, ih / 4);
        }
    }

    public boolean isAttivo() { 
        return attivo; 
    }
}