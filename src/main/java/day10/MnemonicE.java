package day10;

import lombok.Getter;

@Getter
public enum MnemonicE {
	NOOP(1), ADDX(2);

	private final int cycles;

	MnemonicE(final int cycles) {
		this.cycles = cycles;
	}

	public static MnemonicE fromString(final String value) {
		return switch (value) {
			case "noop" -> MnemonicE.NOOP;
			case "addx" -> MnemonicE.ADDX;
			default -> throw new IllegalArgumentException(String.format("Input direction %s is invalid", value));
		};
	}
}
