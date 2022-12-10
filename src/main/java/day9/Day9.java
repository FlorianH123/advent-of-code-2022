package day9;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;
import util.InputFileUtils;

public class Day9 {
	private static final boolean LOG_VISITED_PLACES_BY_TAIL = true;
	private static final boolean LOG_PATH_ITERATIVE = false;
	private static int LOG_PATH_ITERATION = 0;

	public static void main(final String[] args) throws IOException {
		final var linesPart1 = InputFileUtils.getLines("day9/input.txt");

		System.out.println("--- part1 ---");
		final var result1 = part1(linesPart1);
		System.out.println("The tail of the rope visited " + result1 + " positions at least once");

		final var linesPart2 = InputFileUtils.getLines("day9/input-2.txt");

		System.out.println("--- part2 ---");
		final var result2 = part2(linesPart2);
		System.out.println("The tail of the rope visited " + result2 + " positions at least once");
	}

	public static int part1(final List<String> lines) {
		return move(lines, 2);
	}

	public static int part2(final List<String> lines) {
		return move(lines, 10);
	}

	private static int move(final List<String> lines, final int robeLength) {
		final LinkedList<Point> points = new LinkedList<>(Stream.generate(Point::new).limit(robeLength).toList());
		printGrid(points, 3, 3);

		final var visitedPositions = new HashSet<Point>();
		visitedPositions.add(new Point());

		for (final var line : lines) {
			final var moveInstruction = MoveInstruction.fromString(line);
			executeMoveInstruction(visitedPositions, points, moveInstruction);
		}

		printGrid(visitedPositions, 3, 3);

		return visitedPositions.size();
	}

	private static void executeMoveInstruction(final Set<Point> visitedPositions, final LinkedList<Point> nodes,
			final MoveInstruction moveInstruction) {
		final Point pointHead = nodes.getHead().getValue();
		final Point pointTail = nodes.getTail().getValue();

		for (int i = 0; i < moveInstruction.steps(); i++) {

			switch (moveInstruction.direction()) {
				case UP -> pointHead.y++;
				case DOWN -> pointHead.y--;
				case LEFT -> pointHead.x--;
				case RIGHT -> pointHead.x++;
			}

			printGrid(nodes, 3, 3);

			for (final var node : nodes) {
				if (node.getNext() != null) {
					final var currentPointX = node.getNext().getValue().x;
					final var currentPointY = node.getNext().getValue().y;
					getTailPosition(node.getValue(), node.getNext().getValue());

					if (node.getNext().getValue().x != currentPointX || node.getNext().getValue().y != currentPointY) {
						printGrid(nodes, 3, 3);
					}
				}
			}

			visitedPositions.add(new Point(pointTail.x, pointTail.y));
		}
	}

	private static void getTailPosition(final Point previousPoint, final Point currentPoint) {
		int newX = currentPoint.x;
		int newY = currentPoint.y;

		if (Math.abs(previousPoint.x - currentPoint.x) > 1) {
			if (currentPoint.y != previousPoint.y) {
				newY += (int) Math.signum(previousPoint.y - currentPoint.y);
			}
			newX += (int) Math.signum(previousPoint.x - currentPoint.x);
		} else if (Math.abs(previousPoint.y - currentPoint.y) > 1) {
			if (currentPoint.x != previousPoint.x) {
				newX += (int) Math.signum(previousPoint.x - currentPoint.x);
			}
			newY += (int) Math.signum(previousPoint.y - currentPoint.y);
		}

		currentPoint.x = newX;
		currentPoint.y = newY;
	}

	private static void printGrid(final Set<Point> visitedPositions, final int paddingX, final int paddingY) {
		if (!LOG_VISITED_PLACES_BY_TAIL) {
			return;
		}

		final var maxX = visitedPositions.stream().map(point -> point.x).max(Integer::compare).orElse(0) + paddingX;
		final var maxY = visitedPositions.stream().map(point -> point.y).max(Integer::compare).orElse(0) + paddingY;
		final var minX = visitedPositions.stream().map(point -> point.x).min(Integer::compare).orElse(0) - paddingX;
		final var minY = visitedPositions.stream().map(point -> point.y).min(Integer::compare).orElse(0) - paddingY;

		for (int y = maxY; y >= minY; y--) {
			for (int x = minX; x <= maxX; x++) {
				int finalX = x;
				int finalY = y;
				final var hasVisited = visitedPositions.stream()
						.anyMatch(point -> point.equals(new Point(finalX, finalY)));
				System.out.print(hasVisited ? "# " : ". ");
			}
			System.out.println();
		}
	}

	private static void printGrid(final LinkedList<Point> points, final int paddingX, final int paddingY) {
		if (!LOG_PATH_ITERATIVE) {
			return;
		}

		System.out.println(LOG_PATH_ITERATION + ".");
		LOG_PATH_ITERATION++;

		final var list = new ArrayList<Point>();

		for (final var point : points) {
			list.add(point.getValue());
		}

		final var maxX = list.stream().map(point -> point.x).max(Integer::compare).orElse(0) + paddingX;
		final var maxY = list.stream().map(point -> point.y).max(Integer::compare).orElse(0) + paddingY;
		final var minX = list.stream().map(point -> point.x).min(Integer::compare).orElse(0) - paddingX;
		final var minY = list.stream().map(point -> point.y).min(Integer::compare).orElse(0) - paddingY;

		for (int y = maxY; y >= minY; y--) {
			for (int x = minX; x <= maxX; x++) {
				int finalX = x;
				int finalY = y;
				final var currentPointOptional = list.stream().filter(point -> point.equals(new Point(finalX, finalY)))
						.findFirst();

				if (currentPointOptional.isPresent()) {
					final var currentPoint = currentPointOptional.get();

					if (currentPoint.equals(points.getHead().getValue())) {
						System.out.print("H ");
					} else if (currentPoint.equals(points.getTail().getValue())) {
						System.out.print("T ");
					} else {
						System.out.print("# ");
					}
				} else if (x == 0 && y == 0) {
					System.out.print("s ");
				} else {
					System.out.print(". ");
				}
			}
			System.out.println();
		}

		System.out.println();
	}
}
