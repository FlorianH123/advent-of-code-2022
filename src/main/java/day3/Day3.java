package day3;

import java.io.IOException;
import java.util.List;
import util.InputFileUtils;

public class Day3 {
	public static void main(final String[] args) throws IOException {
		var lines = InputFileUtils.getLines("day3/input.txt");

		System.out.println("--- part1 ---");
		var priorities1 = part1(lines);
		System.out.println("The priority of duplicated items is " + priorities1);

		System.out.println("--- part2 ---");
		var priorities2 = part2(lines);
		System.out.println("The priority of batch items is " + priorities2);
	}

	public static int part1(final List<String> lines) {
		var priorities = 0;

		for (final var line : lines) {
			if (line.length() % 2 != 0) {
				throw new IllegalArgumentException("Line is not dividable into equal large parts!");
			}

			final String rucksack1 = line.substring(0, (line.length() / 2));
			final String rucksack2 = line.substring((line.length() / 2));

			for (final var c : rucksack1.toCharArray()) {
				if (rucksack2.indexOf(c) != -1) {
					priorities += getPriorityOfChar(c);
					break;
				}
			}
		}

		return priorities;
	}

	public static int part2(final List<String> lines) {
		var priorities = 0;
		final List<List<String>> groups = InputFileUtils.partitionBy(lines, 3);

		for (var group : groups) {
			if (group.size() != 3) {
				throw new IllegalArgumentException("Group does not contain 3 members!");
			}

			final String member1Rucksack = group.get(0);
			final String member2Rucksack = group.get(1);
			final String member3Rucksack = group.get(2);

			for (final var c : member1Rucksack.toCharArray()) {
				if (member2Rucksack.indexOf(c) != -1 && member3Rucksack.indexOf(c) != -1) {
					priorities += getPriorityOfChar(c);
					break;
				}
			}
		}

		return priorities;
	}

	private static int getPriorityOfChar(final char c) {

		if (c >= 'a' && c <= 'z') {
			return c - 96;
		}

		if (c >= 'A' && c <= 'Z') {
			return c - 38;
		}

		throw new IllegalArgumentException(String.format("Character %c is not convertible to a priority", c));
	}
}
