package day4;

import util.InputFileUtils;
import util.Pair;

import java.io.IOException;
import java.util.List;

public class Day4 {
    public static void main(final String[] args) throws IOException {
        final var lines = InputFileUtils.getLines("day4/input.txt");

        System.out.println("--- part1 ---");
        final var totalMatches1 = part1(lines);
        System.out.printf("There are %d fully overlapping sections%n", totalMatches1);

        System.out.println("--- part2 ---");
        final var totalMatches2 = part2(lines);
        System.out.printf("There are %d overlapping sections%n", totalMatches2);
    }

    public static long part1(final List<String> lines) {
        final var sectionBoundaries = getSectionBoundaries(lines);

        return sectionBoundaries.stream().filter((boundaryPair) -> {
            final var leftSectionBoundary = boundaryPair.left();
            final var rightSectionBoundary = boundaryPair.right();

            return leftSectionBoundary.containsBoundary(rightSectionBoundary)
                    || rightSectionBoundary.containsBoundary(leftSectionBoundary);
        }).count();
    }

    public static long part2(final List<String> lines) {
        final var sectionBoundaries = getSectionBoundaries(lines);

        return sectionBoundaries.stream().filter((boundaryPair) -> {
            final var leftSectionBoundary = boundaryPair.left();
            final var rightSectionBoundary = boundaryPair.right();

            return leftSectionBoundary.hasSectionInBoundary(rightSectionBoundary)
                    || rightSectionBoundary.hasSectionInBoundary(leftSectionBoundary);
        }).count();
    }

    private static List<Pair<SectionBoundary, SectionBoundary>> getSectionBoundaries(final List<String> lines) {
        return lines.stream().map(line -> {
            if (!line.contains(",")) {
                throw new IllegalArgumentException("Line cannot be split!");
            }

            final var linesSplit = line.split(",");
            final var leftSectionBoundary = getSectionBoundaries(linesSplit[0]);
            final var rightSectionBoundary = getSectionBoundaries(linesSplit[1]);

            return Pair.of(leftSectionBoundary, rightSectionBoundary);
        }).toList();
    }

    private static SectionBoundary getSectionBoundaries(final String sections) {
        if (!sections.contains("-")) {
            throw new IllegalArgumentException("Section boundary not contains \"-\"");
        }

        final var boundary = sections.split("-");
        return SectionBoundary.of(Integer.parseInt(boundary[0]), Integer.parseInt(boundary[1]));
    }
}
