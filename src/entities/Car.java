package entities;

import javafx.geometry.Rectangle2D;

public class Car extends Entity {
    public Car(int x, int y) {
        super("/assets/cars.png");
        setX(x);
        setY(y);

        setViewport(new Rectangle2D(0, 0, 32,64));
    }

    @Override
    public double getSpeed() {
        return 1;
    }
}
