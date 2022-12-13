package day12;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class DijkstraAlgorithm {

	public static void calculateShortestPathFromSource(final Node startNode) {
		// Start node has a distance of 0
		startNode.setDistance(0);

		final Set<Node> settledNodes = new HashSet<>();
		final Set<Node> unsettledNodes = new HashSet<>();

		unsettledNodes.add(startNode);

		while (unsettledNodes.size() != 0) {
			final Node currentNode = getLowestDistanceNode(unsettledNodes);
			unsettledNodes.remove(currentNode);

			for (final var adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
				final Node adjacentNode = adjacencyPair.getKey();
				final Integer edgeWeight = adjacencyPair.getValue();

				if (!settledNodes.contains(adjacentNode)) {
					calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
					unsettledNodes.add(adjacentNode);
				}
			}

			settledNodes.add(currentNode);
		}
	}

	private static Node getLowestDistanceNode(final Set<Node> unsettledNodes) {
		Node lowestDistanceNode = null;
		int lowestDistance = Integer.MAX_VALUE;

		for (final Node node : unsettledNodes) {
			final int nodeDistance = node.getDistance();

			if (nodeDistance < lowestDistance) {
				lowestDistance = nodeDistance;
				lowestDistanceNode = node;
			}
		}

		return lowestDistanceNode;
	}

	private static void calculateMinimumDistance(final Node evaluationNode, final Integer edgeWeigh,
			final Node sourceNode) {
		final Integer sourceDistance = sourceNode.getDistance();

		if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
			evaluationNode.setDistance(sourceDistance + edgeWeigh);
			LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
			shortestPath.add(sourceNode);
			evaluationNode.setShortestPath(shortestPath);
		}
	}
}
