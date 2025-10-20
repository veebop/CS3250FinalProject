import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
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

		ChoiceBox<PixelType> brushType = new ChoiceBox<>();
		brushType.getItems().addAll(PixelType.values());
		brushType.setOnAction(e -> {
			Main.setBrush(brushType.getValue());
		});
		brushType.setValue(PixelType.SAND);
		Main.setBrush(brushType.getValue());

		// TODO: Add a slider that changes simulation speed

		ToggleButton showDebug = new ToggleButton("Show Debug Information");
		showDebug.setOnAction(e -> {
			Main.setDebug(showDebug.isSelected());
		});

		getChildren().addAll(title, startSimButton);
		getChildren().add(brushType);
		getChildren().add(showDebug);
	}
}
