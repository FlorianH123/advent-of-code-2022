package day3;

import org.junit.jupiter.api.Test;
import util.InputFileUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {

    @Test
    public void part1() throws IOException {
        final var lines = InputFileUtils.getLines("day3/input-example.txt");
        final var priority = Day3.part1(lines);

        assertEquals(157, priority, "The priority of duplicated items should be 157");
    }

    @Test
    public void part2() throws IOException {
        final var lines = InputFileUtils.getLines("day3/input-example.txt");
        final var priority = Day3.part2(lines);

        assertEquals(70, priority, "The priority of badge items should be 70");
    }
}
