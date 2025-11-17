public enum PixelType {
	SAND, WALL, WATER, ERASER;

	@Override
	public String toString() {
		switch (this) {
		case SAND:
			return "Sand";
		case WALL:
			return "Wall";
		case WATER:
			return "Water";
		case ERASER:
			return "Eraser";
		default:
			return "None";
		}
	}
}
