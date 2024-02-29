package vehicles;

import javafx.scene.image.ImageView;

public abstract class Vehicle extends ImageView {
    private double destinationX = 0;
    private double destinationY = 0;

    /**
     * Get the speed of the vehicle
     * @return the speed of the vehicle
     */
    protected abstract double getSpeed();

    protected Vehicle(String url, double x, double y) {
        super(url);
        setX(x);
        setY(y);
        setDestinationX(x);
        setDestinationY(y);
    }

    /**
     * Update the position of the vehicle
     */
    public void update() {
        double distance = Math.sqrt(Math.pow(destinationX - getX(), 2) + Math.pow(destinationY - getY(), 2));

        if (distance <= 1) {
            setDestinationX(Math.random() * 500);
            setDestinationY(Math.random() * 500);
        }

        if (distance > 1) {
            double dx = (destinationX - getX()) / distance;
            double dy = (destinationY - getY()) / distance;
            setX(getX() + dx * getSpeed());
            setY(getY() + dy * getSpeed());
        }

        double angle = Math.toDegrees(Math.atan2(destinationY - getY(), destinationX - getX()));
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
