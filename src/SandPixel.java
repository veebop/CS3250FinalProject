public class SandPixel extends DynamicPixel {
    public SandPixel(double temp) {
        super(temp, 1700.0, Double.MAX_VALUE);
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
}
