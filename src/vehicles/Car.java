package vehicles;

import javafx.geometry.Rectangle2D;

import java.util.Random;

public class Car extends Vehicle {
    private static final Random random = new Random();

    private final static int[] positions = { 0, 32, 64, 96, 128 };

    public Car(double x, double y) {
        super("/assets/cars.png", x, y);
        setViewport(new Rectangle2D(positions[random.nextInt(positions.length)], 0, 32, 64));
    }

    @Override
    public double getSpeed() {
        return 1;
    }
}
