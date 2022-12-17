package day14;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.InputFileUtils;

class Day14Test {

	@Test
	void part1() throws IOException {
		final var lines = InputFileUtils.getLines("day14/input-example.txt");
		final var result = Day14.part1(lines);
		assertEquals(24, result,
				"The amount of sand unit resting before sand starts falling into the abyss should be 24");
	}

	@Test
	void part2() throws IOException {
		final var lines = InputFileUtils.getLines("day14/input-example.txt");
		final var result = Day14.part2(lines);
		assertEquals(93, result, "The amount of sand unit resting before blocking the source should be 93");
	}
}