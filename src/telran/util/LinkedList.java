package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> extends AbstractCollection<T> implements List<T> {
	private static class Node<T> {
		T obj;
		Node<T> prev;
		Node<T> next;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private Node<T> head;
	private Node<T> tail;

	private class LinkedListIterator implements Iterator<T> {
		Node<T> current = head;
		boolean flagNext = false;

		@Override
		public boolean hasNext() {

			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = current.obj;
			current = current.next;
			flagNext = true;
			return res;
		}

		@Override
		public void remove() {
			if (!flagNext) {
				throw new IllegalStateException();
			}
			Node<T> removedNode = current == null ? tail : current.prev;
			removeNode(removedNode);
			flagNext = false;
		}

	}

	@Override
	public Iterator<T> iterator() {

		return new LinkedListIterator();
	}

	@Override
	public boolean add(T element) {
		Node<T> node = new Node<>(element);
		if (head == null) {
			head = tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}

		size++;
		return true;
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, 0, size + 1);
		if (index == size) {
			add(element);
		} else if (index == 0) {
			addToHead(element);
		} else {
			addToMiddle(index, element);
		}

	}

	private void addToHead(T element) {
		Node<T> node = new Node<>(element);
		node.next = head;
		head.prev = node;
		head = node;
		size++;

	}

	private void addToMiddle(int index, T element) {
		Node<T> node = new Node<>(element);
		Node<T> nodeIndex = getNode(index);
		Node<T> nodePrev = nodeIndex.prev;
		nodePrev.next = node;
		node.prev = nodePrev;
		nodeIndex.prev = node;
		node.next = nodeIndex;
		size++;

	}

	private Node<T> getNode(int index) {

		return index < size >> 1 ? getNodeFromLeft(index) : getNodeFromRight(index);
	}

	private Node<T> getNodeFromRight(int index) {
		Node<T> current = tail;
		for (int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getNodeFromLeft(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	private void removeNode(Node<T> current) {
		if (current == head) {
			removeFromHead();
		} else if (current == tail) {
			removeFromTail();
		} else {
			removeFromMiddle(current);
		}

		size--;

	}

	@Override
	public T remove(int index) {
		checkIndex(index, 0, size);
		Node<T> current = getNode(index);
		if (current == null) {
			throw new IllegalStateException("current in method remove is null");
		}
		removeNode(current);
		return current.obj;
	}

	private void removeFromMiddle(Node<T> current) {
		Node<T> prev = current.prev;
		Node<T> next = current.next;
		prev.next = next;
		next.prev = prev;

	}

	private void removeFromHead() {
		if (head.next == null) {
			head = tail = null;
		} else {
			Node<T> next = head.next;
			next.prev = null;
			head = next;
		}
	}

	private void removeFromTail() {
		Node<T> prev = tail.prev;
		prev.next = null;
		tail = prev;

	}

	@Override
	public int indexOf(T pattern) {
		Node<T> current = head;
		int index = 0;
		while (current != null && !current.obj.equals(pattern)) {
			current = current.next;
			index++;
		}
		return current != null ? index : -1;
	}

	@Override
	public int lastIndexOf(T pattern) {
		Node<T> current = tail;
		int index = size - 1;
		while (current != null && !current.obj.equals(pattern)) {
			current = current.prev;
			index--;
		}
		return index;
	}

	@Override
	public T get(int index) {
		checkIndex(index, 0, size);
		return getNode(index).obj;
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index, 0, size);
		Node<T> node = getNode(index);
		node.obj = element;

	}

	public void setNext(int index1, int index2) {

		if (index1 < index2) {
			throw new IllegalArgumentException();
		}
		checkIndex(index1, 0, size() - 1);
		checkIndex(index2, 0, size() - 1);
		getNode(index1).next = getNode(index2);
	}

	public boolean hasLoop() {
		Node<T> stepOne = head;
		Node<T> stepTwo = head;
		boolean result = false;

		while (stepTwo != null && stepTwo.next != null && !result) {
			stepOne = stepOne.next;
			stepTwo = stepTwo.next.next;
			result = stepOne == stepTwo;
		}
		return result;
	}

}