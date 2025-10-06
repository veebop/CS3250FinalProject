import java.util.Map;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private static Simulation sim;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        sim = new Simulation(50, 50);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(new SimulationCanvas(sim), new ControlsScene());

        Scene scene = new Scene(hbox, 1000, 1000, Color.WHITESMOKE);
        stage.setScene(scene);
        stage.show();
    }

    public static void startSim() {
        for (Pixel pixel : sim.getPixels().values()) {
            System.out.println(pixel);
        }
    }
}
