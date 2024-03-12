package vehicles;

import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import movement.Waypoint;

public abstract class Vehicle extends ImageView {
    private Waypoint waypoint;

    private boolean firstRotation = true;

    /**
     * Get the speed of the vehicle
     * @return the speed of the vehicle
     */
    protected abstract double getSpeed();

    protected Vehicle(String url, Waypoint waypoint) {
        super(url);

        this.waypoint = waypoint;
        setX(waypoint.getX());
        setY(waypoint.getY());

        Rotate rotate = new Rotate();
        rotate.setPivotX(16); // Assuming the vehicle is symmetric
        rotate.setPivotY(0); // Top edge of the vehicle
        getTransforms().add(rotate);
    }

    /**
     * Update the position of the vehicle
     */
    public void update() {
        if (!this.isVisible() || waypoint == null) return;

        double distance = Math.sqrt(Math.pow(getDestinationX() - getX(), 2) + Math.pow(getDestinationY() - getY(), 2));

        if (distance <= 1) {
            waypoint = waypoint.getRandomExit();

            if (waypoint == null) {
                this.setVisible(false);
                return;
            }
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

    public double getDestinationX() {
        return waypoint.getX();
    }

    public double getDestinationY() {
        return waypoint.getY();
    }
}
