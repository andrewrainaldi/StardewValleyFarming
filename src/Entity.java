package src;

import java.awt.Graphics;

public abstract class Entity {

    protected double x;
    protected double y;

    protected double width;
    protected double height;

    protected double speed;

    // COSTRUTTORE CORRETTO
    public Entity(
        double x,
        double y,
        double width,
        double height,
        double speed
    ) {

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.speed = speed;
    }

    // UPDATE BASE
    public void update() {

    }

    // DRAW OBBLIGATORIO
    public abstract void draw(Graphics g);
}
