
public class SteamPixel extends GasPixel {
	/**
	 * Constructor for the steam pixel
	 *
	 * @param temp Starting temperature of the steam pixel
	 */
	public SteamPixel(double temp) {
		super(-1, temp, 100, 0, (int) (Math.random() * 5 + 220), (int) (Math.random() * 5 + 220),
				(int) (Math.random() * 15 + 230), 255);
	}

	@Override
	public LiquidPixel condensate() {
		// TODO
		return null;
	}

	@Override
	public SolidPixel deposit() {
		// TODO
		return null;
	}

}
