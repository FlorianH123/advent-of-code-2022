package day13;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.InputFileUtils;

class Day13Test {

	@Test
	void part1() throws IOException {
		final var lines = InputFileUtils.getLines("day13/input-example.txt");
		final var result = Day13.part1(lines);
		assertEquals(13, result, "The sum of indices which are already in order should be 13");
	}

	@Test
	void part2() throws IOException {
		final var lines = InputFileUtils.getLines("day13/input-example.txt");
		final var result = Day13.part2(lines);
		assertEquals(140, result, "The product of both divider package indices should be 140");
	}
}