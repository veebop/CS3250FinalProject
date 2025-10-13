import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * This class contains all of the controls for the simulation.
 */
public class ControlsScene extends VBox {
	/**
	 * Constructor for the ControlsScene
	 */
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
			Main.stopSim();
		});

		getChildren().addAll(title, startSimButton, stopSimButton);
	}
}
