package day12;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.InputFileUtils;

class Day12Test {

	@Test
	void part1() throws IOException {
		final var lines = InputFileUtils.getLines("day12/input-example.txt");
		final var result = Day12.part1(lines);
		assertEquals(31, result, "The shortest path should be 31 steps");
	}

	@Test
	void part2() throws IOException {
		final var lines = InputFileUtils.getLines("day12/input-example.txt");
		final var result = Day12.part2(lines);
		assertEquals(29, result, "The shortest path should be 29 steps");
	}
}