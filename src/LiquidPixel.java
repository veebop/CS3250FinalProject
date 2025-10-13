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
	LiquidPixel(double temp, double freezePoint, double vapPoint) {
		super(temp, PixelType.LIQUID);
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

}
