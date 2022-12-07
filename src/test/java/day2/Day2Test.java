package day2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.InputFileUtils;

public class Day2Test {

	@Test()
	public void part1Test() throws IOException {
		final var lines = InputFileUtils.getLines("day2/input-example.txt");
		final var totalScore = Day2.part1(lines);

		assertEquals(15, totalScore, "You should score a total of 15");
	}

	@Test()
	public void part2Test() throws IOException {
		final var lines = InputFileUtils.getLines("day2/input-example.txt");
		final var totalScore = Day2.part2(lines);

		assertEquals(12, totalScore, "You should score a total of 12");
	}
}
