package scr;
import java.awt.Graphics;

public abstract class Entity{
protected double x, y;
protected double speed;
protected double width;
protected double height;

    public Entity(double x, double y, double width, double height, double speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    // Metodo base per aggiornare la logica (da sovrascrivere se serve)
    public void update() {
      
    }

    // Metodo per disegnare l'entità
    public abstract void draw(Graphics g);
}
