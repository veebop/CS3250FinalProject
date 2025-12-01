public enum PixelType {
	OIL, SAND, SAWDUST, WALL, WATER, ERASER;

	@Override
	public String toString() {
		switch (this) {
		case OIL:
			return "Oil";
		case SAND:
			return "Sand";
		case SAWDUST:
			return "Sawdust";
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
