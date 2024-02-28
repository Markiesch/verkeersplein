package elements;

import entities.Car;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Pane;

public class TrafficPane extends Pane {
    public TrafficPane() {
        Image image = new Image("/assets/grass.png");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, null, null);

        setBackground(new Background(backgroundImage));

        Car car = new Car(5, 5);
        car.setDestinationX(100);
        car.setDestinationY(200);
        this.getChildren().add(car);

        AnimationTimer anim = new AnimationTimer() {
            @Override
            public void handle(long now) {
                car.update();
            }
        };

        anim.start();
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
    }
}
