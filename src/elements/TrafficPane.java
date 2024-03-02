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
    private static final double INNER_RADIUS = 50;
    private static final double ROUND_ABOUT_RADIUS = 100;
    private static final double ROAD_SIZE = 100;

    private static final double height = 750;
    private static final double width = 750;

    private final List<Vehicle> entities = new ArrayList<>();

    public TrafficPane() {
        Rectangle roadX = new Rectangle(width, ROAD_SIZE, ROAD_COLOR);
        roadX.setY(height / 2 - ROAD_SIZE / 2);

        Rectangle roadY = new Rectangle(ROAD_SIZE, height, ROAD_COLOR);
        roadY.setX(width / 2 - ROAD_SIZE / 2);


        getChildren().addAll(
                roadX,
                roadY,
                new Circle(width / 2, height / 2, ROUND_ABOUT_RADIUS + INNER_RADIUS, ROAD_COLOR),
                new Circle(width / 2, height / 2, INNER_RADIUS, BG_COLOR)
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
        WaypointSequence waypointSequence = new WaypointSequence(
                // Left (start)
                new Waypoint(0, height / 2 + ROAD_SIZE / 4),
                new Waypoint((width / 2) - ROUND_ABOUT_RADIUS, (height / 2) + ROAD_SIZE / 4),

                // Bottom
                new Waypoint(width / 2 - ROAD_SIZE / 4, height / 2 + ROUND_ABOUT_RADIUS),
                new Waypoint(width / 2 + ROAD_SIZE / 4, height / 2 + ROUND_ABOUT_RADIUS),

                // Right
                new Waypoint((width / 2) + ROUND_ABOUT_RADIUS, (height / 2) + ROAD_SIZE / 4),
                new Waypoint((width / 2) + ROUND_ABOUT_RADIUS, (height / 2) - ROAD_SIZE / 4),

                // Top
                new Waypoint(width / 2 + ROAD_SIZE / 4, height / 2 - ROUND_ABOUT_RADIUS),
                new Waypoint(width / 2 - ROAD_SIZE / 4, height / 2 - ROUND_ABOUT_RADIUS),

                // Left (end)
                new Waypoint((width / 2) - ROUND_ABOUT_RADIUS, (height / 2) - ROAD_SIZE / 4),
                new Waypoint(0, height / 2 - ROAD_SIZE / 4)
        );

        // Debug waypoints
        for (Waypoint waypoint : waypointSequence.getWaypoints()) {
            new Rectangle(5, 5, Color.RED) {{
                setX(waypoint.getX());
                setY(waypoint.getY());
                getChildren().add(this);
            }};
        }

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
