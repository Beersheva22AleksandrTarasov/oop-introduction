package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> extends AbstractCollection<T> implements Sorted<T> {
	static private class Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left;
		Node<T> right;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private class TreeSetIterator implements Iterator<T> {
		Node<T> currentNode = root;

		TreeSetIterator() {
			if (currentNode != null) {
				currentNode = getLeastNode(currentNode);
			}
		}

		@Override
		public boolean hasNext() {

			return currentNode != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			T result = currentNode.obj;
			currentNode = getNextCurrentNode(currentNode);
			boolean flagNext = false;
			return result;

		}

		private Node<T> getNextCurrentNode(Node<T> current) {

			return current.right == null ? getGreaterParent(current) : getLeastNode(current.right);
		}

		private Node<T> getGreaterParent(Node<T> current) {
			while (current.parent != null && current.parent.left != current) {
				current = current.parent;
			}
			return current.parent;
		}

		public Node<T> getLeastNode(Node<T> currentNode) {
			while (currentNode.left != null) {
				currentNode = currentNode.left;
			}
			return currentNode;
		}

	}

	private Node<T> root;
	private Comparator<T> comp;

	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}

	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}

	@Override
	public boolean add(T element) {
		Node<T> parentNode = getNode(element);
		boolean result = false;
		int compResult = 0;

		if (parentNode == null || (compResult = comp.compare(element, parentNode.obj)) != 0) {
			result = true;
			size++;
			Node<T> newNode = new Node<T>(element);
			newNode.parent = parentNode;
			if (parentNode == null) {
				root = newNode;
			} else {
				if (compResult < 0) {
					parentNode.left = newNode;
				} else {
					parentNode.right = newNode;
				}
			}
		}

		return result;
	}

	private Node<T> getNode(T element) {
		Node<T> currentNode = root;
		Node<T> parentNode = null;
		int compResult;
		while (currentNode != null && (compResult = comp.compare(element, currentNode.obj)) != 0) {
			parentNode = currentNode;
			currentNode = compResult < 0 ? currentNode.left : currentNode.right;
		}
		return currentNode == null ? parentNode : currentNode;
	}

	@Override
	public boolean remove(T pattern) {
		// Not implemented yet
		return false;
	}

	@Override
	public boolean contains(T element) {
		Node<T> elementNode = getNode(element);
		return elementNode != null && comp.compare(element, elementNode.obj) == 0;

	}

	@Override
	public Iterator<T> iterator() {

		return new TreeSetIterator();
	}

	@Override
	public T floor(T element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T ceiling(T element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T first() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T last() {
		// TODO Auto-generated method stub
		return null;
	}
}
