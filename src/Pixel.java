public abstract class Pixel {

    /**
     * Holds the x (horizonal) coordinate of the pixel.
     */
    private int x;
    /**
     * Holds the y (vertical) coordinate of the pixel.
     */
    private int y;
    /**
     * Holds the horizontal velocity of the pixel.
     */
    private double vx;
    /**
     * Holds the vertical velocity of the pixel.
     */
    private double vy;
    /**
     * The temperature of the picture
     */
    private double temp;
    /**
     * The type of the pixel.
     */
    private final PixelType type;

    /**
     * Pixel class constructor, intended to only be used by children
     *
     * @param x    The starting horizontal location of the pixel
     * @param y    The starting vertical location of the pixel
     * @param temp The starting temperature of the pixel
     * @param type The PixelType of the pixel
     */
    public Pixel(int x, int y, double temp, PixelType type) {
        this.x = x;
        this.y = y;
        this.vx = 0;
        this.vy = 0;
        this.temp = temp;
        this.type = type;
    }

    /**
     * Gets the horizontal position of the pixel.
     *
     * @return The horizontal position of the pixel.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the horizontal position of the pixel.
     *
     * @param x The horizontal position to set a pixel to
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the vertical position of the pixel
     *
     * @return The vertical position of the pixel.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the vertical position of a pixel.
     *
     * @param y The vertical position to set a pixel to
     */
    public void setY(int y) {
        this.y = y;
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
     * Gets the horizontal velocity of the pixel.
     *
     * @return The horizontal velocity of the pixel.
     */
    public double getVx() {
        return vx;
    }

    /**
     * Sets the horizontal velocity of the pixel.
     *
     * @param vx The horizontal velocity to set the pixel to.
     */
    public void setVx(double vx) {
        this.vx = vx;
    }

    /**
     * Gets the vertical velocity of the pixel.
     *
     * @return The vertical velocity of the pixel.
     */
    public double getVy() {
        return vy;
    }

    /**
     * Sets the horizontal velocity of the pixel.
     *
     * @param vy The vertical velocity to set the pixel to.
     */
    public void setVy(double vy) {
        this.vy = vy;
    }

    /**
     * Moves a pixel based off of its velocity & gravity
     */
    public abstract void move();

    /**
     * Types that a pixel can be.
     */
    public enum PixelType {
        SOLID,
        LIQUID,
        GAS,
        FIRE,
    }
}
