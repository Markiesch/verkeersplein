package movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class Waypoint {
    private static final Random random = new Random();

    private final double x;
    private final double y;

    private final List<Waypoint> exits;

    public Waypoint(double x, double y) {
        this.x = x;
        this.y = y;
        exits = new ArrayList<>();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void addExit(Waypoint ...exits) {
        this.exits.addAll(Arrays.asList(exits));
    }

    public Waypoint getRandomExit() {
        if (exits.isEmpty()) return null;
        return exits.get(random.nextInt(exits.size()));
    }
}
