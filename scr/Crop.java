package scr;

import java.awt.Color;
import java.awt.Graphics;

public class Crop extends Plantation {

    private String cropName;
    private int sellPrice;

    public Crop(double x, double y, String cropName) {

        // x, y, width, height, maxGrowthStage, tempo crescita
        super(x, y, 48, 48, 4, 3000);

        this.cropName = cropName.toLowerCase();

        switch (this.cropName) {

            case "tomato":
                sellPrice = 50;
                break;

            case "corn":
                sellPrice = 80;
                break;

            case "pumpkin":
                sellPrice = 120;
                break;

            default:
                sellPrice = 30;
                break;
        }
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

        switch (cropName) {

            case "tomato":
                drawTomato(g);
                break;

            case "corn":
                drawCorn(g);
                break;

            case "pumpkin":
                drawPumpkin(g);
                break;

            default:
                drawGeneric(g);
                break;
        }

        // Bordo tile
        g.setColor(Color.BLACK);
        g.drawRect((int)x, (int)y, (int)width, (int)height);
    }

    private void drawTomato(Graphics g) {

        g.setColor(Color.GREEN);

        if (growthStage == 0) {

            g.fillOval((int)x + 20, (int)y + 20, 8, 8);

        } else if (growthStage == 1) {

            g.fillRect((int)x + 22, (int)y + 16, 4, 16);

        } else if (growthStage == 2) {

            g.fillRect((int)x + 22, (int)y + 10, 4, 22);

            g.setColor(Color.RED);
            g.fillOval((int)x + 14, (int)y + 18, 10, 10);
            g.fillOval((int)x + 24, (int)y + 18, 10, 10);

        } else {

            g.fillRect((int)x + 22, (int)y + 6, 4, 28);

            g.setColor(Color.RED);

            g.fillOval((int)x + 10, (int)y + 14, 12, 12);
            g.fillOval((int)x + 26, (int)y + 14, 12, 12);
            g.fillOval((int)x + 18, (int)y + 24, 12, 12);
        }
    }

    private void drawCorn(Graphics g) {

        g.setColor(Color.GREEN);

        if (growthStage == 0) {

            g.fillRect((int)x + 22, (int)y + 22, 4, 8);

        } else if (growthStage == 1) {

            g.fillRect((int)x + 20, (int)y + 16, 8, 16);

        } else if (growthStage == 2) {

            g.fillRect((int)x + 18, (int)y + 10, 12, 24);

            g.setColor(Color.YELLOW);
            g.fillRect((int)x + 18, (int)y + 18, 12, 10);

        } else {

            g.fillRect((int)x + 16, (int)y + 4, 16, 34);

            g.setColor(Color.YELLOW);
            g.fillRect((int)x + 14, (int)y + 14, 20, 14);
        }
    }

    private void drawPumpkin(Graphics g) {

        if (growthStage == 0) {

            g.setColor(Color.GREEN);
            g.fillOval((int)x + 20, (int)y + 20, 8, 8);

        } else if (growthStage == 1) {

            g.setColor(Color.ORANGE);
            g.fillOval((int)x + 16, (int)y + 16, 16, 16);

        } else if (growthStage == 2) {

            g.setColor(Color.ORANGE);
            g.fillOval((int)x + 10, (int)y + 14, 28, 20);

        } else {

            g.setColor(new Color(255, 140, 0));
            g.fillOval((int)x + 6, (int)y + 10, 36, 24);

            g.setColor(Color.GREEN);
            g.fillRect((int)x + 22, (int)y + 4, 4, 10);
        }
    }

    private void drawGeneric(Graphics g) {

        g.setColor(Color.GREEN);

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

    public String getCropName() {
        return cropName;
    }

    public int getSellPrice() {
        return sellPrice;
    }
}