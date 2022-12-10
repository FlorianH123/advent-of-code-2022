package day9;

public record MoveInstruction(DirectionE direction, int steps) {
	public static MoveInstruction fromString(final String str) {
		final var stringSplit = str.split(" ");
		assert stringSplit.length == 2;

		return new MoveInstruction(DirectionE.fromString(stringSplit[0]), Integer.parseInt(stringSplit[1]));
	}
}
