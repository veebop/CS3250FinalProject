import java.util.HashMap;
import java.util.List;

/**
 * Class representing a simulation
 */
public class Simulation {
	/**
	 * Width of the simulation
	 */
	private final int width;
	/**
	 * Height of the simulation
	 */
	private final int height;
	/**
	 * A hashmap representing the grid of pixels. We use a one dimensional hashmap
	 * for performance (and simplicity). The key for each pixel is determined with
	 * the formula (x + y * width).
	 */
	private HashMap<Integer, Pixel> pixels;

	/**
	 * Constructor for Simulation
	 * 
	 * @param width  The horizontal width of the simulation
	 * @param height The vertical height of the simulation
	 */
	public Simulation(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new HashMap<>();
	}

	/*
	 * Getter for the simulation width
	 * 
	 * @return Simulation width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Getter for the simulation height
	 * 
	 * @return Simulation height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns an HashMap of all pixels in the simulation
	 * 
	 * @return List of all pixels in the simulation
	 */
	public HashMap<Integer, Pixel> getPixels() {
		return pixels;
	}

	/**
	 * Getter for a pixel at specified coordinates
	 * 
	 * @param x Horizontal coordinate of the pixel to get
	 * @param y Vertical coordinate of the pixel to get
	 * @return The pixel at the coordinates
	 */
	public Pixel getPixel(int x, int y) {
		return pixels.get(x + y * width);
	}

	/**
	 * Puts a pixel at the specified coordinates
	 * 
	 * @param pixel The pixel to place
	 * @param x     Horizontal coordinate
	 * @param y     Vertical coordinate
	 */
	public void setPixel(Pixel pixel, int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			pixels.put(x + y * width, pixel);
		}
	}

	/**
	 * Runs one tick of the simulation.
	 */
	public void tick() {
		HashMap<Integer, Pixel> newPixels = new HashMap<>();

		// Because all static pixels cannot move and other pixels cannot overwrite
		// them, we place them in the new hashmap first
		for (HashMap.Entry<Integer, Pixel> entry : this.pixels.entrySet()) {
			if (entry.getValue().getType() == Pixel.PixelType.STATIC) {
				newPixels.put(entry.getKey(), entry.getValue());
			}
		}

		// Now we handle all the other pixels
		// We want to loop bottom to top, since most pixels fall down
		for (int i = this.height * this.width - 1; i >= 0; i--) {
			if (this.pixels.containsKey(i) && this.pixels.get(i).getType() != Pixel.PixelType.STATIC) {
				Pixel p = this.pixels.get(i);
				List<Integer> moveLocations = p.move();

				// Loop through the new possible locations
				for (int j = 0; j < moveLocations.size(); j += 2) {
					int newLocation = i + moveLocations.get(j) + moveLocations.get(j + 1) * this.width;
					// If the spot isn't taken and is in bounds, move the pixel there
					if (!newPixels.containsKey(newLocation) && newLocation >= 0
							&& newLocation < this.width * this.height
							// Stop pixels from wrapping around the edges
							&& (i % width) + moveLocations.get(j) >= 0 && (i % width) + moveLocations.get(j) < width) {
						newPixels.put(newLocation, p);
						// We moved this pixel, move to the next one
						break;
					}
				}
			}
		}

		// Update the list of pixels
		this.pixels = newPixels;
	}
}
