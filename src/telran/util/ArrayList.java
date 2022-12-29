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
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = element;
		size++;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	@Override
	public boolean remove(T pattern) {
		boolean result = false;
		int index = 0;

		while (index < size && !result) {
			if (pattern.equals(get(index))) {
				removeElement(index);
				result = true;
			}
			index++;
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
		boolean result = false;
		for (int index = 0; index < size; index++) {
			if (predicate.test(get(index))) {
				removeElement(index--);
				result = true;
			}
		}
		return result;
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
	public boolean isEmpty() {

		return size == 0;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public boolean contains(T pattern) {

		return indexOf(pattern) >= 0;
	}

	@Override
	public T[] toArray(T[] ar) {
		if (ar.length < size) {
			ar = Arrays.copyOf(array, size);
		}
		System.arraycopy(array, 0, ar, 0, size);
		Arrays.fill(ar, size, ar.length, null);
		return ar;
	}

	@Override
	public int indexOf(T pattern) {
		int index = 0;
		while (index < size && !pattern.equals(get(index))) {
			index++;
		}

		return index < size ? index : -1;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int index = size - 1;
		while (index >= 0 && !pattern.equals(get(index))) {
			index--;
		}
		return index;
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
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
	}
}