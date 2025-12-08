/**
 * This class is a basic representation of an RGBA color.
 */
public class PixelColor {
	/**
	 * The red color value, 0-255
	 */
	private int r;
	/**
	 * The green color value, 0-255
	 */
	private int g;
	/**
	 * The blue color value, 0-255
	 */
	private int b;
	/**
	 * The alpha value, 0.0-1.0
	 */
	private double a;

	/**
	 * Constructor for the color. All values must be between 0-255, except for
	 * alpha, which is an decimal between 0-1.
	 * 
	 * @param r The red color value
	 * @param g The green color value
	 * @param b the blue color value
	 * @param a the alpha value
	 */
	PixelColor(int r, int g, int b, double a) {
		setR(r);
		setG(g);
		setB(b);
		setA(a);
	}

	/**
	 * Returns the red color value
	 * 
	 * @return The red color value from 0-255
	 */
	public int getR() {
		return r;
	}

	/**
	 * Sets the red color value
	 * 
	 * @param r The new red color value
	 */
	public void setR(int r) {
		// Keep between 0 and 255
		this.r = (int) Math.max(0, Math.min(255, r));
	}

	/**
	 * Returns the green color value
	 * 
	 * @return The green color value from 0-255
	 */
	public int getG() {
		return g;
	}

	/**
	 * Sets the green color value
	 * 
	 * @param g The new green color value
	 */
	public void setG(int g) {
		// Keep between 0 and 255
		this.g = (int) Math.max(0, Math.min(255, g));
	}

	/**
	 * Returns the blue color value
	 * 
	 * @return The blue color value from 0-255
	 */
	public int getB() {
		return b;
	}

	/**
	 * Sets the blue color value
	 * 
	 * @param b The new blue color value
	 */
	public void setB(int b) {
		// Keep between 0 and 255
		this.b = (int) Math.max(0, Math.min(255, b));
	}

	/**
	 * Returns the alpha value
	 * 
	 * @return The alpha value from 0-255
	 */
	public double getA() {
		return a;
	}

	/**
	 * Sets the alpha value
	 * 
	 * @param a The new alpha color value
	 */
	public void setA(double a) {
		// Keep between 0 and 1
		this.a = Math.max(0.0, Math.min(1.0, a));
	}

}
