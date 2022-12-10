package day10;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.InputFileUtils;

class Day10Test {

	@Test
	void part1() throws IOException {
		final var lines = InputFileUtils.getLines("day10/input-example.txt");
		final var result = Day10.part1(lines);
		assertEquals(13140, result, "The sum of the 20 signal strength should be 13140");
	}

	@Test
	void part2() throws IOException {
		final var lines = InputFileUtils.getLines("day10/input-example.txt");
		Day10.part2(lines);
		// No test result because of graphical output
	}
}