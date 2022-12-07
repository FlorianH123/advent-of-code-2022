package day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {

    @Test
    void part1() {
        var result = Day6.part1("mjqjpqmgbljsphdztnvjfqwrcgsmlb");
        assertEquals(7, result);

        result = Day6.part1("bvwbjplbgvbhsrlpgdmjqwftvncz");
        assertEquals(5, result);

        result = Day6.part1("nppdvjthqldpwncqszvftbrmjlhg");
        assertEquals(6, result);

        result = Day6.part1("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg");
        assertEquals(10, result);

        result = Day6.part1("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw");
        assertEquals(11, result);
    }

    @Test
    void part2() {
        var result = Day6.part2("mjqjpqmgbljsphdztnvjfqwrcgsmlb");
        assertEquals(19, result);

        result = Day6.part2("bvwbjplbgvbhsrlpgdmjqwftvncz");
        assertEquals(23, result);

        result = Day6.part2("nppdvjthqldpwncqszvftbrmjlhg");
        assertEquals(23, result);

        result = Day6.part2("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg");
        assertEquals(29, result);

        result = Day6.part2("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw");
        assertEquals(26, result);
    }
}
