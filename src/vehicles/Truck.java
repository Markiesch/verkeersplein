package vehicles;

import javafx.geometry.Rectangle2D;
import movement.Waypoint;

import java.util.Random;

public class Truck extends Vehicle {
    private static final Random random = new Random();

    private final static int[] positions = { 0, 32, 64, 96, 128 };

    public Truck(Waypoint waypoint) {
        super("/assets/cars.png", waypoint);
        setViewport(new Rectangle2D(positions[random.nextInt(positions.length)], 0, 32, 64));
    }

    @Override
    public double getSpeed() {
        return .5;
    }
}
