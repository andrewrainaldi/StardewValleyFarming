package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Villager extends Entity {

    private String merceInVendita;
    private int costo;
    private boolean giocatoreVicino = false;
    
    private double offsetFluttuante = 0;
    private double tempoAnimazione = 0;

    public Villager(int x, int y, String merceInVendita, int costo) {
        // Passa i 5 parametri interi richiesti dal costruttore di Entity
        super(x, y, 36, 44, 0);
        this.merceInVendita = merceInVendita;
        this.costo = costo;
    }

    public void aggiornaNegozio(String nuovaMerce, int nuovoCosto) {
        this.merceInVendita = nuovaMerce;
        this.costo = nuovoCosto;
    }
    @Override
    public void update() {
        tempoAnimazione += 0.05;
        offsetFluttuante = Math.sin(tempoAnimazione) * 4;
    }

    // Cambiato il parametro da "Character" a "Entity" così non serve nessuna classe Character esterna!
    public void controllaVicinanza(Entity giocatore) {
        double centroX_V = this.x + this.width / 2.0;
        double centroY_V = this.y + this.height / 2.0;
        
        // Legge direttamente le coordinate x, y, width e height ereditate da Entity
        double centroX_G = giocatore.x + giocatore.width / 2.0;
        double centroY_G = giocatore.y + giocatore.height / 2.0;

        double distanza = Math.sqrt(Math.pow(centroX_V - centroX_G, 2) + Math.pow(centroY_V - centroY_G, 2));
        this.giocatoreVicino = (distanza < 80);
    }
 
    @Override
    public void draw(Graphics g) {
        int drawX = (int) x;
        int drawY = (int) y;
        int drawWidth = (int) width;
        int drawHeight = (int) height;

        // Disegno Fabbro (Veste Grigia/Nera da Fabbro metallurgico)
        g.setColor(Color.decode("#e0a96d"));
        g.fillRect(drawX + 6, drawY, drawWidth - 12, 14);

        g.setColor(Color.decode("#34495e")); // Grembiule scuro
        g.fillRect(drawX, drawY + 14, drawWidth, drawHeight - 14);
        
        g.setColor(Color.DARK_GRAY); 
        g.fillRect(drawX + 4, drawY + drawHeight - 4, 8, 4);
        g.fillRect(drawX + drawWidth - 12, drawY + drawHeight - 4, 8, 4);

        if (giocatoreVicino) {
            int fumettoX = drawX - 25;
            int fumettoY = (int) (y - 45 + offsetFluttuante);
            int fumettoW = 90;
            int fumettoH = 34;

            // Sfondo bianco del fumetto
            g.setColor(Color.decode("#d0ddea"));
            g.fillRoundRect(fumettoX, fumettoY, fumettoW, fumettoH, 10, 10);
            
            // Bordo nero del fumetto
            g.setColor(Color.decode("#000000"));
            g.drawRoundRect(fumettoX, fumettoY, fumettoW, fumettoH, 10, 10);

            // Freccia inferiore del fumetto
            int[] polyX = {(int)(x + width / 2 - 5), (int)(x + width / 2), (int)(x + width / 2 + 5)};
            int[] polyY = {fumettoY + fumettoH, fumettoY + fumettoH + 6, fumettoY + fumettoH};
            
            g.setColor(Color.decode("#d0ddea"));
            g.fillPolygon(polyX, polyY, 3);
            
            g.setColor(Color.decode("#000000"));
            g.drawPolyline(polyX, polyY, 3);

            // Testo interno al fumetto
            g.setFont(new Font("Arial", Font.BOLD, 9));
            
            if (costo > 0) {
                g.drawString(merceInVendita + " (" + costo + "$)", fumettoX + 5, fumettoY + 14);
                g.setColor(Color.decode("#27ae60"));
                g.setFont(new Font("Arial", Font.PLAIN, 8));
                g.drawString("Premi [E] per comprare", fumettoX + 4, fumettoY + 26);
            } else {
                g.drawString(merceInVendita, fumettoX + 12, fumettoY + 20); 
            }
        }
    }

    public boolean isGiocatoreVicino() { return giocatoreVicino; }
    public int getCosto() { return costo; }
    public String getMerceInVendita() { return merceInVendita; }
}