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
	 * Horizontal location of the brush (where a new pixel will be placed)
	 */
	private double brushX;
	/**
	 * Vertical location of the brush (where a new pixel will be placed)
	 */
	private double brushY;
	/**
	 * This boolean should be set to true to show some debug information
	 */
	private boolean debug = false;
	/**
	 * The type of pixel to be placed or eraser
	 */
	private PixelType brushType;
	/**
	 * Whether the mouse is being pressed or not
	 */
	private boolean mouseDown;

	/**
	 * The constructor for the SimulationCanvas
	 *
	 * @param sim The simulation this object will represent
	 */
	public SimulationCanvas(Simulation sim) {
		super(750, 750);

		this.sim = sim;

		// Create event to draw brush
		this.setOnMouseMoved(e -> {
			brushX = (int) (e.getX() / this.getWidth() * sim.getWidth()) * pixelRatio;
			brushY = (int) (e.getY() / this.getWidth() * sim.getWidth()) * pixelRatio;
		});

		// Create a new pixel on click/drag
		this.setOnMousePressed(e -> {
			addPixel();
			this.mouseDown = true;
		});
		this.setOnMouseReleased(e -> {
			this.mouseDown = false;
		});
		this.setOnMouseDragged(e -> {
			double oldX = brushX;
			double oldY = brushY;
			brushX = (int) (e.getX() / this.getWidth() * sim.getWidth()) * pixelRatio;
			brushY = (int) (e.getY() / this.getWidth() * sim.getWidth()) * pixelRatio;
			if (brushX != oldX || brushY != oldY) {
				addPixel();
			}
		});

		// Hide brush if it is outside the canvas
		this.setOnMouseExited(e -> {
			brushX = Double.NaN;
		});

		this.draw(0, 0);

		// TODO: Event to resize canvas
	}

	/**
	 * This function draws a new frame to the canvas
	 *
	 * @param tickTime   Time (in ns) since the last tick
	 * @param renderTime Time (in ns) since the last drawn frame
	 */
	public void draw(long tickTime, long renderTime) {
		GraphicsContext gc = this.getGraphicsContext2D();
		double canvasWidth = this.getWidth();
		double canvasHeight = this.getHeight();

		pixelRatio = this.getWidth() / sim.getWidth();

		gc.setStroke(Color.BLACK);

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

		// Draw the debug information
		if (debug) {
			gc.setFill(Color.BLACK);
			gc.fillText(1_000_000_000 / tickTime + " ticks/sec", 0, gc.getFont().getSize());
			gc.fillText(1_000_000_000 / renderTime + " fps", 0, gc.getFont().getSize() * 2);
		}
	}

	/**
	 * This function sets the brush type
	 *
	 * @param type The new type for the brush
	 */
	public void setBrush(PixelType type) {
		this.brushType = type;
	}

	/**
	 * This function sets whether debug information should be shown
	 *
	 * @param showDebug Whether debug information should be shown
	 */
	public void setDebug(boolean showDebug) {
		debug = showDebug;
	}

	/**
	 * Adds a new pixel to the simulation
	 */
	public boolean addPixel() {
		if (this.mouseDown) {
			int pixelX = (int) (brushX / pixelRatio);
			int pixelY = (int) (brushY / pixelRatio);
			if (brushX >= 0) {
				if (sim.getPixel(pixelX, pixelY) == null) {
					switch (brushType) {
					case OIL:
						sim.setPixel(new OilPixel(10), (int) (brushX / pixelRatio), (int) (brushY / pixelRatio));
						break;
					case SAND:
						sim.setPixel(new SandPixel(10), (int) (brushX / pixelRatio), (int) (brushY / pixelRatio));
						break;
					case WALL:
						sim.setPixel(new WallPixel(10), (int) (brushX / pixelRatio), (int) (brushY / pixelRatio));
						break;
					case WATER:
						sim.setPixel(new WaterPixel(10), (int) (brushX / pixelRatio), (int) (brushY / pixelRatio));
						break;
					default:
						break;
					}
				} else if (brushType == PixelType.ERASER) {
					sim.deletePixel((int) (brushX / pixelRatio), (int) (brushY / pixelRatio));
				}
			}
			return true;
		} else {
			return false;
		}
	}
}
