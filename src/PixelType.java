public enum PixelType {
	SAND, WATER, ERASER;

	@Override
	public String toString() {
		switch (this) {
		case SAND:
			return "Sand";
		case WATER:
			return "Water";
		case ERASER:
			return "Eraser";
		default:
			return "None";
		}
	}
}
