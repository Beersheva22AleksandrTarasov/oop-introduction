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
		Node<T> prev = null;
		boolean flagNext = false;

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
			prev = currentNode;
			currentNode = getNextCurrentNode(currentNode);
			flagNext = true;
			return result;

		}

		@Override
		public void remove() {
			if (!flagNext) {
				throw new IllegalStateException();
			}

			flagNext = false;
			if (isJunction(prev)) {
				currentNode = prev;
			}
			removeNode(prev);
		}

	}

	private static final String SYMBOL = " ";
	private static final int NUMBER_SYMBOLS_PER_LEVEL = 3;
	private Node<T> root;
	private Comparator<T> comp;

	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}

	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
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

	public Node<T> getGreatestNode(Node<T> currentNode) {
		while (currentNode.right != null) {
			currentNode = currentNode.right;
		}
		return currentNode;
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
		Node<T> current = getNode(pattern);
		boolean result = false;
		if (current != null && comp.compare(current.obj, pattern) == 0) {
			result = true;
			removeNode(current);
		}
		return result;
	}

	private void removeNode(Node<T> current) {
		if (isJunction(current)) {
			removeNodeJunction(current);
		} else {
			removeNodeNonJunction(current);
		}
		size--;

	}

	private boolean isJunction(Node<T> current) {
		return current.left != null && current.right != null;
	}

	private void removeNodeJunction(Node<T> current) {
		Node<T> substitution = getLeastNode(current.right);
		current.obj = substitution.obj;
		removeNodeNonJunction(substitution);

	}

	private void removeNodeNonJunction(Node<T> current) {
		Node<T> parent = current.parent;
		Node<T> child = current.left == null ? current.right : current.left;
		if (parent == null) {
			root = child;
		} else {
			if (parent.left == current) {
				parent.left = child;
			} else {
				parent.right = child;
			}
		}
		if (child != null) {
			child.parent = parent;
		}

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
		return floorCeiling(element, true);
	}

	@Override
	public T ceiling(T element) {
		return floorCeiling(element, false);
	}

	private T floorCeiling(T element, boolean isFloor) {
		T res = null;
		int compRes = 0;
		Node<T> current = root;
		while (current != null && (compRes = comp.compare(element, current.obj)) != 0) {
			if ((compRes < 0 && !isFloor) || (compRes > 0 && isFloor)) {
				res = current.obj;
			}
			current = compRes < 0 ? current.left : current.right;
		}
		return current == null ? res : current.obj;
	}

	@Override
	public T first() {
		return root != null ? getLeastNode(root).obj : null;
	}

	@Override
	public T last() {
		return root != null ? getGreatestNode(root).obj : null;
	}

	public void displayTreeRotated() {
		displayTreeRotated(root, 0);
	}

	private void displayTreeRotated(Node<T> node, int level) {
		if (node != null) {
			displayTreeRotated(node.right, level + 1);
			displayRoot(node, level);
			displayTreeRotated(node.left, level + 1);
		}

	}

	private void displayRoot(Node<T> node, int level) {
		System.out.printf("%s%s\n", SYMBOL.repeat(NUMBER_SYMBOLS_PER_LEVEL * level), node.obj);

	}

	public int height() {

		return height(root);
	}

	private int height(Node<T> node) {
		int result = 0;
		if (node != null) {
			int heightLeft = height(node.left);
			int heightRight = height(node.right);
			result = Math.max(heightLeft, heightRight) + 1;
		}
		return result;

	}

	public int width() {

		return width(root);
	}

	private int width(Node<T> node) {
		int result = 0;
		if (node != null) {
			if (node.left == null && node.right == null) {
				result = 1;
			} else {
				result = width(node.left) + width(node.right);
			}

		}
		return result;
	}

	public void inversion() {
		comp = comp.reversed();
		inversion(root);
	}

	private void inversion(Node<T> node) {
		if (node != null) {
			inversion(node.left);
			inversion(node.right);
			swap(node);
		}

	}

	private void swap(Node<T> node) {
		Node<T> temp = node.left;
		node.left = node.right;
		node.right = temp;
	}
}
