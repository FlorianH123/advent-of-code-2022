package day1;

import org.junit.jupiter.api.Test;
import util.InputFileUtils;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Test {

	@Test
	public void part1Test() throws IOException {
		final List<String> lines = InputFileUtils.getLines("day1/input-example.txt");
		final List<List<String>> elves = InputFileUtils.partitionBy(lines, "");
		final int result = Day1.partOne(elves);

		assertEquals(24000, result, "The elf with the most calories should carry 24000");
	}

	@Test
	public void part2Test() throws IOException {
		final List<String> lines = InputFileUtils.getLines("day1/input-example.txt");
		final List<List<String>> elves = InputFileUtils.partitionBy(lines, "");
		final int result = Day1.partTwo(elves);

		assertEquals(45000, result, "The top 3 elves should carry 45000 calories");
	}
}
