import java.util.Arrays;
import java.util.List;

/**
 * This class represents a solid static (not affected by physics) pixel
 */
public abstract class StaticPixel extends SolidPixel {
	/**
	 * Constructor for a static pixel
	 * 
	 * @param temp      The starting temperature of the pixel
	 * @param meltPoint The melting point of the pixel
	 * @param subPoint  The sublimation point of the pixel
	 * @param r         The red color value of the pixel
	 * @param g         The green color value of the pixel
	 * @param b         The blue color value of the pixel
	 * @param a         The alpha value of the pixel
	 */
	public StaticPixel(double density, double temp, double meltPoint, double subPoint, int r, int g, int b, int a) {
		super(Pixel.PixelType.STATIC, density, temp, meltPoint, subPoint, r, g, b, a);
	}

	/**
	 * Static pixels cannot move, so this function always returns {0, 0}
	 * 
	 * @return The location(s) the pixel can move to
	 */
	@Override
	public final List<Integer> move() {
		return Arrays.asList(0, 0);
	}
}
