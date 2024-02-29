package elements;

import entities.Car;
import entities.Vehicle;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class TrafficPane extends Pane {
    private final List<Vehicle> entities = new ArrayList<>();

    public TrafficPane() {
        Image image = new Image("/assets/grass.png");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, null, null);

        setBackground(new Background(backgroundImage));


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

    public void spawnCar() {
        Car car = new Car(5, 5);
        car.setDestinationX(100);
        car.setDestinationY(200);
        getChildren().add(car);
        entities.add(car);
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
    }
}
