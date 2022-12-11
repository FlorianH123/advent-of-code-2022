package day11;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import lombok.Data;
import util.Pair;
import util.StringUtils;

@Data
public class Monkey {
	private final List<Long> itemWorryLevelList;
	private final Function<Long, Long> updateWorryLevel;
	private final Function<Long, Integer> nextMonkey;
	private Long inspectedItems = 0L;
	private final Long testDivisor;

	public Monkey(final List<Long> itemWorryLevelList, final Function<Long, Long> updateWorryLevel,
			final Function<Long, Integer> nextMonkey, final Long testDivisor) {

		this.itemWorryLevelList = new ArrayList<>(itemWorryLevelList);
		this.updateWorryLevel = updateWorryLevel;
		this.nextMonkey = nextMonkey;
		this.testDivisor = testDivisor;
	}

	public void inspectItem() {
		this.inspectedItems++;
	}

	public void testWorryLevel() {
		var itemWorryLevel = this.getFirstWorryLevel();
		itemWorryLevel = this.updateWorryLevel.apply(itemWorryLevel);
		this.setFirstWorryLeve(itemWorryLevel);
	}

	public void applyRelief(final Function<Long, Long> reliefFunction) {
		var itemWorryLevel = this.getFirstWorryLevel();
		itemWorryLevel = reliefFunction.apply(itemWorryLevel);
		this.setFirstWorryLeve(itemWorryLevel);
	}

	public Pair<Integer, Long> throwItem() {
		var itemWorryLevel = this.getFirstWorryLevel();
		final var monkeyIndex = this.nextMonkey.apply(itemWorryLevel);

		this.itemWorryLevelList.remove(0);

		return Pair.of(monkeyIndex, itemWorryLevel);
	}

	private Long getFirstWorryLevel() {
		return this.itemWorryLevelList.get(0);
	}

	private void setFirstWorryLeve(final Long worryLevel) {
		this.itemWorryLevelList.set(0, worryLevel);
	}

	public static Function<Long, Long> getUpdateWorryLevelFunction(final String operationLine) {
		final var operationTokens = operationLine.split(" ");

		if (operationTokens.length != 3) {
			throw new IllegalArgumentException("Operation line " + operationLine + " is not valid");
		}

		BiFunction<Long, Long, Long> operation = switch (operationTokens[1]) {
			case "+" -> Long::sum;
			case "-" -> (x, y) -> x - y;
			case "*" -> (x, y) -> x * y;
			case "/" -> (x, y) -> x / y;
			default -> throw new IllegalArgumentException("Operation " + operationTokens[1] + " unknown");
		};

		var lhs = operationTokens[0];
		var rhs = operationTokens[2];

		if (StringUtils.isNumber(lhs) && StringUtils.isNumber(rhs)) {
			throw new IllegalArgumentException("Both operands are numbers!");
		}

		final Function<Long, Long> updateWorryLevelFunction;

		if (StringUtils.isNumber(lhs)) {
			updateWorryLevelFunction = x -> operation.apply(Long.parseLong(lhs), x);
		} else if (StringUtils.isNumber(rhs)) {
			updateWorryLevelFunction = x -> operation.apply(x, Long.parseLong(rhs));
		} else {
			updateWorryLevelFunction = x -> operation.apply(x, x);
		}

		return updateWorryLevelFunction;
	}

	public static Function<Long, Integer> getNextMonkeyFunction(final Long test, final int monkeyIndex1,
			final int monkeyIndex2) {
		return worryLevel -> (worryLevel % (test) == 0) ? monkeyIndex1 : monkeyIndex2;
	}
}
