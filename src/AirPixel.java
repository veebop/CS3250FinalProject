import java.util.ArrayList;
import java.util.List;

public class AirPixel extends GasPixel {
	/**
	 * Constructor for the air pixel
	 *
	 * @param temp Starting temperature of the air pixel
	 */
	public AirPixel(double temp) {
		super(0, temp, Double.MIN_VALUE, Double.MIN_VALUE, 255, 255, 255, 0.5);
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

	@Override
	public List<Integer> move() {
		List<Integer> moveLocations = new ArrayList<>();

		// Try to move in a random direction
		moveLocations.add(Math.random() > .5 ? 1 : -1);
		moveLocations.add(Math.random() > .5 ? 1 : -1);

		// Stay in place
		moveLocations.add(0);
		moveLocations.add(0);

		return moveLocations;
	}

}
