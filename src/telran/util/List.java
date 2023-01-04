package telran.util;

public interface List<T> extends Collection<T> {
	
	void add(int index, T element);

	T remove(int index);

	int indexOf(T pattern);

	int lastIndexOf(T pattern);

	T get(int index);

	void set(int index, T element);

	default void checkIndex(int index, int min, int max) {
		if (index < min || index > max) {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	default boolean contains(T pattern) {

		return indexOf(pattern) > -1;
	}

}
