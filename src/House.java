package src;

import java.awt.Graphics; 

public class House extends Entity {

    // Colori della casa salvati come stringhe esadecimali pulite
    private final String coloreMuri = "#ba4a00";
    private final String coloreTetto = "#ba4a00"; 
    private final String colorePorta = "#5e2700";
    private final String coloreFinestra = "#add8e6";

    public House(double x, double y, double w, double h) {
        // Passiamo a Entity i 5 parametri richiesti (x, y, width, height, speed = 0)
        super(x, y, w, h, 0); 
    }
    @Override
    public void update() {
        // Entità statica
    }
    @Override
    public void draw(Graphics g) {
        // Lasciato vuoto o utilizzabile dal tuo ciclo di rendering AWT/Swing standard
    }

    // Metodi Getter pubblici
    public String getColoreMuri() { return coloreMuri; }
    public String getColoreTetto() { return coloreTetto; }
    public String getColorePorta() { return colorePorta; }
    public String getColoreFinestra() { return coloreFinestra; }

    // Ritorna le coordinate dei tre punti del tetto per il disegno esterno
    public double[][] getCoordinateTetto() {
        return new double[][]{
            {x - 10, x + (width / 2.0), x + width + 10}, // Coordinate X
            {y + 20, y - 15, y + 20}                      // Coordinate Y
        };
    }

    public boolean controllaSeDietroCasa(Player giocatore) {
        if (giocatore == null) return false;

        // Calcolo manuale della sovrapposizione tra House e Player
        boolean siSovrappongono = (giocatore.x < this.x + this.width && 
                                   giocatore.x + 32 > this.x && 
                                   giocatore.y < this.y + this.height && 
                                   giocatore.y + 32 > this.y);

        return siSovrappongono && (giocatore.y < this.y + 20);
    }

    public void gestisciCollisione(Player giocatore, double vecchiaX, double vecchiaY) {
        if (giocatore != null) {
            // Controllo manuale della collisione sui muri della casa
            boolean siSovrappongono = (giocatore.x < this.x + this.width && 
                                       giocatore.x + 32 > this.x && 
                                       giocatore.y < this.y + this.height && 
                                       giocatore.y + 32 > this.y);
            
            if (siSovrappongono && (giocatore.y >= this.y + 10)) {
                // Riposizionamento alle coordinate precedenti (mantenendo double)
                giocatore.x = vecchiaX;
                giocatore.y = vecchiaY;
            }
        }
    }
}