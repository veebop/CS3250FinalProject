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
	 * Size of the brush
	 */
	private int brushSize = 1;
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
		super(Math.min(750, (int) (750 * ((double) sim.getWidth() / (double) sim.getHeight()))),
				Math.min(750, (int) (750 * ((double) sim.getHeight() / (double) sim.getWidth()))));

		this.sim = sim;

		// Create event to draw brush
		this.setOnMouseMoved(e -> {
			brushX = snapX(e.getX());
			brushY = snapY(e.getY());
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
			brushX = snapX(e.getX());
			brushY = snapY(e.getY());
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
			gc.strokeRect(brushX, brushY, pixelRatio * brushSize, pixelRatio * brushSize);
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
	 * This function sets the brush size
	 *
	 * @param size The new size for the brush (in pixels)
	 */
	public void setBrushSize(int size) {
		this.brushSize = size;
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
			for (int x = 0; x < brushSize; x++) {
				for (int y = 0; y < brushSize; y++) {
					if (sim.getPixel(pixelX() + x, pixelY() + y) == null) {
						switch (brushType) {
						case AIR:
							sim.setPixel(new AirPixel(10), pixelX() + x, pixelY() + y);
							break;
						case OIL:
							sim.setPixel(new OilPixel(10), pixelX() + x, pixelY() + y);
							break;
						case SAND:
							sim.setPixel(new SandPixel(10), pixelX() + x, pixelY() + y);
							break;
						case SAWDUST:
							sim.setPixel(new SawdustPixel(10), pixelX() + x, pixelY() + y);
							break;
						case STEAM:
							sim.setPixel(new SteamPixel(110), pixelX() + x, pixelY() + y);
							break;
						case WALL:
							sim.setPixel(new WallPixel(10), pixelX() + x, pixelY() + y);
							break;
						case WATER:
							sim.setPixel(new WaterPixel(10), pixelX() + x, pixelY() + y);
							break;
						default:
							break;
						}
					} else if (brushType == PixelType.ERASER) {
						sim.deletePixel(pixelX() + x, pixelY() + y);
					}
				}
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This function returns the X coordinate of the pixel under the brush
	 *
	 * @return X coordinate of pixel under brush
	 */
	private int pixelX() {
		return (int) (brushX / pixelRatio);
	}

	/**
	 * This function returns the Y coordinate of the pixel under the brush
	 *
	 * @return Y coordinate of pixel under brush
	 */
	private int pixelY() {
		return (int) (brushY / pixelRatio);
	}

	/**
	 * This function returns an X canvas coordinate that is the edge of a pixel
	 *
	 * @param canvasX Current canvas coordinate
	 * @return X canvas coordinate
	 */
	private double snapX(double canvasX) {
		return Math.min(Math.max(0, (int) (canvasX / this.getWidth() * sim.getWidth()) * pixelRatio),
				this.getWidth() - brushSize * pixelRatio);
	}

	/**
	 * This function returns an Y canvas coordinate that is the edge of a pixel
	 *
	 * @param canvasY Current canvas coordinate
	 * @return Y canvas coordinate
	 */
	private double snapY(double canvasY) {
		return Math.min(Math.max(0, (int) (canvasY / this.getHeight() * sim.getHeight()) * pixelRatio),
				this.getHeight() - brushSize * pixelRatio);

	}
}
