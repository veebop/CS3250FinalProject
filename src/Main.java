import javafx.application.Application;
import javafx.application.Platform;
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
	 * The main stage of the application
	 */
	private static Stage mainStage;

	/**
	 * Visual representation of the simulation
	 */
	private static SimulationCanvas simCanvas;

	/**
	 * Controls for the simulation
	 */
	private static ControlsScene controlsScene;

	/**
	 * Whether or not the simulation should be running
	 */
	private static boolean simRunning = false;

	/**
	 * Time that the last tick was run
	 */
	private static long lastTick = System.nanoTime();
	/**
	 * How often a tick should run, in nanoseconds
	 */
	private static long tickDelay = 33_333_333; // 30 hz
	/**
	 * Time the the last frame was rendered
	 */
	private static long lastRender = System.nanoTime();
	/**
	 * How often a frame should render, in nanoseconds
	 */
	private static final long RENDER_DELAY = 16_666_667; // ~60 fps
	/**
	 * Time since the last pixel was placed
	 */
	private static long lastPlace = System.nanoTime();
	/**
	 * How often a pixel should be placed while the mouse is held
	 */
	private static final long PLACE_DELAY = 100_000_000; // 10hz

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// Create a default sim and display it
		mainStage = stage;
		createNewSim(50, 50);
		mainStage.show();

		new Thread(() -> {
			long tickTime = Long.MAX_VALUE;

			// Main loop
			while (mainStage.isShowing()) {
				long now = System.nanoTime();

				// Run the simulation one tick
				if (now - lastTick >= tickDelay && simRunning) {
					sim.tick();
					tickTime = now - lastTick;
					lastTick = System.nanoTime();
				}

				// Draw the frame to the screen
				if (now - lastRender >= RENDER_DELAY) {
					final long tt = tickTime;
					final long lr = now - lastRender;
					Platform.runLater(() -> {
						simCanvas.draw(tt, lr);
					});
					lastRender = System.nanoTime();
				}

				// Try to place a new pixel
				if (now - lastPlace >= PLACE_DELAY) {
					if (simCanvas.addPixel()) {
						lastPlace = System.nanoTime();
					}
				}

				// Sleep so we don't completely pin the CPU, at the cost of somewhat less
				// accurate ticks/frames per second.
				long sleepTime = Math.max((Math.min(tickDelay, RENDER_DELAY) - (System.nanoTime() - now)) / 50, 0);
				try {
					Thread.sleep(sleepTime / 1_000_000, (int) (sleepTime % 1_000_000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * This function starts the simulation that is being shown
	 */
	public static void resumeSim() {
		simRunning = true;
	}

	/**
	 * This function stops the simulation that is being shown
	 */
	public static void stopSim() {
		simRunning = false;
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
	 * This function sets the brush size for the current simulation
	 *
	 * @param size New brush size
	 */
	public static void setBrushSize(int size) {
		simCanvas.setBrushSize(size);
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
		tickDelay = tickRate;
	}

	/**
	 * Creates and displays a new simulation
	 *
	 * @param width  Width of the new simulation
	 * @param height Height of the new simulation
	 */
	public static void createNewSim(int width, int height) {
		sim = new Simulation(width, height);

		// Create a new, fresh scene
		HBox hbox = new HBox();
		simCanvas = new SimulationCanvas(sim);
		controlsScene = new ControlsScene();

		hbox.getChildren().addAll(simCanvas, controlsScene);

		Scene scene = new Scene(hbox, 1000, 1000, Color.WHITESMOKE);
		mainStage.setScene(scene);

		// Set default values
		lastTick = System.nanoTime();
		tickDelay = 33_333_333; // 30 hz
		simRunning = false;

	}
}
