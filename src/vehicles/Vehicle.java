package vehicles;

import javafx.scene.image.ImageView;
import movement.Waypoint;
import movement.WaypointSequence;

public abstract class Vehicle extends ImageView {
    private final WaypointSequence sequence;

    private double destinationX = 0;
    private double destinationY = 0;

    /**
     * Get the speed of the vehicle
     * @return the speed of the vehicle
     */
    protected abstract double getSpeed();

    protected Vehicle(String url, WaypointSequence waypointSequence) {
        super(url);

        this.sequence = waypointSequence;
        setNextDestination();
        setX(this.destinationX);
        setY(this.destinationY);
    }

    private void setNextDestination() {
        Waypoint waypoint = sequence.next();
        setDestinationX(waypoint.getX());
        setDestinationY(waypoint.getY());
    }

    /**
     * Update the position of the vehicle
     */
    public void update() {
        if (!this.isVisible()) return;

        double distance = Math.sqrt(Math.pow(getDestinationX() - getX(), 2) + Math.pow(getDestinationY() - getY(), 2));

        if (distance <= 1) {
            Waypoint waypoint = sequence.next();

            if (waypoint == null) {
                this.setVisible(false);
                return;
            }

            setDestinationX(waypoint.getX());
            setDestinationY(waypoint.getY());

        }

        if (distance > 1) {
            double dx = (getDestinationX() - getX()) / distance;
            double dy = (getDestinationY() - getY()) / distance;
            setX(getX() + dx * getSpeed());
            setY(getY() + dy * getSpeed());
        }

        double angle = Math.toDegrees(Math.atan2(getDestinationY() - getY(), getDestinationX() - getX()));
        setRotate(angle + 90);
    }

    /**
     * Get the x coordinate of the destination
     * @return the x coordinate of the destination
     */
    public double getDestinationX() {
        return destinationX;
    }

    /**
     * Set the x coordinate of the destination
     * @param destinationX the x coordinate of the destination
     */
    public void setDestinationX(double destinationX) {
        this.destinationX = destinationX;
    }

    /**
     * Get the y coordinate of the destination
     * @return the y coordinate of the destination
     */
    public double getDestinationY() {
        return destinationY;
    }

    /**
     * Set the y coordinate of the destination
     * @param destinationY the y coordinate of the destination
     */
    public void setDestinationY(double destinationY) {
        this.destinationY = destinationY;
    }
}
