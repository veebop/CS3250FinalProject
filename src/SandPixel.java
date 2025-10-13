/**
 * This represents a sand pixel, a very basic solid pixel that is affected by
 * gravity
 */
public class SandPixel extends DynamicPixel {
	/**
	 * Constructor for the sand pixel
	 * 
	 * @param temp Starting temperature of the sand pixel
	 */
	public SandPixel(double temp) {
		super(temp, 1700.0, Double.MAX_VALUE);
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
