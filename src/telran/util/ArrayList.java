package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> extends AbstractCollection<T> implements List<T> {
	static final int DEFAULT_CAPACITY = 16;
	private T[] array;

	private class ArrayListIterator implements Iterator<T> {
		int index = 0;
		boolean flagNext = false;

		@Override
		public boolean hasNext() {

			return index < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			flagNext = true;
			return array[index++];
		}

		@Override
		public void remove() {
			if (!flagNext) {
				throw new IllegalStateException();
			}
			T removed = array[index - 1];
			ArrayList.this.remove(removed);

			flagNext = false;
		}

	}

	@Override
	public Iterator<T> iterator() {

		return new ArrayListIterator();
	}

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public boolean add(T element) {
		if (size == array.length) {
			reallocate();
		}
		array[size++] = element;
		return true;
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, 0, size);
		if (size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = element;
		size++;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	@Override
	public T remove(int index) {
		checkIndex(index, 0, size);
		T result = get(index);
		removeElement(index);
		return result;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int oldSize = size;
		int helpIndex = 0;
		for (int index = 0; index < oldSize; index++) {
			if (predicate.test(array[index])) {
				size--;
			} else {
				array[helpIndex++] = array[index];
			}
		}
		Arrays.fill(array, size, oldSize, null);
		return oldSize > size;
	}

	private void removeElement(int index) {
		if (index == size - 1) {
			set(index, null);
		} else {
			System.arraycopy(array, index + 1, array, index, size - index);
		}
		size--;
	}

	@Override
	public int indexOf(T pattern) {
		int index = 0;
		while (index < size && !isEqual(get(index), pattern)) {
			index++;
		}

		return index < size ? index : -1;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int index = size - 1;
		while (index >= 0 && !isEqual(get(index), pattern)) {
			index--;
		}
		return index;
	}

	@Override
	public T get(int index) {
		checkIndex(index, 0, size);
		return array[index];
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index, 0, size);
		array[index] = element;
	}

}