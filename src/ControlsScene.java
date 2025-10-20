import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * This class contains all of the controls for the simulation.
 */
public class ControlsScene extends VBox {

	private boolean simRunning = false;

	/**
	 * Constructor for the ControlsScene
	 */
	public ControlsScene() {

		Label title = new Label("Controls:");

		Button startSimButton = new Button("Start simulation");

		startSimButton.setOnAction(e -> {
			if (simRunning) {
				startSimButton.setText("Start Simulation");
				Main.stopSim();
				simRunning = false;
			} else {
				startSimButton.setText("Pause Simulation");
				Main.startSim();
				simRunning = true;
			}
		});

		// TODO: Add a slider that changes simulation speed

		// TODO: Add a dropdown/radials to select pixel type

		// TODO: Add toggle for some debug info

		getChildren().addAll(title, startSimButton);
	}
}
