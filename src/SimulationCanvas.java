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
	 * Whether the simulation should be running or not
	 */
	private boolean running;

	/**
	 * The constructor for the SimulationCanvas
	 * 
	 * @param sim The simulation this object will represent
	 */
	public SimulationCanvas(Simulation sim) {
		super(750, 750);

		this.sim = sim;

		this.running = false;

		GraphicsContext gc = this.getGraphicsContext2D();
		pixelRatio = this.getWidth() / sim.getWidth();

		// TODO: Add pixels with mouse events
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

		// Iterate over the pixels and draw them on the canvas
		for (int x = 0; x < sim.getWidth(); x++) {
			for (int y = 0; y < sim.getHeight(); y++) {
				Pixel pixel = sim.getPixel(x, y);
				if (pixel != null) {
					PixelColor color = pixel.getColor();
					gc.setFill(Color.rgb(color.getR(), color.getG(), color.getB(), color.getA()));
					gc.fillRect(x * pixelRatio, y * pixelRatio, pixelRatio, pixelRatio);
				}
			}
		}
		AnimationTimer animationTimer = createAnimationTimer();

		animationTimer.start();
	}

	/**
	 * This function starts the simulation
	 */
	public void startSim() {
		running = true;
	}

	/**
	 * This function stops the simulation
	 */
	public void stopSim() {
		running = false;
	}

	/**
	 * This function creates the animation timer to animate the simulation
	 */
	private AnimationTimer createAnimationTimer() {
		GraphicsContext gc = this.getGraphicsContext2D();
		double canvasWidth = this.getWidth();
		double canvasHeight = this.getHeight();

		return new AnimationTimer() {
			long lastUpdate = System.nanoTime();
			private final long DELAY = 30_000_000; // ~33 fps

			@Override
			public void handle(long now) {
				if (now - lastUpdate >= DELAY) {
					// Draw background
					gc.setFill(Color.LIGHTBLUE);
					gc.fillRect(0, 0, canvasWidth, canvasHeight);

					// Iterate over the pixels and draw them on the canvas
					for (int x = 0; x < sim.getWidth(); x++) {
						for (int y = 0; y < sim.getHeight(); y++) {
							Pixel pixel = sim.getPixel(x, y);
							if (pixel != null) {
								PixelColor color = pixel.getColor();
								gc.setFill(Color.rgb(color.getR(), color.getG(), color.getB(), color.getA()));
								gc.fillRect(x * pixelRatio, y * pixelRatio, pixelRatio, pixelRatio);
							}
						}
					}

					if (running) {
						// Move the pixels
						sim.movePixels();
					}

					lastUpdate = now;
				}
			}
		};
	}
}
