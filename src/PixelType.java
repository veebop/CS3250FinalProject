public enum PixelType {
	AIR, OIL, SAND, SAWDUST, STEAM, WALL, WATER, ERASER;

	@Override
	public String toString() {
		switch (this) {
		case AIR:
			return "Air";
		case OIL:
			return "Oil";
		case SAND:
			return "Sand";
		case SAWDUST:
			return "Sawdust";
		case STEAM:
			return "Steam";
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
