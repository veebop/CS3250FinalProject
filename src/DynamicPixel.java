import java.util.ArrayList;
import java.util.List;

/**
 * This class represents solid dynamic (affected by physics) pixels
 */
public abstract class DynamicPixel extends SolidPixel {
	/**
	 * Constructor for the DynamicPixel class
	 * 
	 * @param temp      Temperature that the pixel starts at
	 * @param meltPoint Melting point of the pixel
	 * @param subPoint  Sublimation point of the pixel
	 * @param r         The red color value of the pixel
	 * @param g         The green color value of the pixel
	 * @param b         The blue color value of the pixel
	 * @param a         The alpha value of the pixel
	 */
	public DynamicPixel(double density, double temp, double meltPoint, double subPoint, int r, int g, int b, double a) {
		super(Pixel.PixelType.DYNAMIC, density, temp, meltPoint, subPoint, r, g, b, a);
	}

	/**
	 * Dynamic pixels can move down one, down one and horizontal one, or stay still
	 * in that priority.
	 */
	@Override
	public List<Integer> move() {
		List<Integer> moveLocations = new ArrayList<>();

		// Move straight down
		moveLocations.add(0);
		moveLocations.add(1);

		// Move straight down + horizontal
		moveLocations.add(Math.random() > .5 ? 1 : -1); // Randomly decide between left and right
		moveLocations.add(1);

		// Stay in place
		moveLocations.add(0);
		moveLocations.add(0);

		return moveLocations;
	}
}
