package elements;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import vehicles.Car;
import vehicles.Truck;
import vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class TrafficPane extends Pane {
    private static final int height = 500;
    private static final int width = 500;

    private final List<Vehicle> entities = new ArrayList<>();

    public TrafficPane() {
        Image image = new Image("/assets/grass.png");
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                null,
                new BackgroundSize(40, 40, false, false, false, false)
        );

        setBackground(new Background(backgroundImage));

        setMinWidth(width);
        setMinHeight(height);

        AnimationTimer anim = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Vehicle entity : entities) {
                    entity.update();
                }
            }
        };

        anim.start();
    }

    /**
     * Spawn a new car
     */
    public void spawnCar() {
        Car car = new Car(Math.random() * width, Math.random() * height);
        getChildren().add(car);
        entities.add(car);
    }

    /**
     * Spawn a new car
     */
    public void spawnTruck() {
        Truck truck = new Truck(Math.random() * width, Math.random() * height);
        getChildren().add(truck);
        entities.add(truck);
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
    }
}
