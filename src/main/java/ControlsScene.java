import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ControlsScene extends VBox {
    public ControlsScene() {

        Label title = new Label("Controls:");

        Button startSimButton = new Button("Start simulation");
        Button stopSimButton = new Button("Stop simulation");
        stopSimButton.setDisable(true);

        startSimButton.setOnAction(e -> {
            startSimButton.setDisable(true);
            stopSimButton.setDisable(false);
            Main.startSim();
        });
        stopSimButton.setOnAction(e -> {
            startSimButton.setDisable(false);
            stopSimButton.setDisable(true);
        });


        getChildren().addAll(title, startSimButton, stopSimButton);
    }
}
