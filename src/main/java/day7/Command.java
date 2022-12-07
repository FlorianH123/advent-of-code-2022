package day7;

import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Command {
	private CommandTypeE type;
	private Optional<String> arguments = Optional.empty();
	private List<String> output = new ArrayList<>();

	public Command(final CommandTypeE type) {
		this.type = type;
	}

	public Command(final CommandTypeE type, final String arguments) {
		this.type = type;
		this.arguments = Optional.of(arguments);
	}

	public static boolean isCommand(final String line) {
		return line.startsWith("$");
	}

	public static Command fromString(final String line) {
		if (!isCommand(line)) {
			throw new IllegalArgumentException(String.format("Input %s is not a command", line));
		}

		final var commandAndArgs = line.split(" ");
		final var commandType = CommandTypeE.fromString(commandAndArgs[1]);

		if (commandAndArgs.length == 3) {
			final var args = commandAndArgs[2];
			return new Command(commandType, args);
		} else {
			return new Command(commandType);
		}
	}
}
