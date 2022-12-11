package day11;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.InputFileUtils;

class Day11Test {

	@Test
	void part1() throws IOException {
		final var lines = InputFileUtils.getLines("day11/input-example.txt");
		final var result = Day11.part1(lines);
		assertEquals(10605L, result,
				"The two most active monkeys should have a monkey business level of 10605 after 20 rounds!");
	}

	@Test
	void part2() throws IOException {
		final var lines = InputFileUtils.getLines("day11/input-example.txt");
		final var result = Day11.part2(lines);
		assertEquals(2713310158L, result,
				"The two most active monkeys should have a monkey business level of 2713310158 after 10000 rounds!");
	}
}