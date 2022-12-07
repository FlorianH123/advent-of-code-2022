package day7;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.InputFileUtils;

class Day7Test {

	@Test
	void part1() throws IOException {
		final var lines = InputFileUtils.getLines("day7/input-example.txt");
		var result = Day7.part1(lines);
		assertEquals(95437, result);
	}

	@Test
	void part2() throws IOException {
		final var lines = InputFileUtils.getLines("day7/input-example.txt");
		var result = Day7.part2(lines);
		assertEquals(24933642, result);
	}
}
