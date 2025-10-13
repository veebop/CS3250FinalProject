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
	 * Horizontal location of the brush (where a new pixel will be placed)
	 */
	private double brushX;
	/**
	 * Vertical location of the brush (where a new pixel will be placed)
	 */
	private double brushY;

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

		// Create event to draw brush
		this.setOnMouseMoved(event -> {
			brushX = (int) (event.getX() / this.getWidth() * sim.getWidth()) * pixelRatio;
			brushY = (int) (event.getY() / this.getWidth() * sim.getWidth()) * pixelRatio;
		});

		// Create a new pixel on click/drag
		// TODO: Pixels should be created regularly while the mouse is held, not one
		// time when the mouse is pressed
		this.setOnMousePressed(event -> {
			addPixel();
		});
		this.setOnMouseDragged(event -> {
			double oldX = brushX;
			double oldY = brushY;
			brushX = (int) (event.getX() / this.getWidth() * sim.getWidth()) * pixelRatio;
			brushY = (int) (event.getY() / this.getWidth() * sim.getWidth()) * pixelRatio;
			if (brushX != oldX || brushY != oldY) {
				addPixel();
			}
		});

		// Hide brush if it is outside the canvas
		this.setOnMouseExited(event -> {
			brushX = Double.NaN;
		});

		// TODO: Event to resize canvas

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
	 * Adds a new pixel to the simulation
	 */
	private void addPixel() {
		int pixelX = (int) (brushX / pixelRatio);
		int pixelY = (int) (brushY / pixelRatio);
		if (brushX >= 0 && sim.getPixel(pixelX, pixelY) == null) {
			// TODO: Add different kinds of pixels based on selection
			sim.setPixel(new SandPixel(10), (int) (brushX / pixelRatio), (int) (brushY / pixelRatio));
		}
	}

	/**
	 * This function creates the animation timer to animate the simulation
	 */
	private AnimationTimer createAnimationTimer() {
		GraphicsContext gc = this.getGraphicsContext2D();
		double canvasWidth = this.getWidth();
		double canvasHeight = this.getHeight();

		gc.setStroke(Color.BLACK);

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

					// Draw the brush
					if (!Double.isNaN(brushX)) {
						gc.strokeRect(brushX, brushY, pixelRatio, pixelRatio);
					}

					if (running) {
						// Run one tick of the simulation
						sim.tick();
					}

					lastUpdate = now;
				}
			}
		};
	}
}
