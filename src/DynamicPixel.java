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
	 */
	public DynamicPixel(double temp, double meltPoint, double subPoint) {
		super(temp, meltPoint, subPoint);
	}
}
