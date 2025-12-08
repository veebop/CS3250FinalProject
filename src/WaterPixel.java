/**
 * This represents a water pixel, a very basic liquid pixel
 */
public class WaterPixel extends LiquidPixel {
	/**
	 * Constructor for the water pixel
	 *
	 * @param temp Starting temperature of the water pixel
	 */
	public WaterPixel(double temp) {
		super(1, temp, 0, 100, (int) (Math.random() * 10 + 0), (int) (Math.random() * 15 + 195),
				(int) (Math.random() * 15 + 240), 255);
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
