public class SandPixel extends DynamicPixel {
    public SandPixel(int x, int y, double temp) {
        super(x, y, temp, 1700.0, Double.MAX_VALUE);
    }

    @Override
    public LiquidPixel melt() {
        // TODO
        return null;
    }

    @Override
    public GasPixel sublimate() {
        // TODO
        return null;
    }

    @Override
    public void move() {
        // TODO
        System.out.println("Pixel is now at (" + this.getX() + ", " + this.getY() + ")");
    }
}
