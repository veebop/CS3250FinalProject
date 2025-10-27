import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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

		// Simulation start/pause button
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

		// Brush selection
		ChoiceBox<PixelType> brushTypeChoice = new ChoiceBox<>();
		brushTypeChoice.getItems().addAll(PixelType.values());
		brushTypeChoice.setOnAction(e -> {
			Main.setBrush(brushTypeChoice.getValue());
		});
		brushTypeChoice.setValue(PixelType.SAND);
		Main.setBrush(brushTypeChoice.getValue());

		// Simulation speed slider
		Slider simSpeedSlider = new Slider(1, 120, 30);
		// TODO: Add a numerical text box for fine control
		Label simSpeedLabel = new Label((long) simSpeedSlider.getValue() + " ticks per second");
		simSpeedSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldVal, Number newVal) {
				Main.setTickRate(1_000_000_000 / newVal.longValue());
				simSpeedLabel.setText(newVal.longValue() + " ticks per second");
			}
		});

		ToggleButton showDebug = new ToggleButton("Show Debug Information");
		showDebug.setOnAction(e -> {
			Main.setDebug(showDebug.isSelected());
		});

		getChildren().addAll(title, startSimButton);
		getChildren().add(brushTypeChoice);
		getChildren().add(showDebug);
		getChildren().addAll(simSpeedSlider, simSpeedLabel);
	}
}
