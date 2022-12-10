package day9;

import java.util.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class LinkedList<T> implements Iterable<Node<T>> {
	private Node<T> head;
	private Node<T> tail;

	public LinkedList(final Collection<T> data) {
		addAll(data);
	}

	public void addAll(final Collection<T> data) {
		Node<T> predecessor;
		predecessor = this.tail;

		for (final T currentValue : data) {
			final Node<T> newNode = new Node<>(predecessor, currentValue, null);

			if (predecessor == null) {
				this.head = newNode;
			} else {
				predecessor.setNext(newNode);
			}
			predecessor = newNode;
		}

		this.tail = predecessor;
	}

	@Override
	public Iterator<Node<T>> iterator() {
		return new Iterator<>() {
			Node<T> current = head;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Node<T> next() {
				if (!hasNext()) {
					throw new NoSuchElementException("Linked list has no next element!");
				}

				Node<T> node = current;
				current = current.getNext();
				return node;
			}

		};
	}
}
