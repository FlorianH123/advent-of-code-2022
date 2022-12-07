package day7;

public enum CommandTypeE {
	LS("ls"), CD("cd");

	private final String value;

	CommandTypeE(final String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static CommandTypeE fromString(final String value) {
		return CommandTypeE.valueOf(value.toUpperCase());
	}
}
