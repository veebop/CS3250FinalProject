import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	/**
	 * The current simulation being shown
	 */
	private static Simulation sim;

	/**
	 * Visual representation of the simulation
	 */
	private static SimulationCanvas simCanvas;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		sim = new Simulation(50, 50);

		HBox hbox = new HBox();
		simCanvas = new SimulationCanvas(sim);
		ControlsScene controlsScene = new ControlsScene();

		hbox.getChildren().addAll(simCanvas, controlsScene);

		Scene scene = new Scene(hbox, 1000, 1000, Color.WHITESMOKE);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * This function starts the simulation that is being shown
	 */
	public static void startSim() {
		simCanvas.startSim();
	}

	/**
	 * This function stops the simulation that is being shown
	 */
	public static void stopSim() {
		simCanvas.stopSim();
	}

	/**
	 * This function sets the brush type for the current simulation
	 *
	 * @param type New brush type
	 */
	public static void setBrush(PixelType type) {
		simCanvas.setBrush(type);
	}

	/**
	 * This function shows/hides debug information on the canvas
	 *
	 * @param showDebug Whether to show or hide the debug info
	 */
	public static void setDebug(boolean showDebug) {
		simCanvas.setDebug(showDebug);
	}

	/**
	 * This function sets the speed of the simulation by setting how long the time
	 * between each tick is
	 *
	 * @param tickRate The time (in nanoseconds) between each tick
	 */
	public static void setTickRate(long tickRate) {
		simCanvas.setTickRate(tickRate);
	}
}
