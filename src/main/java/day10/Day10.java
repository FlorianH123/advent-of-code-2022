package day10;

import java.io.IOException;
import java.util.List;
import util.InputFileUtils;

public class Day10 {

	private static final int SCREEN_WIDTH = 40;
	private static final int SCREEN_HEIGHT = 6;

	private static final String SPRITE_PIXEL = "#";
	private static final String BACKGROUND_PIXEL = " ";

	public static void main(final String[] args) throws IOException {
		final var lines = InputFileUtils.getLines("day10/input.txt");

		System.out.println("--- part1 ---");
		final var result1 = part1(lines);
		System.out.println("The sum of all signal strengths is " + result1);

		System.out.println("--- part2 ---");
		System.out.println("The ASCII art below should show the 8 characters PZULBAUA");
		part2(lines);
	}

	public static int part1(final List<String> lines) {
		final var instructions = parseInstructions(lines);
		return executeInstructions(instructions, false);
	}

	public static void part2(final List<String> lines) {
		final var instructions = parseInstructions(lines);
		executeInstructions(instructions, true);
	}

	public static int executeInstructions(final List<Instruction> instructions, final boolean graphicalOutput) {
		int currentCycle = 1;
		int registerX = 1;
		int signalStrength = 0;
		int ip = 0;
		int instructionCycles = 0;

		while (ip < instructions.size()) {
			final var instruction = instructions.get(ip);

			if (instructionCycles == instruction.mnemonicE().getCycles()) {
				if (instruction.param() != null) {
					registerX += instruction.param();
				}
				instructionCycles = 0;
				ip++;
			}

			if (graphicalOutput && currentCycle <= SCREEN_WIDTH * SCREEN_HEIGHT) {
				final var pixel = (currentCycle - 1) % 40;
				System.out.print(isSpriteVisible(pixel, registerX) ? SPRITE_PIXEL : BACKGROUND_PIXEL);

				if (currentCycle % 40 == 0) {
					System.out.println();
				}
			}

			if (currentCycle == 20 || currentCycle % 40 == 20) {
				signalStrength += currentCycle * registerX;
			}

			currentCycle++;
			instructionCycles++;
		}

		return signalStrength;
	}

	private static boolean isSpriteVisible(final int pixel, final int registerValue) {
		return pixel >= registerValue - 1 && pixel <= registerValue + 1;
	}

	public static List<Instruction> parseInstructions(final List<String> lines) {
		return lines.stream().map(Instruction::fromString).toList();
	}
}
