package day2;

public enum GameResultE {
	WIN(6), LOSE(0), DRAW(3);

	private final int value;

	GameResultE(final int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
