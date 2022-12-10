package day9;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.InputFileUtils;

class Day9Test {

	@Test
	void part1() throws IOException {
		final var lines = InputFileUtils.getLines("day9/input-example.txt");
		final var result = Day9.part1(lines);
		assertEquals(13, result, "The tail should have visited 13 different positions");
	}

	@Test
	void part2() throws IOException {
		final var lines = InputFileUtils.getLines("day9/input-example-2.txt");
		final var result = Day9.part2(lines);
		assertEquals(36, result, "The tail should have visited 36 different positions");
	}
}