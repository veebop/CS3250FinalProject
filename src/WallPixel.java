/**
 * This represents a wall pixel, basic pixel that is not affected by physics
 */
public class WallPixel extends StaticPixel {
	/**
	 * Constructor for the wall pixel
	 *
	 * @param temp Starting temperature of the wall pixel
	 */
	public WallPixel(double temp) {
		super(Integer.MAX_VALUE, temp, 1700.0, Double.MAX_VALUE, (int) (Math.random() * 15 + 155),
				(int) (Math.random() * 15 + 155), (int) (Math.random() * 15 + 155), 255);
	}

	/**
	 * This function runs when the pixel melts (impossible for this pixel)
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
