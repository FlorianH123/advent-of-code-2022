package day8;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.InputFileUtils;

class Day8Test {

	@Test
	void part1() throws IOException {
		final var lines = InputFileUtils.getLines("day8/input-example.txt");
		final var result = Day8.part1(lines);

		assertEquals(21, result, "A total of 21 trees should be visible");
	}

	@Test
	void part2() throws IOException {
		final var lines = InputFileUtils.getLines("day8/input-example.txt");
		final var result = Day8.part2(lines);

		assertEquals(8, result, "The maximum scenic score should be 8");
	}
}