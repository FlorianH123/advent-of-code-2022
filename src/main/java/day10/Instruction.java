package day10;

public record Instruction(MnemonicE mnemonicE, Integer param) {

	public static Instruction fromString(final String str) {
		if (str.isBlank()) {
			throw new IllegalArgumentException("Line is empty");
		}

		final var strSplit = str.split(" ");
		final var mnemonic = MnemonicE.fromString(strSplit[0]);

		Integer param = null;

		if (strSplit.length == 2) {
			param = Integer.parseInt(strSplit[1]);
		}

		return new Instruction(mnemonic, param);

	}
}
