package day4;

import util.InputFileUtils;
import util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day4 {
    public static void main(String[] args) throws IOException {
        final var lines = InputFileUtils.getLines("day4/input.txt");

        System.out.println("--- part1 ---");
        final var totalMatches1 = part1(lines);
        System.out.printf("There are %d fully overlapping sections%n", totalMatches1);

        System.out.println("--- part2 ---");
        final var totalMatches2 = part2(lines);
        System.out.printf("There are %d overlapping sections%n", totalMatches2);
    }

    public static int part1(final List<String> lines) {
        final var linesSpread = spreadLines(lines);
        var totalMatches = 0;

        for (final var pair : linesSpread) {
            final var leftElf = "," + String.join(",", pair.left()) + ",";
            final var rightElf = "," + String.join(",", pair.right()) + ",";

            if (leftElf.contains(rightElf) || rightElf.contains(leftElf)) {
                totalMatches++;
            }
        }

        return totalMatches;
    }

    public static int part2(final List<String> lines) {
        final var linesSpread = spreadLines(lines);
        var totalMatches = 0;

        for (final var pair : linesSpread) {
            final var leftElf = pair.left();
            final var rightElf = pair.right();
            final Set<String> leftElfSectionSet = new HashSet<>(leftElf);
            final Set<String> rightElfSectionSet = new HashSet<>(rightElf);
            var duplicatedSectionFound = false;

            for (var section : leftElf) {
                if (rightElfSectionSet.contains(section)) {
                    totalMatches++;
                    duplicatedSectionFound = true;
                    break;
                }
            }

            if (!duplicatedSectionFound) {
                for(var section : rightElf) {
                    if (leftElfSectionSet.contains(section)) {
                        totalMatches++;
                        break;
                    }
                }
            }
        }

        return totalMatches;
    }

    private static List<Pair<List<String>, List<String>>> spreadLines(final List<String> lines) {
        return lines.stream().map(line -> {
            if (!line.contains(",")) {
                throw new IllegalArgumentException("Line cannot be split!");
            }

            final var linesSplit = line.split(",");
            final var firstElf = spreadSections(linesSplit[0]);
            final var secondElf = spreadSections(linesSplit[1]);

            return Pair.of(firstElf, secondElf);
        }).toList();
    }

    private static List<String> spreadSections(final String sections) {
        final var sectionSplit = sections.split("-");
        final var sectionList = new ArrayList<String>();

        for (int i = Integer.parseInt(sectionSplit[0]) ; i <= Integer.parseInt(sectionSplit[1]) ; i++) {
            sectionList.add(String.valueOf(i));
        }

        return sectionList;
    }
}
