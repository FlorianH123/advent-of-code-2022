package day12;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.InputFileUtils;

public class Day12 {
	public static void main(final String[] args) throws IOException {
		final var lines = InputFileUtils.getLines("day12/input.txt");

		System.out.println("--- part1 ---");
		final var result1 = part1(lines);
		System.out.println("The shortest path to the top is " + result1 + " steps away");

		System.out.println("--- part2 ---");
		final var result2 = part2(lines);
		System.out.println("The shortest path to the top is " + result2 + " steps away");
	}

	public static int part1(final List<String> lines) {
		final var grid = parseInput(lines);
		final var graph = getDijkstraGraph(grid);
		final var startNode = graph.getStartNode();
		final var destinationNode = graph.getDestinationNode();
		DijkstraAlgorithm.calculateShortestPathFromSource(startNode);

		final var path = destinationNode.getShortestPath();

		System.out.println(path);

		return path.size();
	}

	public static int part2(final List<String> lines) {
		final var grid = parseInput(lines);
		var graph = getDijkstraGraph(grid);
		int minPathLength = Integer.MAX_VALUE;
		final var destinationNode = graph.getDestinationNode();

		final var startNodes = graph.getNodes().stream().filter(node -> node.getElevation() == 0).toList();

		for (final var startNode : startNodes) {
			DijkstraAlgorithm.calculateShortestPathFromSource(startNode);
			final var path = destinationNode.getShortestPath();

			if (path.size() > 0) {
				minPathLength = Math.min(path.size(), minPathLength);
			}

			graph.reset();
		}

		return minPathLength;
	}

	private static char[][] parseInput(final List<String> lines) {
		final char[][] grid = new char[lines.size()][lines.get(0).length()];

		for (int y = 0; y < lines.size(); y++) {
			final char[] row = lines.get(y).toCharArray();
			System.arraycopy(row, 0, grid[y], 0, row.length);
		}

		return grid;
	}

	public static Graph getDijkstraGraph(final char[][] grid) {
		final var graph = new Graph();
		final var nodeMap = new HashMap<Point, Node>();

		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++) {
				final var currentPoint = new Point(x, y);
				final var node = getNode(nodeMap, currentPoint);
				node.setElevation(getElevation(grid[y][x]));

				if (grid[y][x] == 'S') {
					node.setType(Node.NodeTypeE.START);
				}

				if (grid[y][x] == 'E') {
					node.setType(Node.NodeTypeE.DESTINATION);
				}

				getAdjacentNodes(nodeMap, x, y, grid).forEach(neighbourNode -> node.addDestination(neighbourNode, 1));
				graph.addNode(node);
			}
		}

		return graph;
	}

	public static List<Node> getAdjacentNodes(final Map<Point, Node> nodeMap, final int x, final int y,
			final char[][] grid) {
		final var nodes = new ArrayList<Node>();

		final var possibleNeighbours = List.of(new Point(x, y - 1), new Point(x - 1, y), new Point(x, y + 1),
				new Point(x + 1, y));
		final var currentPointElevation = getElevation(grid[y][x]);

		for (final var possibleNeighbour : possibleNeighbours) {
			if (possibleNeighbour.y < 0 || possibleNeighbour.y >= grid.length || possibleNeighbour.x < 0
					|| possibleNeighbour.x >= grid[y].length) {
				continue;
			}

			final var neighbourElevation = getElevation(grid[possibleNeighbour.y][possibleNeighbour.x]);

			if (neighbourElevation - currentPointElevation > 1) {
				continue;
			}

			nodes.add(getNode(nodeMap, possibleNeighbour));
		}

		return nodes;
	}

	private static Node getNode(final Map<Point, Node> nodeMap, final Point currentPoint) {
		Node node;

		if (nodeMap.containsKey(currentPoint)) {
			node = nodeMap.get(currentPoint);
		} else {
			node = new Node(String.format("y=%s,x=%s", currentPoint.y, currentPoint.x));
			nodeMap.put(currentPoint, node);
		}

		return node;
	}

	private static int getElevation(char c) {
		if (c == 'S') {
			c = 'a';
		}

		if (c == 'E') {
			c = 'z';
		}

		return c - 'a';
	}
}
