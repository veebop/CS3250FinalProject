import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
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

		// Debug information button
		ToggleButton showDebug = new ToggleButton("Show Debug Information");
		showDebug.setOnAction(e -> {
			Main.setDebug(showDebug.isSelected());
		});

		// Simulation speed slider
		Slider simSpeedSlider = new Slider(1, 300, 30);
		TextField simSpeedText = new TextField("30");
		Label simSpeedLabel = new Label("ticks per second\n ");

		simSpeedSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldVal, Number newVal) {
				if (oldVal != newVal) {
					Main.setTickRate(1_000_000_000 / newVal.longValue());
					simSpeedText.setText(String.valueOf(newVal.longValue()));
				}
			}
		});

		simSpeedText.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldVal, String newVal) {
				if (oldVal != newVal) {
					// Strip out anything that is not a number
					long valNum = Long.parseLong(newVal.replaceAll("\\D", ""));
					simSpeedText.setText(String.valueOf(valNum));
					// Update slider
					simSpeedSlider.setMax(Math.max(simSpeedSlider.getMax(), valNum));
					simSpeedSlider.setValue(Math.max(valNum, 1));
				}
			}
		});

		// Brush size
		Label brushSizeLabel = new Label("Brush Size");
		TextField brushSizeText = new TextField("1");
		brushSizeText.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> Observable, String oldVal, String newVal) {
				// Strip out anything that is not a number, don't allow brush size less than 1
				int valNum = Math.max(1, Integer.parseInt(newVal.replaceAll("\\D", "")));
				brushSizeText.setText(String.valueOf(valNum));
				Main.setBrushSize(valNum);
			}
		});
		Button increaseSizeBtn = new Button("Brush+");
		Button decreaseSizeBtn = new Button("Brush-");

		increaseSizeBtn.setOnMouseClicked(e -> {
			brushSizeText.setText(String.valueOf(Integer.parseInt(brushSizeText.getText()) + 1));
		});
		decreaseSizeBtn.setOnMouseClicked(e -> {
			brushSizeText.setText(String.valueOf(Integer.parseInt(brushSizeText.getText()) - 1));
		});

		// Add to scene
		getChildren().addAll(title, startSimButton);
		getChildren().add(brushTypeChoice);
		getChildren().add(showDebug);
		getChildren().addAll(simSpeedSlider, simSpeedText, simSpeedLabel);
		getChildren().addAll(brushSizeLabel, increaseSizeBtn, brushSizeText, decreaseSizeBtn);
	}
}
