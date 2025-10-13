// This class represents a solid pixel
public abstract class SolidPixel extends Pixel {

	/**
	 * The melting point of the pixel
	 */
	private final double meltPoint;
	/**
	 * The sublimation point of the pixel
	 */
	private final double subPoint;

	/**
	 * Constructor for solid pixels
	 *
	 * @param type      The type of the pixel
	 * @param temp      The starting temperature of the pixel
	 * @param meltPoint The melting point of the pixel
	 * @param subPoint  The sublimation point of the pixel
	 */
	SolidPixel(Pixel.PixelType type, double temp, double meltPoint, double subPoint) {
		super(temp, type);
		this.meltPoint = meltPoint;
		this.subPoint = subPoint;
	}

	/**
	 * Getter for the melting point of the pixel
	 *
	 * @return The melting point of the pixel
	 */
	public double getMeltPoint() {
		return meltPoint;
	}

	/**
	 * Getter for the sublimation point of the pixel
	 *
	 * @return The sublimation point of the pixel
	 */
	public double getSubPoint() {
		return subPoint;
	}

	/**
	 * Converts the current pixel into a liquid, destroying itself
	 *
	 * @return The newly created LiquidPixel
	 */
	public abstract LiquidPixel melt();

	/**
	 * Converts the current pixel into a gas, destroying itself
	 *
	 * @return the newly created GasPixel
	 */
	public abstract GasPixel sublimate();
}
