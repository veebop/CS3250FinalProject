import java.util.HashMap;

public class Simulation {
    private final int width;
    private final int height;
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
     * Returns an ArrayList of all pixels in the simulation
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
     * Moves the pixels down one
     * TODO: Actually do physics things here, collision, etc.
     */
    public void movePixels() {
        HashMap<Integer, Pixel> newPixels = new HashMap<>();

        for (HashMap.Entry<Integer, Pixel> entry : this.pixels.entrySet()) {
            int i = entry.getKey();
            Pixel p = entry.getValue();
            
            // If there is room below the pixel
            if (i + this.getWidth() < (this.width * this.height)) {
                // Add the pixel to the new HashMap lower
                newPixels.put(i + this.getWidth(), p);
            } else {
                // Keep the pixel where it is
                newPixels.put(i, p);
            }
            // Set pixels to the new HashMap
            this.pixels = newPixels;
        }
    }
}
