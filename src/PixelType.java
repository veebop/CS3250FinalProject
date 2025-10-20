public enum PixelType {
	SAND, ERASER;

	@Override
	public String toString() {
		switch (this) {
		case SAND:
			return "Sand";
		case ERASER:
			return "Eraser";
		default:
			return "None";
		}
	}
}
