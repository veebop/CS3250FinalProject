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
		for (Pixel pixel : sim.getPixels().values()) {
			System.out.println(pixel);
		}
		simCanvas.startSim();
	}

	public static void stopSim() {
		System.out.println("Main stop");
		simCanvas.stopSim();
	}
}
