import java.util.ArrayList;
import java.util.List;

/**
 * This class represents liquid pixels
 */
public abstract class LiquidPixel extends Pixel {

	/**
	 * The freezing point of the pixel.
	 */
	private final double freezePoint;
	/**
	 * The vaporization point of the pixel
	 */
	private final double vapPoint;

	/**
	 * Constructor for the LiquidPixel
	 *
	 * @param temp        The starting temperature of the pixel
	 * @param freezePoint The freezing point of the pixel
	 * @param vapPoint    The vaporization point of the pixel
	 */
	LiquidPixel(double density, double temp, double freezePoint, double vapPoint, int r, int g, int b, double a) {
		super(PixelType.LIQUID, density, temp, r, g, b, a);
		this.freezePoint = freezePoint;
		this.vapPoint = vapPoint;
	}

	/**
	 * Returns the freezing point of the pixel.
	 *
	 * @return The freezing point of the pixel.
	 */
	public double getFreezePoint() {
		return freezePoint;
	}

	/**
	 * Returns the vaporization point of the pixel
	 *
	 * @return The vaporization point of the pixel
	 */
	public double getVapPoint() {
		return vapPoint;
	}

	/**
	 * Creates a solid pixel, destroying itself
	 *
	 * @return The newly created solid pixel
	 */
	public abstract SolidPixel freeze();

	/**
	 * Creates a gas pixel, destroying itself
	 *
	 * @return The newly created gas pixel
	 */
	public abstract GasPixel vaporize();

	@Override
	public List<Integer> move() {
		List<Integer> moveLocations = new ArrayList<>();

		// Move straight down
		moveLocations.add(0);
		moveLocations.add(1);

		// Move down + horizontal
		moveLocations.add(Math.random() > .5 ? 1 : -1); // Randomly decide between left and right
		moveLocations.add(1);

		// Move horizontal only
		moveLocations.add(Math.random() > .5 ? 1 : -1); // Randomly decide between left and right
		moveLocations.add(0);

		// Stay in place
		moveLocations.add(0);
		moveLocations.add(0);

		return moveLocations;
	}

}
