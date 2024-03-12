package elements;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import movement.Waypoint;
import movement.WaypointManager;
import vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static util.Config.*;

public class TrafficPane extends Pane {
    private static final Color ROAD_COLOR = Color.rgb(58, 47, 58);
    private static final Color BG_COLOR = Color.rgb(25, 22, 25);


    private final List<Vehicle> entities = new ArrayList<>();

    public TrafficPane() {
        Rectangle roadX = new Rectangle(WORLD_WIDTH, ROAD_SIZE, ROAD_COLOR);
        roadX.setY(WORLD_HEIGHT / 2 - ROAD_SIZE / 2);
        Rectangle roadY = new Rectangle(ROAD_SIZE, WORLD_HEIGHT, ROAD_COLOR);
        roadY.setX(WORLD_WIDTH / 2 - ROAD_SIZE / 2);

        getChildren().addAll(
                roadX,
                roadY,
                new Circle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, ROUND_ABOUT_RADIUS + INNER_RADIUS, ROAD_COLOR),
                new Circle(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, INNER_RADIUS, BG_COLOR)
        );

        setBackground(new Background(new BackgroundFill(BG_COLOR, null, null)));

        setMinWidth(WORLD_WIDTH);
        setMinHeight(WORLD_HEIGHT);

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
            Vehicle vehicle = clazz.getConstructor(Waypoint.class).newInstance(WaypointManager.getInstance().getRandomStartingWaypoint());
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
