package vehicles;

import javafx.geometry.Rectangle2D;
import movement.WaypointSequence;

import java.util.Random;

public class Car extends Vehicle {
    private static final double CAR_WIDTH = 32;
    private static final double CAR_HEIGHT = 64;
    private static final Random random = new Random();

    private final static int[] positions = { 0, 32, 64, 96, 128 };

    public Car(WaypointSequence waypointSequence) {
        super("/assets/cars.png", waypointSequence);
        setViewport(new Rectangle2D(positions[random.nextInt(positions.length)], 0, CAR_WIDTH, CAR_HEIGHT));
        setTranslateX(-(CAR_WIDTH / 2));
        setTranslateY(-(CAR_HEIGHT / 2));
    }

    @Override
    public double getSpeed() {
        return 1;
    }
}
