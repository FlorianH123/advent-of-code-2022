package day6;

import java.io.IOException;
import java.util.HashSet;
import util.InputFileUtils;

public class Day6 {
	public static void main(String[] args) throws IOException {
		final var buffer = InputFileUtils.getString("day6/input.txt");

		System.out.println("--- part1 ---");
		final var result1 = part1(buffer);
		System.out.println("Start-of-paket marker at position " + result1);

		System.out.println("--- part2 ---");
		final var result2 = part2(buffer);
		System.out.println("Start-of-message marker at position " + result2);
	}

	public static long part1(final String buffer) {
		return getMarker(buffer, 4);
	}

	public static long part2(final String buffer) {
		return getMarker(buffer, 14);
	}

	private static long getMarker(final String buffer, final int numDistinctChars) {
		int i = 0;
		final char[] chars = buffer.toCharArray();

		while (i < chars.length) {
			final var charSet = new HashSet<>();

			for (int j = i; j < i + numDistinctChars; j++) {
				final var c = chars[j];
				charSet.add(c);
			}

			if (charSet.size() == numDistinctChars) {
				return i + numDistinctChars;
			}

			i++;
		}

		throw new IllegalArgumentException("Buffer does not contain a marker!");
	}
}
