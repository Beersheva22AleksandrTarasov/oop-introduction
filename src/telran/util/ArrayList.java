package telran.util;

import java.util.Arrays;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size;

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
		checkIndex(index);
		if (size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size() - index);
		array[index] = element;
		size++;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	@Override
	public boolean remove(T pattern) {
		boolean result = false;
		int i = 0;

		while (i < size() && !result) {
			if (pattern.equals(get(i))) {
				removeElement(i);
				result = true;
			}
			i++;
		}
		return result;
	}

	@Override
	public T remove(int index) {
		checkIndex(index);
		T result = get(index);
		removeElement(index);
		return result;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		boolean res = false;
		for (int i = 0; i < size(); i++) {
			if (predicate.test(get(i))) {
				removeElement(i--);
				res = true;
			}
		}
		return res;
	}

	private void removeElement(int index) {
		if (index == size() - 1) {
			set(index, null);
		} else {
			System.arraycopy(array, index + 1, array, index, size() - index - 1);
		}
		size--;
	}

	@Override
	public boolean isEmpty() {

		return size() == 0 ? true : false;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public boolean contains(T pattern) {
		int i = 0;

		while (i < size() && !pattern.equals(get(i))) {
			i++;
		}

		return i < size();
	}

	@Override
	public T[] toArray(T[] ar) {
		if (ar.length >= size()) {
			System.arraycopy(array, 0, ar, 0, size());
		} else {
			ar = Arrays.copyOf(ar, size());
			System.arraycopy(array, 0, ar, 0, size());
		}

		return ar;
	}

	@Override
	public int indexOf(T pattern) {
		int res = -1;
		int i = 0;

		while (i < size() && res < 0) {
			if (pattern.equals(get(i))) {
				res = i;
			}
			i++;
		}
		return res;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int i = size() - 1;
		while (i >= 0 && !pattern.equals(get(i))) {
			i--;
		}
		return i;
	}

	@Override
	public T get(int index) {
		checkIndex(index);
		return array[index];
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index);
		array[index] = element;
	}

	private void checkIndex(int index) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
	}
}