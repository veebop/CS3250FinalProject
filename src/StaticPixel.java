import java.util.Arrays;
import java.util.List;

/**
 * This class represents a solid static (not affected by physics) pixel
 */
public abstract class StaticPixel extends SolidPixel {
	public StaticPixel(double temp, double meltPoint, double subPoint) {
		super(Pixel.PixelType.STATIC, temp, meltPoint, subPoint);
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
