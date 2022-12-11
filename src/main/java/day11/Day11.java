package day11;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import util.InputFileUtils;
import util.StringUtils;

public class Day11 {
	public static void main(final String[] args) throws IOException {
		final var lines = InputFileUtils.getLines("day11/input.txt");

		System.out.println("--- part1 ---");
		final var result1 = part1(lines);
		System.out.printf("The two most active monkeys have a monkey business level of %d after 20 rounds%n", result1);

		System.out.println("--- part2 ---");
		final var result2 = part2(lines);
		System.out.printf("The two most active monkeys have a monkey business level of %d after 10000 rounds%n",
				result2);
	}

	public static Long part1(final List<String> lines) {
		var monkeys = parseMonkeys(lines);
		return playRounds(monkeys, 20, worryLevel -> Math.floorDiv(worryLevel, 3));
	}

	public static Long part2(final List<String> lines) {
		var monkeys = parseMonkeys(lines);

		// Didn't understand how to come up with this solution. Solution copied from reddit :(
		var modulus = monkeys.stream().map(Monkey::getTestDivisor).reduce(1L, (x, y) -> x * y);

		return playRounds(monkeys, 10_000, worryLevel -> worryLevel % modulus);
	}

	private static Long playRounds(final List<Monkey> monkeys, final int roundsToPlay,
			Function<Long, Long> reliefFunction) {

		for (int i = 0; i < roundsToPlay; i++) {
			playRound(monkeys, reliefFunction);
		}

		return monkeys.stream().map(Monkey::getInspectedItems).sorted(Comparator.reverseOrder()).limit(2).reduce(1L,
				(x, y) -> x * y);
	}

	private static void playRound(final List<Monkey> monkeys, Function<Long, Long> reliefFunction) {
		for (final var monkey : monkeys) {
			while (monkey.getItemWorryLevelList().size() > 0) {
				monkey.inspectItem();
				monkey.testWorryLevel();
				final var monkeyIndexAndItem = monkey.throwItem(reliefFunction);

				monkeys.get(monkeyIndexAndItem.left()).getItemWorryLevelList().add(monkeyIndexAndItem.right());
			}
		}
	}

	private static List<Monkey> parseMonkeys(final List<String> lines) {
		final var monkeys = new ArrayList<Monkey>();
		int i = 0;

		while (i < lines.size()) {
			final var startingItemsLine = StringUtils.substringAfter(lines.get(++i), "Starting items: ");

			if (startingItemsLine == null) {
				throw new IllegalArgumentException("Monkey has no starting items!");
			}

			final var itemWorryLevelList = Arrays.stream(startingItemsLine.split(", ")).map(Long::parseLong).toList();

			final var operationLine = StringUtils.substringAfter(lines.get(++i), "Operation: new = ");

			if (operationLine == null) {
				throw new IllegalArgumentException("Monkey has no operation!");
			}

			final var testInputLine = StringUtils.substringAfter(lines.get(++i), "Test: divisible by ");

			if (testInputLine == null) {
				throw new IllegalArgumentException("Monkey has no test input!");
			}

			final var testInput = Long.parseLong(testInputLine);

			final var testTrueLine = StringUtils.substringAfter(lines.get(++i), "If true: throw to monkey ");

			if (testTrueLine == null) {
				throw new IllegalArgumentException("Monkey has no test true input!");
			}

			final var testTrueMonkeyIndex = Integer.parseInt(testTrueLine);

			final var testFalseLine = StringUtils.substringAfter(lines.get(++i), "If false: throw to monkey ");

			if (testFalseLine == null) {
				throw new IllegalArgumentException("Monkey has no test ^false input!");
			}

			final var testFalseMonkeyIndex = Integer.parseInt(testFalseLine);

			final var monkey = new Monkey(itemWorryLevelList, Monkey.getUpdateWorryLevelFunction(operationLine),
					Monkey.getNextMonkeyFunction(testInput, testTrueMonkeyIndex, testFalseMonkeyIndex), testInput);
			monkeys.add(monkey);

			// Skip empty row
			i += 2;
		}

		return monkeys;
	}
}
