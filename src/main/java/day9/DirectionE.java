package day9;

import lombok.Getter;

@Getter
public enum DirectionE {
	UP("U"), DOWN("D"), LEFT("L"), RIGHT("R");

	private final String value;

	DirectionE(final String value) {
		this.value = value;
	}

	public static DirectionE fromString(final String value) {
		return switch (value) {
			case "U" -> DirectionE.UP;
			case "D" -> DirectionE.DOWN;
			case "L" -> DirectionE.LEFT;
			case "R" -> DirectionE.RIGHT;
			default -> throw new IllegalArgumentException(String.format("Input direction %s is invalid", value));
		};
	}
}
