import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class creates a graphical representation of the simulation
 */
public class SimulationCanvas extends Canvas {
	/**
	 * The simulation that the canvas is showing
	 */
	private Simulation sim;
	/**
	 * The ratio of real screen pixels to to a simulation pixel, so that the
	 * simulation graphics can be scaled up
	 */
	private double pixelRatio;

	/**
	 * The constructor for the SimulationCanvas
	 * 
	 * @param sim The simulation this object will represent
	 */
	public SimulationCanvas(Simulation sim) {
		super(750, 750);

		this.sim = sim;

		GraphicsContext gc = this.getGraphicsContext2D();
		pixelRatio = this.getWidth() / sim.getWidth();

		// Add test pixels to simulation
		for (int i = 0; i < 3; i++) {
			sim.setPixel(new SandPixel(10), i * 10, i * 10);
		}
		// And some random pixels
		for (int i = 0; i < 25; i++) {
			sim.setPixel(new SandPixel(10), (int) (Math.random() * sim.getWidth()),
					(int) (Math.random() * sim.getHeight()));
		}

		// Draw background
		gc.setFill(Color.LIGHTBLUE);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());

		// Test drawing to the canvas
		gc.setFill(Color.SANDYBROWN);

		// Iterate over the pixels and draw them on the canvas
		for (int x = 0; x < sim.getWidth(); x++) {
			for (int y = 0; y < sim.getHeight(); y++) {
				if (sim.getPixel(x, y) != null) {
					gc.fillRect(x * pixelRatio, y * pixelRatio, pixelRatio, pixelRatio);
				}
			}
		}
	}

	/**
	 * This function animates the simulation
	 */
	public void startSim() {
		GraphicsContext gc = this.getGraphicsContext2D();
		double width = this.getWidth();
		double height = this.getHeight();

		new AnimationTimer() {
			long lastUpdate = System.nanoTime();
			private final long DELAY = 30_000_000; // ~33 fps

			@Override
			public void handle(long now) {
				if (now - lastUpdate >= DELAY) {
					// Draw background
					gc.setFill(Color.LIGHTBLUE);
					gc.fillRect(0, 0, width, height);

					// Iterate over the pixels and draw them on the canvas
					gc.setFill(Color.SANDYBROWN);
					for (int x = 0; x < sim.getWidth(); x++) {
						for (int y = 0; y < sim.getHeight(); y++) {
							Pixel pixel = sim.getPixel(x, y);
							if (pixel != null) {
								gc.fillRect(x * pixelRatio, y * pixelRatio, pixelRatio, pixelRatio);
							}
						}
					}
					// Move the pixels
					sim.movePixels();

					lastUpdate = now;
				}
			}
		}.start();
	}
}
