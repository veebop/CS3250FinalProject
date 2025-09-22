public abstract class StaticPixel extends SolidPixel {
    public StaticPixel(int x, int y, double temp, double meltPoint, double subPoint) {
        super(x, y, temp, meltPoint, subPoint);
    }

    /**
     * Static pixels cannot move, so this function does nothing
     */
    @Override
    public final void move() {}
}
