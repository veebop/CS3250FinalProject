import java.util.ArrayList;
import java.util.List;

/**
 * This class represents gas pixels
 */
public abstract class GasPixel extends Pixel {
	/**
	 * The condensation point of the pixel
	 */
	private final double condPoint;
	/**
	 * The deposition point of the pixel
	 */
	private final double depPoint;

	/**
	 * The constructor for the GasPixel
	 *
	 * @param temp      The starting temperature of the pixel
	 * @param condPoint The condensation point of the pixel
	 * @param depPoint  The deposition point of the pixel
	 * @param r         The red color value of the pixel
	 * @param g         The green color value of the pixel
	 * @param b         The blue color value of the pixel
	 * @param a         The alpha value of the pixel
	 */
	public GasPixel(double density, double temp, double condPoint, double depPoint, int r, int g, int b, int a) {
		super(PixelType.GAS, density, temp, r, g, b, a);
		this.condPoint = condPoint;
		this.depPoint = depPoint;
	}

	/**
	 * Getter for the condensation point of the pixel
	 *
	 * @return The condensation point of the pixel
	 */
	public double getCondPoint() {
		return condPoint;
	}

	/**
	 * Getter for the deposition point of the pixel
	 *
	 * @return The deposition point of the pixel
	 */
	public double getDepPoint() {
		return depPoint;
	}

	/**
	 * Creates a liquid pixel, destroying itself
	 *
	 * @return The newly created liquid pixel
	 */
	public abstract LiquidPixel condensate();

	/**
	 * Creates a solid pixel, destroying itself
	 *
	 * @return THe newly created solid pixel
	 */
	public abstract SolidPixel deposit();

	@Override
	public List<Integer> move() {
		List<Integer> moveLocations = new ArrayList<>();

		// Move straight up
		moveLocations.add(0);
		moveLocations.add(-1);

		// Move up + horizontal
		moveLocations.add(Math.random() > .5 ? 1 : -1); // Randomly decide between left and right
		moveLocations.add(-1);

		// Move horizontal only
		moveLocations.add(Math.random() > .5 ? 1 : -1);
		moveLocations.add(0);

		// Stay in place
		moveLocations.add(0);
		moveLocations.add(0);

		return moveLocations;
	}
}
