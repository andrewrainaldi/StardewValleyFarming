package scr;


public class Dog extends Entity {

    void draw(GraphicsContext gc) {
        gc.setFill(Color.BROWN);
        gc.fillRect((double) this.getX(), (double) this.getY(), (double) this.getWidth(), (double) this.getHeight());
    }
}

class Entity {
    public double getX() { return 100.0; }
    public double getY() { return 100.0; }
    public double getWidth() { return 50.0; }
    public double getHeight() { return 50.0; }
}

class GraphicsContext {
    public void setFill(Object color) {}
    public void fillRect(double x, double y, double w, double h) {}
}

class Color {
    public static Object BROWN = new Object();
}