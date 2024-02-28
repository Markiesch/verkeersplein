package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class Car extends ImageView {
    private int destinationX = 0;
    private int destinationY = 0;

    public Car(int x, int y) {
        super("/assets/cars.png");
        setViewport(new Rectangle2D(
                0,
                0,
                32,64
        ));
        setX(x);
        setY(y);
    }

    public void update() {
        boolean moveLeft = getX() > destinationX;
        boolean moveRight = getX() < destinationX;
        boolean moveUp = getY() > destinationY;
        boolean moveDown = getY() < destinationY;

        if (moveLeft) setX(getX() - 1);
        if (moveRight) setX(getX() + 1);

        if (moveUp) setY(getY() - 1);
        if (moveDown) setY(getY() + 1);

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
