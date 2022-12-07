package day1;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import util.InputFileUtils;

public class Day1 {
	public static void main(final String[] args) throws IOException {
		final List<String> lines = InputFileUtils.getLines("day1/input.txt");
		final List<List<String>> elves = InputFileUtils.partitionBy(lines, "");

		System.out.println("--- Part 1 ---");
		partOne(elves);

		System.out.println("--- Part 2 ---");
		partTwo(elves);
	}

	public static int partOne(final List<List<String>> elves) {
		final List<Integer> totalCaloriesPerElf = elves.stream()
				.map(elfCalories -> elfCalories.stream().mapToInt(Integer::parseInt).sum()).toList();
		final int maxCalories = totalCaloriesPerElf.stream().max(Integer::compare).orElse(0);
		final int elfWithMostCalories = totalCaloriesPerElf.indexOf(maxCalories) + 1;

		System.out.printf("Elf %d carries the most calories with a total of %d%n", elfWithMostCalories, maxCalories);

		return maxCalories;
	}

	public static int partTwo(final List<List<String>> elves) {
		final List<Integer> totalCaloriesPerElf = elves.stream()
				.map(elfCalories -> elfCalories.stream().mapToInt(Integer::parseInt).sum())
				.sorted(Comparator.reverseOrder()).toList();

		final int caloriesTop3 = totalCaloriesPerElf.subList(0, 3).stream().reduce(0, Integer::sum);

		System.out.printf("The top 3 elves carry a total of %d calories%n", caloriesTop3);

		return caloriesTop3;
	}
}
