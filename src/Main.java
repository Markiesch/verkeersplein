import elements.TrafficPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import vehicles.Car;
import vehicles.Truck;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        TrafficPane trafficPane = new TrafficPane();

        BorderPane pane = new BorderPane();

        Button createCarButton = new Button("Spawn car");
        createCarButton.setPadding(new Insets(20));
        createCarButton.setOnAction((e) -> trafficPane.spawnVehicle(Car.class));

        Button createTruckButton = new Button("Spawn truck");
        createTruckButton.setPadding(new Insets(20));
        createTruckButton.setOnAction((e) -> trafficPane.spawnVehicle(Truck.class));

        FlowPane buttonBar = new FlowPane(Orientation.VERTICAL);
        buttonBar.getChildren().addAll(createCarButton, createTruckButton);

        pane.setCenter(trafficPane);
        pane.setRight(buttonBar);

        Scene scene = new Scene(pane);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}