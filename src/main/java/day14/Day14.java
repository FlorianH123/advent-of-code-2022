package day14;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import util.InputFileUtils;
import util.Pair;

public class Day14 {

	private static final Point SAND_START = new Point(500, 0);

	public static void main(final String[] args) throws IOException {

		final var lines = InputFileUtils.getLines("day14/input.txt");

		System.out.println("--- part1 ---");
		final var result1 = part1(lines);
		System.out.printf("%d units of sand came to rest before sands started falling into the abyss%n", result1);

		System.out.println("--- part2 ---");
		final var result2 = part2(lines);
		System.out.printf("%d units of sand came to rest before the source was blocked%n", result2);
	}

	public static int part1(final List<String> lines) {
		final var rockFormations = parseInput(lines);
		final var cave = initializeCave(rockFormations, false);

		cave.print();

		return simulateSand(cave);
	}

	public static int part2(final List<String> lines) {
		final var rockFormations = parseInput(lines);
		final var cave = initializeCave(rockFormations, true);

		cave.print();

		return simulateSand(cave);
	}

	private static int simulateSand(final Cave cave) {
		var sandUnits = 0;
		var fallInAbyss = false;
		var blockingSource = false;

		while (!fallInAbyss && !blockingSource) {
			if (cave.layout()[SAND_START.y - cave.minY()][SAND_START.x - cave.minX()] == 'o') {
				blockingSource = true;
				continue;
			}

			fallInAbyss = step(cave, SAND_START.x - cave.minX(), SAND_START.y - cave.minY());

			if (!fallInAbyss) {
				sandUnits++;
			}
		}

		cave.print();

		return sandUnits;
	}

	private static boolean step(final Cave cave, final int startX, final int startY) {
		int y = startY;
		final var caveLayout = cave.layout();

		while (true) {
			if (y > caveLayout.length) {
				return true;
			}

			if (canRest(caveLayout, startX, y)) {
				caveLayout[y][startX] = 'o';
				return false;
			}

			if (!canFallDown(caveLayout, startX, y)) {
				if (canFallLeft(caveLayout, startX, y)) {
					return step(cave, startX - 1, y);
				} else if (canFallRight(caveLayout, startX, y)) {
					return step(cave, startX + 1, y);
				} else {
					throw new IllegalArgumentException("Not possible");
				}
			}

			y++;
		}
	}

	private static boolean canRest(final char[][] caveLayout, final int x, final int y) {
		return !canFallDown(caveLayout, x, y) && !canFallLeft(caveLayout, x, y) && !canFallRight(caveLayout, x, y);
	}

	private static boolean canFallDown(final char[][] caveLayout, final int x, final int y) {
		return fallInAbyss(caveLayout, x, y + 1) || caveLayout[y + 1][x] == '.';
	}

	private static boolean canFallLeft(final char[][] caveLayout, final int x, final int y) {
		return fallInAbyss(caveLayout, x - 1, y + 1) || caveLayout[y + 1][x - 1] == '.';
	}

	private static boolean canFallRight(final char[][] caveLayout, final int x, final int y) {
		return fallInAbyss(caveLayout, x + 1, y + 1) || caveLayout[y + 1][x + 1] == '.';
	}

	private static boolean fallInAbyss(final char[][] caveLayout, final int x, final int y) {
		return y < 0 || y >= caveLayout.length || x < 0 || x >= caveLayout[y].length;
	}

	private static List<Pair<Point, Point>> parseInput(final List<String> lines) {
		final var rockFormations = new ArrayList<Pair<Point, Point>>();

		for (final var line : lines) {

			final var rockCoordinates = line.split(" -> ");
			Point startRock = null;

			for (final var rockCoordinate : rockCoordinates) {
				final var rock = parseRockCoordinate(rockCoordinate);

				if (startRock == null) {
					startRock = rock;
					continue;
				}

				rockFormations.add(Pair.of(startRock, rock));
				startRock = rock;
			}
		}

		return rockFormations;
	}

	private static Point parseRockCoordinate(final String rockCoordinate) {
		final var xyCoordinates = rockCoordinate.split(",");

		if (xyCoordinates.length != 2) {
			throw new IllegalArgumentException(rockCoordinate + "is not a valid rock coordinate");
		}

		return new Point(Integer.parseInt(xyCoordinates[0]), Integer.parseInt(xyCoordinates[1]));
	}

	private static Cave initializeCave(final List<Pair<Point, Point>> rockFormations, final boolean hasFloor) {
		var minX = rockFormations.stream().map(pair -> Math.min(pair.left().x, pair.right().x)).min(Integer::compare)
				.orElseThrow(IllegalArgumentException::new);
		var minY = rockFormations.stream().map(pair -> Math.min(pair.left().y, pair.right().y)).min(Integer::compare)
				.orElseThrow(IllegalArgumentException::new);
		var maxX = rockFormations.stream().map(pair -> Math.max(pair.left().x, pair.right().x)).max(Integer::compare)
				.orElseThrow(IllegalArgumentException::new);
		var maxY = rockFormations.stream().map(pair -> Math.max(pair.left().y, pair.right().y)).max(Integer::compare)
				.orElseThrow(IllegalArgumentException::new);

		minX = Math.min(minX, SAND_START.x);
		minY = Math.min(minY, SAND_START.y);
		maxX = Math.max(maxX, SAND_START.x);
		maxY = Math.max(maxY, SAND_START.y);

		if (hasFloor) {
			rockFormations.add(Pair.of(new Point(minX - maxY, maxY + 2), new Point(maxX + maxY, maxY + 2)));

			minX = rockFormations.stream().map(pair -> Math.min(pair.left().x, pair.right().x)).min(Integer::compare)
					.orElseThrow(IllegalArgumentException::new);
			minY = rockFormations.stream().map(pair -> Math.min(pair.left().y, pair.right().y)).min(Integer::compare)
					.orElseThrow(IllegalArgumentException::new);
			maxX = rockFormations.stream().map(pair -> Math.max(pair.left().x, pair.right().x)).max(Integer::compare)
					.orElseThrow(IllegalArgumentException::new);
			maxY = rockFormations.stream().map(pair -> Math.max(pair.left().y, pair.right().y)).max(Integer::compare)
					.orElseThrow(IllegalArgumentException::new);

			minX = Math.min(minX, SAND_START.x);
			minY = Math.min(minY, SAND_START.y);
			maxX = Math.max(maxX, SAND_START.x);
			maxY = Math.max(maxY, SAND_START.y);
		}

		final var xLength = Math.abs(minX - maxX) + 1;
		final var yLength = Math.abs(minY - maxY) + 1;

		final var cave = new char[yLength][xLength];

		for (final char[] chars : cave) {
			Arrays.fill(chars, '.');
		}

		for (final var rockFormation : rockFormations) {
			if (rockFormation.left().y == rockFormation.right().y) {
				final var startX = Math.min(rockFormation.left().x, rockFormation.right().x);
				final var endX = Math.max(rockFormation.left().x, rockFormation.right().x);

				for (int x = startX - minX; x <= endX - minX; x++) {
					cave[rockFormation.left().y - minY][x] = '#';
				}
			} else {
				final var startY = Math.min(rockFormation.left().y, rockFormation.right().y);
				final var endY = Math.max(rockFormation.left().y, rockFormation.right().y);

				for (int y = startY - minY; y <= endY - minY; y++) {
					cave[y][rockFormation.right().x - minX] = '#';
				}
			}
		}

		cave[SAND_START.y - minY][SAND_START.x - minX] = '+';

		return new Cave(minX, minY, maxX, maxY, cave);
	}
}
