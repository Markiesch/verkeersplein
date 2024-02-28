package entities;

import javafx.scene.image.ImageView;

public abstract class Entity extends ImageView {
    private int destinationX = 0;
    private int destinationY = 0;

    protected abstract double getSpeed();

    protected Entity(String url) {
        super(url);
    }

    public void update() {
        boolean moveLeft = getX() > destinationX;
        boolean moveRight = getX() < destinationX;
        boolean moveUp = getY() > destinationY;
        boolean moveDown = getY() < destinationY;

        if (moveLeft) setX(getX() - getSpeed());
        if (moveRight) setX(getX() + getSpeed());
        if (moveUp) setY(getY() - getSpeed());
        if (moveDown) setY(getY() + getSpeed());

        if (moveLeft && moveDown) setRotate(225);
        else if (moveLeft && moveUp) setRotate(315);
        else if (moveRight && moveDown) setRotate(135);
        else if (moveRight && moveUp) setRotate(45);
        else if (moveLeft) setRotate(270);
        else if (moveRight) setRotate(90);
        else if (moveDown) setRotate(180);
        else if (moveUp) setRotate(0);
    }

    public int getDestinationX() {
        return destinationX;
    }

    public void setDestinationX(int destinationX) {
        this.destinationX = destinationX;
    }

    public int getDestinationY() {
        return destinationY;
    }

    public void setDestinationY(int destinationY) {
        this.destinationY = destinationY;
    }
}
