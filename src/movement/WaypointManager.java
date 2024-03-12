package movement;

import java.util.Random;

import static util.Config.*;

public final class WaypointManager {
    private final Random random = new Random();

    private final Waypoint topLeft = new Waypoint(WORLD_WIDTH / 2 - ROAD_SIZE / 4, WORLD_HEIGHT / 2 - ROUND_ABOUT_RADIUS);
    private final Waypoint topRight = new Waypoint(WORLD_WIDTH / 2 + ROAD_SIZE / 4, WORLD_HEIGHT / 2 - ROUND_ABOUT_RADIUS);

    private final Waypoint rightTop = new Waypoint((WORLD_WIDTH / 2) + ROUND_ABOUT_RADIUS, (WORLD_HEIGHT / 2) - ROAD_SIZE / 4);
    private final Waypoint rightBottom = new Waypoint((WORLD_WIDTH / 2) + ROUND_ABOUT_RADIUS, (WORLD_HEIGHT / 2) + ROAD_SIZE / 4);

    private final Waypoint bottomLeft = new Waypoint(WORLD_WIDTH / 2 - ROAD_SIZE / 4, WORLD_HEIGHT / 2 + ROUND_ABOUT_RADIUS);
    private final Waypoint bottomRight = new Waypoint(WORLD_WIDTH / 2 + ROAD_SIZE / 4, WORLD_HEIGHT / 2 + ROUND_ABOUT_RADIUS);

    private final Waypoint leftBottom = new Waypoint(
            (WORLD_WIDTH / 2) - ROUND_ABOUT_RADIUS,
            (WORLD_HEIGHT / 2) + ROAD_SIZE / 4);
        private final Waypoint leftTop = new Waypoint(
                (WORLD_WIDTH / 2) - ROUND_ABOUT_RADIUS,
                (WORLD_HEIGHT / 2) - ROAD_SIZE / 4
        );

    private final Waypoint entranceTop = new Waypoint(WORLD_WIDTH / 2 - ROAD_SIZE / 4, 0);
    private final Waypoint exitTop = new Waypoint(WORLD_WIDTH / 2 + ROAD_SIZE / 4, 0);

    private final Waypoint entranceRight = new Waypoint(WORLD_WIDTH, WORLD_HEIGHT / 2 - ROAD_SIZE / 4);
    private final Waypoint exitRight = new Waypoint(WORLD_WIDTH, WORLD_HEIGHT / 2 + ROAD_SIZE / 4);

    private final Waypoint entranceBottom = new Waypoint(WORLD_WIDTH / 2 + ROAD_SIZE / 4, WORLD_HEIGHT);
    private final Waypoint exitBottom = new Waypoint(WORLD_WIDTH / 2 - ROAD_SIZE / 4, WORLD_HEIGHT);

    private final Waypoint entranceLeft = new Waypoint(0, WORLD_HEIGHT / 2 + ROAD_SIZE / 4);
    private final Waypoint exitLeft = new Waypoint(0, WORLD_HEIGHT / 2 - ROAD_SIZE / 4);


    private WaypointManager() {
        initExits();
    }

    private void initExits() {
        entranceLeft.addExit(leftBottom);
        entranceBottom.addExit(bottomRight);
        entranceRight.addExit(rightTop);
        entranceTop.addExit(topLeft);

        leftBottom.addExit(bottomLeft);
        bottomLeft.addExit(exitBottom, bottomRight);
        bottomRight.addExit(rightBottom);
        rightBottom.addExit(exitRight, rightTop);
        rightTop.addExit(topRight);
        topRight.addExit(exitTop, topLeft);
        topLeft.addExit(leftTop);
        leftTop.addExit(exitLeft, leftBottom);
    }

    public static WaypointManager getInstance() {
        return WaypointManagerHolder.INSTANCE;
    }

    private static class WaypointManagerHolder {
        private static final WaypointManager INSTANCE = new WaypointManager();
    }

    public Waypoint getRandomStartingWaypoint() {
        Waypoint[] startingWaypoints = { entranceTop, entranceRight, entranceBottom, entranceLeft };

        return startingWaypoints[random.nextInt(startingWaypoints.length)];
    }
}
