package day8;

import java.io.IOException;
import java.util.List;
import util.InputFileUtils;

public class Day8 {
	public static void main(final String[] args) throws IOException {
		final var lines = InputFileUtils.getLines("day8/input.txt");

		System.out.println("--- part1 ---");
		final var result1 = part1(lines);
		System.out.println(result1 + " trees are visible");

		System.out.println("--- part2 ---");
		final var result2 = part2(lines);
		System.out.println("The maximum scenic score is " + result2);
	}

	public static long part1(final List<String> lines) {
		final var grid = parseTreeGrid(lines);

		final int outerTrees = ((grid.length + grid[0].length) * 2) - 4;
		final int visibleInnerTrees = grid.length > 2 && grid[0].length > 2 ? getVisibleInnerTrees(grid) : 0;

		return outerTrees + visibleInnerTrees;
	}

	public static long part2(final List<String> lines) {
		final var grid = parseTreeGrid(lines);
		return getHighestScenicScore(grid);
	}

	private static int getVisibleInnerTrees(final int[][] grid) {
		var visibleInnerTrees = 0;

		for (int y = 1; y < grid.length - 1; y++) {
			for (int x = 1; x < grid[y].length - 1; x++) {
				final var treeHeight = grid[y][x];

				if (treeHeight > 0 && (isVisibleFromLeft(grid[y], treeHeight, x)
						|| isVisibleFromRight(grid[y], treeHeight, x) || isVisibleFromTop(grid, treeHeight, x, y)
						|| isVisibleFromBottom(grid, treeHeight, x, y))) {
					visibleInnerTrees++;
				}
			}
		}

		return visibleInnerTrees;
	}

	private static boolean isVisibleFromLeft(final int[] line, final int treeHeight, final int xIndex) {
		for (int x = xIndex - 1; x >= 0; x--) {
			if (line[x] >= treeHeight) {
				return false;
			}
		}

		return true;
	}

	private static boolean isVisibleFromRight(final int[] line, final int treeHeight, final int xIndex) {
		for (int x = xIndex + 1; x < line.length; x++) {
			if (line[x] >= treeHeight) {
				return false;
			}
		}

		return true;
	}

	private static boolean isVisibleFromTop(final int[][] grid, final int treeHeight, final int xIndex,
			final int yIndex) {
		for (int y = yIndex - 1; y >= 0; y--) {
			final var currentTreeHeight = grid[y][xIndex];

			if (currentTreeHeight >= treeHeight) {
				return false;
			}
		}

		return true;
	}

	private static boolean isVisibleFromBottom(final int[][] grid, final int treeHeight, final int xIndex,
			final int yIndex) {
		for (int y = yIndex + 1; y < grid.length; y++) {
			final var currentTreeHeight = grid[y][xIndex];

			if (currentTreeHeight >= treeHeight) {
				return false;
			}
		}

		return true;
	}

	private static int[][] parseTreeGrid(final List<String> lines) {
		final int maxX = lines.get(0).length();
		final int maxY = lines.size();
		final var grid = new int[maxY][maxX];

		for (int y = 0; y < lines.size(); y++) {
			final String line = lines.get(y);
			final char[] charArray = line.toCharArray();

			for (int x = 0; x < charArray.length; x++) {
				grid[y][x] = Character.getNumericValue(charArray[x]);
			}
		}

		return grid;
	}

	private static int getHighestScenicScore(final int[][] grid) {
		var maxScenicScore = 0;

		for (int y = 1; y < grid.length - 1; y++) {
			for (int x = 1; x < grid[y].length - 1; x++) {
				final var treeHeight = grid[y][x];

				final var scenicScoreLeft = getScenicScoreLeft(grid[y], treeHeight, x);

				if (scenicScoreLeft == 0) {
					continue;
				}

				final var scenicScoreRight = getScenicScoreRight(grid[y], treeHeight, x);

				if (scenicScoreRight == 0) {
					continue;
				}

				final var scenicScoreTop = getScenicScoreTop(grid, treeHeight, x, y);

				if (scenicScoreTop == 0) {
					continue;
				}

				final var scenicScoreBottom = getScenicScoreBottom(grid, treeHeight, x, y);

				if (scenicScoreBottom == 0) {
					continue;
				}

				final var currentScenicScore = scenicScoreLeft * scenicScoreRight * scenicScoreTop * scenicScoreBottom;

				if (currentScenicScore > maxScenicScore) {
					maxScenicScore = currentScenicScore;
				}
			}
		}

		return maxScenicScore;
	}

	private static int getScenicScoreLeft(final int[] line, final int treeHeight, final int xIndex) {
		var scenicScoreLeft = 0;

		for (int x = xIndex - 1; x >= 0; x--) {
			if (line[x] >= treeHeight) {
				scenicScoreLeft++;
				break;
			}

			scenicScoreLeft++;
		}

		return scenicScoreLeft;
	}

	private static int getScenicScoreRight(final int[] line, final int treeHeight, final int xIndex) {
		var scenicScoreRight = 0;

		for (int x = xIndex + 1; x < line.length; x++) {
			if (line[x] >= treeHeight) {
				scenicScoreRight++;
				break;
			}

			scenicScoreRight++;
		}

		return scenicScoreRight;
	}

	private static int getScenicScoreTop(final int[][] grid, final int treeHeight, final int xIndex, final int yIndex) {
		var scenicScoreTop = 0;

		for (int y = yIndex - 1; y >= 0; y--) {
			var currentTreeHeight = grid[y][xIndex];

			if (currentTreeHeight >= treeHeight) {
				scenicScoreTop++;
				break;
			}

			scenicScoreTop++;
		}

		return scenicScoreTop;
	}

	private static int getScenicScoreBottom(final int[][] grid, final int treeHeight, final int xIndex,
			final int yIndex) {
		var scenicScoreBottom = 0;

		for (int y = yIndex + 1; y < grid.length; y++) {
			var currentTreeHeight = grid[y][xIndex];

			if (currentTreeHeight >= treeHeight) {
				scenicScoreBottom++;
				break;
			}

			scenicScoreBottom++;
		}

		return scenicScoreBottom;
	}
}
