package day4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.InputFileUtils;

class Day4Test {

	@Test
	void part1() throws IOException {
		final var lines = InputFileUtils.getLines("day4/input-example.txt");
		var result = Day4.part1(lines);

		assertEquals(2, result, "There should be two fully contained sections!");
	}

	@Test
	void part2() throws IOException {
		final var lines = InputFileUtils.getLines("day4/input-example.txt");
		var result = Day4.part2(lines);

		assertEquals(4, result, "There should be 4 overlapping sections!");
	}
}
