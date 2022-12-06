package day5;

import org.junit.jupiter.api.Test;
import util.InputFileUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day5Test {

    @Test
    void part1() throws IOException {
        final var lines = InputFileUtils.getLines("day5/input-example.txt");
        final var result = Day5.part1(lines);

        assertEquals("CMZ", result, "The top crate from each stack should show CMZ!");
    }

    @Test
    void part2() throws IOException {
        final var lines = InputFileUtils.getLines("day5/input-example.txt");
        final var result = Day5.part2(lines);

        assertEquals("MCD", result, "The top crate from each stack should show MCD!");
    }
}