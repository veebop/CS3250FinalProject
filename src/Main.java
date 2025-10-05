import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene controls = new Scene(new ControlsScene());

        stage.setScene(controls);
        stage.show();
    }

    public static void startSim() {
        // TODO: properly implement this
        SandPixel testSand = new SandPixel(0, 0, 0);
        for (int i  = 0; i < 10; i++) {
            testSand.move();
        }
    }
}
