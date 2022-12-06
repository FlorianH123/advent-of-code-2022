package day5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Instruction {
    private final int amount;
    private final int fromStack;

    private final int toStack;

    public Instruction(final int amount, final int fromStack, final int toStack) {
        this.amount = amount;
        this.fromStack = fromStack;
        this.toStack = toStack;
    }

    public static Instruction fromString(final String instructionString) {
        final Pattern pattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
        final Matcher matcher = pattern.matcher(instructionString);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Cannot parse instruction " + instructionString);
        }

        final var amount = Integer.parseInt(matcher.group(1));
        final var fromStack = Integer.parseInt(matcher.group(2));
        final var toStack = Integer.parseInt(matcher.group(3));

        return new Instruction(amount, fromStack, toStack);
    }

    public int getAmount() {
        return amount;
    }

    public int getFromStack() {
        return fromStack;
    }

    public int getToStack() {
        return toStack;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "amount=" + amount +
                ", fromStack=" + fromStack +
                ", toStack=" + toStack +
                '}';
    }
}