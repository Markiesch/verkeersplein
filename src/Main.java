import elements.TrafficPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        TrafficPane trafficPane = new TrafficPane();

        Scene scene = new Scene(trafficPane, 500, 500);

        stage.setScene(scene);
        stage.show();
    }
}