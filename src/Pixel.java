import java.util.List;

/**
 * Represents a pixel, the basic object of the simulation
 */
public abstract class Pixel {

	/**
	 * The temperature of the picture
	 */
	private double temp;
	// TODO: Add color of the pixel as part of the class
	/**
	 * The type of the pixel.
	 */
	private final PixelType type;

	/**
	 * Pixel class constructor, intended to only be used by children
	 *
	 * @param temp The starting temperature of the pixel
	 * @param type The PixelType of the pixel
	 */
	public Pixel(double temp, PixelType type) {
		this.temp = temp;
		this.type = type;
	}

	/**
	 * Gets the type of the pixel.
	 *
	 * @return The PixelType of the pixel.
	 */
	public PixelType getType() {
		return type;
	}

	/**
	 * Gets the temperature of the pixel.
	 *
	 * @return The temperature of the pixel.
	 */
	public double getTemp() {
		return temp;
	}

	/**
	 * Sets the temperature of the pixel.
	 *
	 * @param temp The temperature to set the pixel to.
	 */
	public void setTemp(double temp) {
		this.temp = temp;
	}

	/**
	 * Gets potential move locations for a pixel, in order of priority
	 * 
	 * @return A list of integers, where each two represent the relative movement a
	 *         pixel could make
	 */
	public abstract List<Integer> move();

	/**
	 * Types that a pixel can be.
	 */
	public enum PixelType {
		STATIC, DYNAMIC, LIQUID, GAS, FIRE,
	}
}
