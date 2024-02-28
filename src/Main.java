import elements.TrafficPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        TrafficPane trafficPane = new TrafficPane();

        BorderPane pane = new BorderPane();

        Pane buttonBar = new Pane();
        Button button = new Button("Spawn car");
        button.setPadding(new Insets(20));

        button.setOnAction((e) -> trafficPane.spawnCar());
        buttonBar.getChildren().add(button);

        pane.setCenter(trafficPane);
        pane.setRight(buttonBar);

        Scene scene = new Scene(pane, 500, 500);

        stage.setScene(scene);
        stage.show();
    }
}