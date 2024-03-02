package vehicles;

import javafx.geometry.Point3D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Rotate;
import movement.Waypoint;
import movement.WaypointSequence;

public abstract class Vehicle extends ImageView {
    private final WaypointSequence sequence;

    private double destinationX = 0;
    private double destinationY = 0;

    private boolean firstRotation = true;

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

        Rotate rotate = new Rotate();
        rotate.setPivotX(16); // Assuming the vehicle is symmetric
        rotate.setPivotY(0); // Top edge of the vehicle
        getTransforms().add(rotate);
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
        rotateTo(angle);
    }

    private void rotateTo(double angle) {
        double speed = 0.75;

        double delta = (angle - (getRotate() - 90) + 180) % 360 - 180;
        double newRotation = angle;

        if (firstRotation) {
            newRotation = angle;
            firstRotation = false;
        } else if (delta > speed) {
            newRotation = (getRotate() - 90) + speed;
        } else if (delta < -speed) {
            newRotation = (getRotate() - 90) - speed;
        }

        setRotate(newRotation + 90);
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
