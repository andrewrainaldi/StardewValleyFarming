package scr;

import java.awt.Color;
import java.awt.Graphics; // Importato Graphics generico richiesto dalla superclasse
import java.awt.Graphics2D;

/**
 * Crop eredita tutte le meccaniche da Plantation e DESCRIVE COME QUALSIASI PIANTA FUNZIONA
 */
public class Crop extends Plantation {

    private final String cropName; // AGGIUNTO 'final' (Risolve l'hint 1: Field sellPrice can be final)
    private final int sellPrice;  // AGGIUNTO 'final' per sicurezza sul prezzo fisso

    public Crop(double x, double y, String cropName) {
        super( x, y, 48, 48, 4, 3000 );

        this.cropName = cropName;

        // Uso dello switch expression moderno
        this.sellPrice = switch (cropName.toLowerCase()) {
            case "tomato" -> 50;
            case "corn" -> 80;
            case "pumpkin" -> 120;
            default -> 30;
        };
    }

    @Override
    public void update() {
        super.update();
    }

    // RISOLVE L'ERRORE PRINCIPALE: Accetta 'Graphics' come vuole la classe padre,
    // e fa il cast a Graphics2D all'interno per mantenere i disegni precisi.
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // ===== TERRA =====
        g2.setColor(new Color(139, 69, 19)); // SADDLEBROWN
        g2.fillRect((int)x, (int)y, (int)width - 1, (int)height - 1);

        // ===== DISEGNO PIANTA =====
        switch (cropName.toLowerCase()) {
            case "tomato" -> drawTomato(g2);
            case "corn" -> drawCorn(g2);
            case "pumpkin" -> drawPumpkin(g2);
            default -> drawGeneric(g2);
        }

        // contorno tile
        g2.setColor(Color.decode("#4a3220"));
        g2.drawRect((int)x, (int)y, (int)width - 1, (int)height - 1);
    }

    private void drawTomato(Graphics2D g2) {
        // Trasformato in switch per risolvere l'hint sui "chain of ifs"
        switch (growthStage) {
            case 0 -> {
                g2.setColor(Color.GREEN);
                g2.fillOval((int)x + 20, (int)y + 20, 8, 8);
            }
            case 1 -> {
                g2.setColor(Color.GREEN);
                g2.fillOval((int)x + 18, (int)y + 18, 12, 12);
            }
            case 2 -> {
                g2.setColor(Color.GREEN);
                g2.fillRect((int)x + 22, (int)y + 14, 4, 20);
                g2.setColor(Color.RED);
                g2.fillOval((int)x + 12, (int)y + 24, 10, 10);
                g2.fillOval((int)x + 26, (int)y + 24, 10, 10);
            }
            default -> {
                g2.setColor(Color.GREEN);
                g2.fillRect((int)x + 22, (int)y + 8, 4, 30);
                g2.setColor(Color.RED);
                g2.fillOval((int)x + 10, (int)y + 20, 12, 12);
                g2.fillOval((int)x + 26, (int)y + 20, 12, 12);
                g2.fillOval((int)x + 18, (int)y + 12, 12, 12);
            }
        }
    }

    private void drawCorn(Graphics2D g2) {
        g2.setColor(Color.GREEN);
        // Trasformato in switch per risolvere l'hint sui "chain of ifs"
        switch (growthStage) {
            case 0 -> g2.fillRect((int)x + 22, (int)y + 22, 4, 10);
            case 1 -> g2.fillRect((int)x + 20, (int)y + 14, 8, 20);
            case 2 -> {
                g2.fillRect((int)x + 18, (int)y + 8, 12, 30);
                g2.setColor(Color.YELLOW);
                g2.fillRect((int)x + 16, (int)y + 18, 16, 10);
            }
            default -> {
                g2.fillRect((int)x + 16, (int)y + 4, 16, 38);
                g2.setColor(new Color(255, 215, 0)); // GOLD
                g2.fillRect((int)x + 12, (int)y + 16, 24, 14);
            }
        }
    }

    private void drawPumpkin(Graphics2D g2) {
        // Trasformato in switch per risolvere l'hint sui "chain of ifs"
        switch (growthStage) {
            case 0 -> {
                g2.setColor(Color.GREEN);
                g2.fillOval((int)x + 20, (int)y + 20, 8, 8);
            }
            case 1 -> {
                g2.setColor(Color.ORANGE);
                g2.fillOval((int)x + 16, (int)y + 16, 16, 16);
            }
            case 2 -> {
                g2.setColor(Color.ORANGE);
                g2.fillOval((int)x + 10, (int)y + 14, 28, 22);
            }
            default -> {
                g2.setColor(new Color(255, 140, 0)); // DARKORANGE
                g2.fillOval((int)x + 6, (int)y + 12, 36, 26);
                g2.setColor(Color.GREEN);
                g2.fillRect((int)x + 22, (int)y + 6, 4, 10);
            }
        }
    }

    private void drawGeneric(Graphics2D g2) {
        g2.setColor(Color.GREEN);
        int size = 8 + (growthStage * 6);
        g2.fillOval((int)(x + width / 2 - size / 2), (int)(y + height / 2 - size / 2), size, size);
    }

    @Override
    public void harvest() {
        if (harvestable) {
            System.out.println("Hai raccolto " + cropName + " e guadagnato " + sellPrice + " gold!");
            destroy();
        }
    }

    public String getCropName() { return cropName; }
    public int getSellPrice() { return sellPrice; }
}