package day9;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Node<T> {
	private T value;
	private Node<T> next;
	private Node<T> previous;

	public Node(final Node<T> previous, final T value, final Node<T> next) {
		this.value = value;
		this.next = next;
		this.previous = previous;
	}
}
