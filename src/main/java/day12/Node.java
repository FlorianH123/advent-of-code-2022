package day12;

import java.util.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Node {
	@Getter
	private final Map<Node, Integer> adjacentNodes = new HashMap<>();
	@ToString.Include
	@EqualsAndHashCode.Include
	private final String name;
	@Getter
	@Setter
	private NodeTypeE type = NodeTypeE.PATH;

	@Getter
	@Setter
	private List<Node> shortestPath = new LinkedList<>();

	@Getter
	@Setter
	private Integer distance = Integer.MAX_VALUE;

	@Getter
	@Setter
	private int elevation;

	public Node(final String name) {
		this.name = name;
	}

	public void addDestination(final Node destination, final int distance) {
		adjacentNodes.put(destination, distance);
	}

	public enum NodeTypeE {
		START, DESTINATION, PATH
	}

	public void reset() {
		this.distance = Integer.MAX_VALUE;
		this.shortestPath = new LinkedList<>();
	}
}
