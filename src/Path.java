package src;

import java.awt.Color;
import java.awt.Graphics; // Importato Graphics generico richiesto dalla superclasse

public class Path extends Entity {

    // 1. Modificati i parametri da double a int per allinearsi alla classe Entity
    public Path(int x, int y, int width, int height) {
        // 2. Passati i 4 parametri + un 5° parametro intero (es. 0) richiesto da Entity
        super(x, y, width, height, 0); 
    }

    @Override
    public void update() {
        // Statico - Nessuna logica di aggiornamento necessaria
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.decode("#d4b383")); // Colore ghiaia
        g.fillRect((int) x, (int) y, (int) width - 1, (int) height - 1);
    }
}
