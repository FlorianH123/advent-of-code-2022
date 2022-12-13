package day12;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Graph {
	private final List<Node> nodes = new ArrayList<>();

	public void addNode(Node nodeA) {
		nodes.add(nodeA);
	}

	public Node getStartNode() {
		return this.getNodeByType(Node.NodeTypeE.START);
	}

	public Node getDestinationNode() {
		return this.getNodeByType(Node.NodeTypeE.DESTINATION);
	}

	private Node getNodeByType(final Node.NodeTypeE type) {
		return this.nodes.stream().filter(node -> node.getType().equals(type)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Input has no " + type.name()));
	}

	public void reset() {
		this.nodes.forEach(Node::reset);
	}
}
