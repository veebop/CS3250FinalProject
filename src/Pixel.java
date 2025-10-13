import java.util.List;

/**
 * Represents a pixel, the basic object of the simulation
 */
public abstract class Pixel {

	/**
	 * The temperature of the picture
	 */
	private double temp;

	/**
	 * The color of the pixel
	 */
	private PixelColor color;

	/**
	 * The type of the pixel.
	 */
	private final PixelType type;

	/**
	 * Pixel class constructor
	 *
	 * @param type The PixelType of the pixel
	 * @param temp The starting temperature of the pixel
	 * @param r    The red color value of the pixel
	 * @param g    The green color value of the pixel
	 * @param b    The blue color value of the pixel
	 * @param a    The alpha value of the pixel
	 */
	public Pixel(PixelType type, double temp, int r, int g, int b, int a) {
		this.color = new PixelColor(r, g, b, a);
		this.temp = temp;
		this.type = type;
	}

	/**
	 * Returns the color of the pixel
	 * 
	 * @return Color of the pixel
	 */
	public PixelColor getColor() {
		return this.color;
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
