package day5;

import util.InputFileUtils;

import java.io.IOException;
import java.util.*;

public class Day5 {
    public static void main(String[] args) throws IOException {
        final var lines = InputFileUtils.getLines("day5/input.txt");

        System.out.println("--- part1 ---");
        final var result1 = part1(lines);
        System.out.println("Top top crates show " + result1);

        System.out.println("--- part2 ---");
        final var result2 = part2(lines);
        System.out.println("Top top crates show " + result2);
    }

    public static String part1(final List<String> lines) {
        return process(lines, CraneTypeE.CrateMover_9000);
    }

    public static String part2(final List<String> lines) {
        return process(lines, CraneTypeE.CrateMover_9001);
    }

    private static String process(final List<String> lines, final CraneTypeE crane) {
        final var splitList = InputFileUtils.partitionBy(lines, "");
        final var crateLineStrings = splitList.get(0);

        final var stacksNumberString = crateLineStrings.get(crateLineStrings.size() - 1);
        final var crateStacks = createCrateStacks(stacksNumberString);
        final var crateStackSetups = crateLineStrings.subList(0, crateLineStrings.size() - 1);

        setupStackLayout(crateStackSetups, crateStacks);

        final var instructions = parseInstructions(splitList.get(1));

        switch (crane) {
            case CrateMover_9000 -> executeInstructions(instructions, crateStacks, false);
            case CrateMover_9001 -> executeInstructions(instructions, crateStacks, true);
        }

        return buildTopCratesMessage(crateStacks);
    }

    private static void executeInstructions(final List<Instruction> instructions, final List<Stack<String>> crateStacks,
                                            final boolean canMoveMultiple) {
        for (final var instruction : instructions) {
            final var fromStackNumber = instruction.getFromStack();
            final var toStackNumber = instruction.getToStack();

            final var fromStack = crateStacks.get(fromStackNumber - 1);
            final var toStack = crateStacks.get(toStackNumber - 1);

            if (canMoveMultiple) {
                final List<String> poppedCrates = new ArrayList<>();
                for (int i = 0; i < instruction.getAmount(); i++) {
                    poppedCrates.add(fromStack.pop());
                }
                Collections.reverse(poppedCrates);
                poppedCrates.forEach(toStack::push);
            } else {
                for (int i = 0; i < instruction.getAmount(); i++) {
                    if (fromStack.isEmpty()) {
                        throw new IllegalArgumentException(String
                                .format("%s want to pop from stack %d but it was empty", instruction, fromStackNumber));
                    }

                    final var crate = fromStack.pop();
                    toStack.push(crate);
                }
            }
        }
    }

    private static String buildTopCratesMessage(final List<Stack<String>> crateStacks) {
        final var sb = new StringBuilder();

        for (final var stack : crateStacks) {
            if (!stack.isEmpty()) {
                sb.append(stack.peek());
            }
        }

        return sb.toString();
    }

    /**
     * Returns a list with stacks
     *
     * @param stacksNumberString " 1 2 3"
     */
    private static List<Stack<String>> createCrateStacks(final String stacksNumberString) {
        final List<Stack<String>> crateStacks = new ArrayList<>();
        Arrays.stream(stacksNumberString.trim().split("\\s{3}")).forEach(c -> crateStacks.add(new Stack<>()));

        return crateStacks;
    }

    private static void setupStackLayout(final List<String> crateStackSetups, final List<Stack<String>> crateStacks) {
        for (int i = crateStackSetups.size() - 1; i >= 0; i--) {
            var cratesString = crateStackSetups.get(i);
            var cratesCharArray = cratesString.toCharArray();
            var j = 1;
            var crateIndex = 0;

            while (j < cratesCharArray.length) {
                final Character c = cratesCharArray[j];
                if (!c.equals(' ')) {
                    crateStacks.get(crateIndex).push(String.valueOf(c));
                }

                crateIndex++;
                j += 4;
            }
        }
    }

    private static List<Instruction> parseInstructions(final List<String> instructionStrings) {
        return instructionStrings.stream().map(Instruction::fromString).toList();
    }
}
