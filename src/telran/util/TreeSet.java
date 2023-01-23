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
			TreeSet.this.remove(prev.obj);

			flagNext = false;
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
			if (current.left != null && current.right != null) {
				Node<T> node = getLeastNode(current.right);
				current.obj = node.obj;
				removeCurrentNode(node);
			} else {
				removeCurrentNode(current);
			}
			result = true;
		}
		return result;
	}

	private void removeCurrentNode(Node<T> current) {
		Node<T> currentChild = current.left != null ? current.left : current.right;
		if (currentChild != null) {
			currentChild.parent = current.parent;
		}
		if (current.parent == null) {
			removeRoot(currentChild);
		} else {
			if (comp.compare(current.obj, current.parent.obj) < 0) {
				current.parent.left = currentChild;
			} else {
				current.parent.right = currentChild;
			}
		}
		current.parent = null;
		current.left = null;
		current.right = null;
		size--;
	}

	private void removeRoot(Node<T> child) {
		if (size == 1) {
			root = null;
		} else {
			root = child;
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
		Iterator<T> it = iterator();
		boolean flag = false;
		T current = null;
		T prev = null;

		while (!flag && it.hasNext()) {
			current = it.next();
			if (comp.compare(current, element) > 0) {
				flag = true;
			}
			if (!flag)
				prev = current;
		}

		return prev;
	}

	@Override
	public T ceiling(T element) {
		Iterator<T> it = iterator();
		T result = null;
		T current = null;
		while (result == null && it.hasNext()) {
			current = it.next();
			if (comp.compare(current, element) >= 0) {
				result = current;
			}
		}

		return result;
	}

	@Override
	public T first() {
		return root != null ? getLeastNode(root).obj : null;
	}

	@Override
	public T last() {
		return root != null ? getGreatestNode(root).obj : null;
	}
}
