package entities;

import javafx.geometry.Rectangle2D;

import java.util.Random;

public class Car extends Vehicle {
    private static final Random random = new Random();

    private final static int[] positions = { 0, 32, 64, 96, 128 };

    public Car(int x, int y) {
        super("/assets/cars.png");
        setX(x);
        setY(y);

        setViewport(new Rectangle2D(positions[random.nextInt(positions.length)], 0, 32, 64));
    }

    @Override
    public double getSpeed() {
        return 1;
    }
}
