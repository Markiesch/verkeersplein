package elements;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import movement.Waypoint;
import movement.WaypointSequence;
import vehicles.Car;
import vehicles.Truck;
import vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class TrafficPane extends Pane {
    private static final int height = 500;
    private static final int width = 500;

    WaypointSequence waypointSequence = new WaypointSequence(
            new Waypoint(0, 250),
            new Waypoint(175, 250),
            new Waypoint(250, 325),
            new Waypoint(375, 250),
            new Waypoint(250, 125),
            new Waypoint(250, 0)
    );

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


    public void spawnVehicle(Class<? extends Vehicle> clazz) {
        try {
            Vehicle vehicle = clazz.getDeclaredConstructor(WaypointSequence.class).newInstance(waypointSequence);
            getChildren().add(vehicle);
            entities.add(vehicle);
        } catch (Exception e) {
            System.out.println("Failed to spawn vehicle");
        }
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
    }
}
