package elements;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import movement.Waypoint;
import movement.WaypointSequence;
import vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class TrafficPane extends Pane {
    private static final Color ROAD_COLOR = Color.rgb(58, 47, 58);
    private static final Color BG_COLOR = Color.rgb(25, 22, 25);
    private static final double ROAD_SIZE = 75;

    private static final double height = 500;
    private static final double width = 500;

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
        Rectangle roadX = new Rectangle(width, ROAD_SIZE, ROAD_COLOR);
        roadX.setY(height / 2 - ROAD_SIZE / 2);

        Rectangle roadY = new Rectangle(ROAD_SIZE, height, ROAD_COLOR);
        roadY.setX(width / 2 - ROAD_SIZE / 2);


        getChildren().addAll(
                roadX,
                roadY,
                new Circle(width / 2, height / 2, 125, ROAD_COLOR),
                new Circle(width / 2, height / 2, 50, BG_COLOR)
        );

        setBackground(new Background(new BackgroundFill(BG_COLOR, null, null)));

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

        setOnMouseClicked(e -> {
            System.out.println(e.getX() + ", " + e.getY());
        });
    }


    public void spawnVehicle(Class<? extends Vehicle> clazz) {
        try {
            Vehicle vehicle = clazz.getConstructor(WaypointSequence.class).newInstance(waypointSequence);
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
