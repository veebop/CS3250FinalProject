public abstract class GasPixel extends Pixel {
    /**
     * The condensation point of the pixel
     */
    private final double condPoint;
    /**
     * The deposition point of the pixel
     */
    private final double depPoint;

    /**
     * The constructor for the GasPixel
     *
     * @param x         The starting horizontal coordinate of the pixel
     * @param y         The starting vertical coordinate of the pixel
     * @param temp      The starting temperature of the pixel
     * @param condPoint The condensation point of the pixel
     * @param depPoint  The deposition point of the pixel
     */
    public GasPixel(int x, int y, double temp, double condPoint, double depPoint) {
        super(x, y, temp, PixelType.GAS);
        this.condPoint = condPoint;
        this.depPoint = depPoint;
    }

    /**
     * Getter for the condensation point of the pixel
     *
     * @return The condensation point of the pixel
     */
    public double getCondPoint() {
        return condPoint;
    }

    /**
     * Getter for the deposition point of the pixel
     *
     * @return The deposition point of the pixel
     */
    public double getDepPoint() {
        return depPoint;
    }

    /**
     * Creates a liquid pixel, destroying itself
     *
     * @return The newly created liquid pixel
     */
    public abstract LiquidPixel condensate();

    /**
     * Creates a solid pixel, destroying itself
     *
     * @return THe newly created solid pixel
     */
    public abstract SolidPixel deposit();
}
