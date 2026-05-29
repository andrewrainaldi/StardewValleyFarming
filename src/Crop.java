package src;

import java.awt.Color;
import java.awt.Graphics;

public class Crop extends Plantation {

    private String cropName;
    private final int sellPrice;

    public Crop(double x, double y, String cropName) {

        // x, y, width, height, maxGrowthStage, tempo crescita
        super(x, y, 48, 48, 4, 3000);

        this.cropName = cropName.toLowerCase();

        // Soluzione Hint 4: Convertito in switch expression moderno per eliminare il warning a riga 16
        int price = 30;
        switch (this.cropName) {
            case "tomato":
                price = 50;
                break;
            case "corn":
                price = 80;
                break;
            case "pumpkin":
                price = 120;
                break;
        }
        this.sellPrice = price;
    }

    @Override
    public void update() {
        super.update();
    }
    
    @Override
    public void draw(Graphics g) {

        // Terra
        g.setColor(new Color(139, 69, 19));
        g.fillRect((int)x, (int)y, (int)width, (int)height);

        // Soluzione Hint 5: Convertito in rule switch moderno per eliminare il warning a riga 48
        switch (cropName) {
            case "tomato" -> drawTomato(g);
            case "corn" -> drawCorn(g);
            case "pumpkin" -> drawPumpkin(g);
            default -> drawGeneric(g);
        }

        // Bordo tile
        g.setColor(Color.decode("#000509"));
        g.drawRect((int)x, (int)y, (int)width, (int)height);
    }

    private void drawTomato(Graphics g) {

        g.setColor(Color.decode("#216324"));

        // Soluzione Hint 1: Sostituita la catena di if/else con uno switch basato su growthStage (riga 76)
        switch (growthStage) {
            case 0 -> g.fillOval((int)x + 20, (int)y + 20, 8, 8);
            case 1 -> g.fillRect((int)x + 22, (int)y + 16, 4, 16);
            case 2 -> {
                g.fillRect((int)x + 22, (int)y + 10, 4, 22);
                g.setColor(Color.decode("#72151a"));
                g.fillOval((int)x + 14, (int)y + 18, 10, 10);
                g.fillOval((int)x + 24, (int)y + 18, 10, 10);
                harvestable = true;
            }
        }
    }

    private void drawCorn(Graphics g) {

        g.setColor(Color.decode("#216324"));

        // Soluzione Hint 2: Sostituita la catena di if/else con uno switch basato su growthStage (riga 108)
        switch (growthStage) {
            case 0 -> g.fillRect((int)x + 22, (int)y + 22, 4, 8);
            case 1 -> g.fillRect((int)x + 20, (int)y + 16, 8, 16);
            case 2 -> {
                g.fillRect((int)x + 18, (int)y + 10, 12, 24);
                g.setColor(Color.decode("#FFFF00"));
                g.fillRect((int)x + 18, (int)y + 18, 12, 10);
                harvestable = true;
            }
        }
    }

    private void drawPumpkin(Graphics g) {

       
        switch (growthStage) {
            case 0 -> {
                g.setColor(Color.decode("#216324"));
                g.fillOval((int)x + 20, (int)y + 20, 8, 8);
            }
            case 1 -> {
                g.setColor(Color.decode("#FFA500"));
                g.fillOval((int)x + 16, (int)y + 16, 16, 16);
            }
            case 2 -> {
                g.setColor(Color.decode("#FFA500"));
                g.fillOval((int)x + 10, (int)y + 14, 28, 20);
                harvestable = true;
            }
        }
    }

    private void drawGeneric(Graphics g) {

        g.setColor(Color.decode("#216324"));

        int size = 8 + growthStage * 6;

        g.fillOval(
            (int)(x + width / 2 - size / 2),
            (int)(y + height / 2 - size / 2),
            size,
            size
        );
    }

    @Override
    public void harvest() {

        if (harvestable) {

            System.out.println(
                "Hai raccolto " +
                cropName +
                " e guadagnato " +
                sellPrice +
                " gold!"
            );

            destroy();
        }
    }
}