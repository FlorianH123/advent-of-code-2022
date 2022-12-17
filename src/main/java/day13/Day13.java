package day13;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import util.InputFileUtils;
import util.Pair;

public class Day13 {

	private static final Gson GSON = new Gson();

	public static void main(String[] args) throws IOException {
		final var lines = InputFileUtils.getLines("day13/input.txt");

		System.out.println("--- part1 ---");
		final var result1 = part1(lines);
		System.out.println("The sum of indices which are already in the right order is " + result1);

		System.out.println("--- part2 ---");
		final var result2 = part2(lines);
		System.out.println("The product of both divider package indices is " + result2);
	}

	public static int part1(final List<String> lines) {

		final var result = parseInput(lines);
		var orderedIndices = 0;

		for (int i = 0; i < result.size(); i++) {
			final Pair<List<Object>, List<Object>> pair = result.get(i);
			final var compareValue = compareValues(pair.left(), pair.right());

			if (compareValue == -1) {
				orderedIndices += i + 1;
			}
		}

		return orderedIndices;
	}

	public static int part2(final List<String> lines) {
		final var result = parseInput(lines);

		// divider packet
		result.add(Pair.of(List.of(List.of(2.0)), List.of(List.of(6.0))));

		var orderedIndices = 1;

		final var sortedList = result.stream().map(a -> List.of(a.left(), a.right())).flatMap(Collection::stream)
				.sorted(Day13::compareValues).toList();

		for (int i = 0; i < sortedList.size(); i++) {
			List<Object> listItem = sortedList.get(i);

			if (listItem.equals(List.of(List.of(2.0))) || listItem.equals(List.of(List.of(6.0)))) {
				orderedIndices *= i + 1;
			}
		}

		return orderedIndices;
	}

	@SuppressWarnings("unchecked")
	private static List<Pair<List<Object>, List<Object>>> parseInput(final List<String> lines) {
		final var list = new ArrayList<Pair<List<Object>, List<Object>>>();
		final var splitList = InputFileUtils.partitionBy(lines, "");

		for (final List<String> listPairs : splitList) {
			assert listPairs.size() == 2;
			final List<Object> firstList = GSON.fromJson(listPairs.get(0), List.class);
			final List<Object> secondList = GSON.fromJson(listPairs.get(1), List.class);

			list.add(Pair.of(firstList, secondList));
		}

		return list;
	}

	public static int compareValues(final Object left, final Object right) {
		if (left instanceof Double leftNumber && right instanceof Double rightNumber) {
			return leftNumber.compareTo(rightNumber);
		} else if (left instanceof List<?> leftList && right instanceof List<?> rightList) {
			int compareResult = 0;
			int i = 0;
			while (compareResult == 0) {
				if (i == leftList.size() && i < rightList.size()) {
					return -1;
				}

				if (i < leftList.size() && i == rightList.size()) {
					return 1;
				}

				if (i == leftList.size() && i == rightList.size()) {
					return 0;
				}

				compareResult = compareValues(leftList.get(i), rightList.get(i));
				i++;
			}

			return compareResult;
		} else if (left instanceof Double leftNumber) {
			return compareValues(List.of(leftNumber), right);
		} else if (right instanceof Double rightNumber) {
			return compareValues(left, List.of(rightNumber));
		}

		throw new IllegalArgumentException("unreachable");
	}
}
