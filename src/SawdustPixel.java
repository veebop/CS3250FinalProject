/**
 * This represents a sawdust pixel, solid pixel that floats on water
 */
public class SawdustPixel extends DynamicPixel {
	/**
	 * Constructor for the sawdust pixel
	 *
	 * @param temp Starting temperature of the sawdust pixel
	 */
	public SawdustPixel(double temp) {
		super(0.5, temp, 1700.0, Double.MAX_VALUE, (int) (Math.random() * 15 + 230), (int) (Math.random() * 10 + 194),
				(int) (Math.random() * 15 + 126), 255);
	}

	/**
	 * This function runs when the pixel melts
	 */
	@Override
	public LiquidPixel melt() {
		// TODO
		return null;
	}

	/**
	 * This function runs when the pixel sublimates (impossible for this pixel)
	 */
	@Override
	public GasPixel sublimate() {
		// TODO
		return null;
	}
}
