package learn.mt.cpjdpp.particles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Particle {
    private final Random rng = new Random();
    private final Circle circle;
    private int x;
    private int y;

    public Particle(int x, int y) {
        this.circle = new Circle(0, 0, 10, Color.BLUE);
        this.x = x;
        this.y = y;
        setCircleCenter();
    }

    public Circle getCircle() {
        return circle;
    }

    public synchronized void move() {
        x += rng.nextInt(10) - 5;
        y += rng.nextInt(20) - 10;
    }

    public void draw() {
        setCircleCenter();
    }

    private void setCircleCenter() {
        int lx;
        int ly;
        synchronized (this) {
            lx = x;
            ly = y;
        }
        circle.setCenterX(lx);
        circle.setCenterY(ly);
    }
}
