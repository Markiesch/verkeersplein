package movement;

public class WaypointSequence {
    private Waypoint[] waypoints;
    private int index = 0;

    public WaypointSequence(Waypoint ...waypoints) {
        this.waypoints = waypoints;
    }

    public Waypoint next() {
        if (index >= waypoints.length) return null;
        return waypoints[index++];
    }

    public Waypoint[] getWaypoints() {
        return waypoints;
    }
}
