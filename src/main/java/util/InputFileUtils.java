package util;

import day1.Day1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InputFileUtils {

	public static List<String> getLines(final String filename) throws IOException {
		try (final InputStream is = Day1.class.getClassLoader().getResourceAsStream(filename)) {
			if (is == null) {
				throw new FileNotFoundException("File " + filename + " not found!");
			}

			try (final InputStreamReader isr = new InputStreamReader(is);
					final BufferedReader br = new BufferedReader(isr)) {
				return br.lines().toList();
			}
		}
	}

	public static String getString(final String filename) throws IOException {
		try (final InputStream is = Day1.class.getClassLoader().getResourceAsStream(filename)) {
			if (is == null) {
				throw new FileNotFoundException("File " + filename + " not found!");
			}

			try (final InputStreamReader isr = new InputStreamReader(is);
					final BufferedReader br = new BufferedReader(isr)) {
				return br.readLine();
			}
		}
	}

	public static List<List<String>> partitionBy(final List<String> lines, final int batchSize) {
		final List<List<String>> partionedList = new ArrayList<>();
		var index = 0;

		if (!lines.isEmpty()) {
			partionedList.add(new ArrayList<>());
		}

		for (final var line : lines) {
			if (partionedList.get(index).size() == batchSize) {
				index++;
				partionedList.add(new ArrayList<>());
			}

			partionedList.get(index).add(line);
		}

		return partionedList;
	}

	public static List<List<String>> partitionBy(final List<String> lines, final String separator) {
		final List<List<String>> partitionedList = new ArrayList<>();
		var index = 0;

		if (!lines.isEmpty()) {
			partitionedList.add(new ArrayList<>());
		}

		for (final var line : lines) {
			if (line.equals(separator)) {
				partitionedList.add(new ArrayList<>());
				index++;
			} else {
				partitionedList.get(index).add(line);
			}
		}

		return partitionedList;
	}
}
