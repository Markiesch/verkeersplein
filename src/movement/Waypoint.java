package movement;

import java.util.Random;

public class Waypoint {
    private static final Random random = new Random();

    private final double x;
    private final double y;

    private final Waypoint[] waypoints;

    public Waypoint(double x, double y, Waypoint ...exits) {
        this.x = x;
        this.y = y;

        waypoints = exits;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Waypoint getRandomExit() {
        return waypoints[random.nextInt(waypoints.length)];
    }
}
