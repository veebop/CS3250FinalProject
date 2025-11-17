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
		// We use rand here to add some variation to the color
		super(2, temp, 1700.0, Double.MAX_VALUE, (int) (Math.random() * 15 + 230), (int) (Math.random() * 10 + 164),
				(int) (Math.random() * 15 + 96), 255);
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
