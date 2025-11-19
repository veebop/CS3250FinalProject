/**
 * This represents a water pixel, a very basic liquid pixel
 */
public class OilPixel extends LiquidPixel {
	/**
	 * Constructor for the oil pixel
	 *
	 * @param temp Starting temperature of the oil pixel
	 */
	public OilPixel(double temp) {
		super(0.75, temp, 1700.0, Double.MAX_VALUE, (int) (Math.random() * 20 + 25), (int) (Math.random() * 25 + 40),
				(int) (Math.random() * 25 + 45), 255);
	}

	/**
	 * This function runs when the pixel freezes
	 */
	@Override
	public SolidPixel freeze() {
		// TODO
		return null;
	}

	/**
	 * This function runs when the pixel evaporates
	 */
	@Override
	public GasPixel vaporize() {
		// TODO
		return null;
	}
}
