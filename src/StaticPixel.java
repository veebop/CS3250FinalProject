public abstract class StaticPixel extends SolidPixel {
    public StaticPixel(double temp, double meltPoint, double subPoint) {
        super(temp, meltPoint, subPoint);
    }

    /**
     * Static pixels cannot move, so this function does nothing
     */
    @Override
    public final void move() {
    }
}
