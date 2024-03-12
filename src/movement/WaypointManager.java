package movement;

import static util.Config.*;

public final class WaypointManager {
    private static final Waypoint exitTop = new Waypoint(0, 0);
    private static final Waypoint exitRight = new Waypoint(0, 0);
    private static final Waypoint exitBottom = new Waypoint(0, 0);
    private static final Waypoint exitLeft = new Waypoint(0, 0);

    private static final Waypoint topLeft = new Waypoint(0, 0);
    private static final Waypoint topRight = new Waypoint(0, 0);
    private static final Waypoint rightTop = new Waypoint(0, 0);
    private static final Waypoint rightBottom = new Waypoint(0, 0);
    private static final Waypoint bottomLeft = new Waypoint(0, 0);
    private static final Waypoint bottomRight = new Waypoint(0, 0);
    private static final Waypoint leftBottom = new Waypoint(
            (WORLD_WIDTH / 2) - ROUND_ABOUT_RADIUS,
            (WORLD_HEIGHT / 2) + ROAD_SIZE / 4,
            bottomLeft
    );
        private static final Waypoint leftTop = new Waypoint(
                (WORLD_WIDTH / 2) - ROUND_ABOUT_RADIUS,
                (WORLD_HEIGHT / 2) - ROAD_SIZE / 4,
                exitLeft,
                leftBottom
        );

    private static final Waypoint entranceTop = new Waypoint(0, 0, topLeft);
    private static final Waypoint entranceRight = new Waypoint(0, 0, rightTop);
    private static final Waypoint entranceBottom = new Waypoint(0, 0, bottomRight);
    private static final Waypoint entranceLeft = new Waypoint(0, 0, leftBottom);


    public static Waypoint[] getStartingWaypoints() {
        return new Waypoint[] { entranceTop, entranceRight, entranceBottom, entranceLeft };
    }
}
