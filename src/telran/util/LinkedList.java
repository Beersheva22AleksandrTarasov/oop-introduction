package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
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
	private int size;

	private class LinkedListIterator implements Iterator<T> {
		public Node<T> current = head;

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
			return res;
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

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		Node<T> current = head;
		int oldSize = size;
		for (int i = 0; i < oldSize; i++) {
			if (predicate.test(current.obj)) {
				remove(current.obj);
			}
			current = current.next;
		}
		return oldSize > size;
	}

	@Override
	public boolean remove(T pattern) {
		int index = indexOf(pattern);
		boolean result = index > -1;
		if (result) {
			remove(index);
		}
		return result;

	}

	@Override
	public T remove(int index) {
		checkIndex(index, 0, size);
		Node<T> current = getNode(index);

		if (current.equals(head)) {
			removeFromHead(current);
		} else if (current.equals(tail)) {
			removeFromTail(current);
		} else {
			removeFromMiddle(current);
		}

		return current.obj;
	}

	private void removeFromMiddle(Node<T> current) {
		(current.prev).next = current.next;
		(current.next).prev = current.prev;
		size--;
	}

	private void removeFromHead(Node<T> current) {
		if (head == tail) {
			head = null;
		} else {
			head = current.next;
			head.prev = null;
		}
		size--;
	}

	private void removeFromTail(Node<T> current) {
		if (tail == head) {
			tail = null;
		} else {
			tail = current.prev;
			tail.next = null;
		}
		size--;
	}

	@Override
	public boolean isEmpty() {

		return size == 0;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public T[] toArray(T[] ar) {
		if (ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		}
		Node<T> current = head;
		for (int i = 0; i < size; i++) {
			ar[i] = current.obj;
			current = current.next;
		}
		Arrays.fill(ar, size, ar.length, null);
		return ar;
	}

	@Override
	public int indexOf(T pattern) {
		Node<T> current = head;
		int result = -1;
		int index = 0;
		if (pattern != null) {
			while (index < size && result < 0) {
				if (pattern.equals(current.obj)) {
					result = index;
				}
				current = current.next;
				index++;
			}
		}
		return result;
	}

	@Override
	public int lastIndexOf(T pattern) {
		Node<T> current = tail;
		int result = -1;
		int index = size - 1;
		if (pattern != null) {
			while (index > 0 && result < 0) {
				if (pattern.equals(current.obj)) {
					result = index;
				}
				current = current.prev;
				index--;
			}
		}
		return result;
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

}