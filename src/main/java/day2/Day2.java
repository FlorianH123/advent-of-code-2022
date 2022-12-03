package day2;

import util.InputFileUtils;

import java.io.*;
import java.util.List;

public class Day2 {
    public static void main(final String[] args) throws IOException {
        final var lines = InputFileUtils.getLines("day2/input.txt");

        System.out.println("--- Part 1 ---");
        var totalScore1 = part1(lines);
        System.out.println("You should score a total of " + totalScore1);

        System.out.println("--- Part 2 ---");
        var totalScore2 = part2(lines);
        System.out.println("You should score a total of " + totalScore2);
    }

    public static int part1(final List<String> lines) {
        final int totalScore = lines.stream().map(line -> {
            final String[] choices = line.split(" ");
            final ChoiceE opponentChoice = decryptOpponentChoice(choices[0]);
            final ChoiceE myChoice = decryptMyChoice(choices[1]);

            final var winner = ChoiceE.getWinner(opponentChoice, myChoice);
            final GameResultE gameResult = switch (winner) {
                case 0 -> GameResultE.DRAW;
                case -1 -> GameResultE.WIN;
                case 1 -> GameResultE.LOSE;
                default -> throw new IllegalArgumentException();
            };

            return myChoice.getValue() + gameResult.getValue();
        }).reduce(0, Integer::sum);

        return totalScore;
    }

    public static int part2(final List<String> lines) {
        final int totalScore = lines.stream().map(line -> {
            final String[] choices = line.split(" ");
            final ChoiceE opponentChoice = decryptOpponentChoice(choices[0]);
            final GameResultE gameResult = decryptGameResult(choices[1]);

            final var myChoice = ChoiceE.getChoiceForGameResult(opponentChoice, gameResult);

            return myChoice.getValue() + gameResult.getValue();
        }).reduce(0, Integer::sum);

        return totalScore;
    }

    private static GameResultE decryptGameResult(final String encryptedGameResult   ) {
        return switch (encryptedGameResult) {
            case "X" -> GameResultE.LOSE;
            case "Y" -> GameResultE.DRAW;
            case "Z" -> GameResultE.WIN;
            default -> throw new IllegalArgumentException("Unknown input!");
        };
    }

    private static ChoiceE decryptMyChoice(final String encryptedChoice) {
        return switch (encryptedChoice) {
            case "X" -> ChoiceE.ROCK;
            case "Y" -> ChoiceE.PAPER;
            case "Z" -> ChoiceE.SCISSORS;
            default -> throw new IllegalArgumentException("Unknown input!");
        };
    }

    private static ChoiceE decryptOpponentChoice(final String encryptedChoice) {
        return switch (encryptedChoice) {
            case "A" -> ChoiceE.ROCK;
            case "B" -> ChoiceE.PAPER;
            case "C" -> ChoiceE.SCISSORS;
            default -> throw new IllegalArgumentException("Unknown input!");
        };
    }
}
