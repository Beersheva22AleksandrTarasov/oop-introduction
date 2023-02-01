package telran.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface Collection<T> extends Iterable<T> {

	boolean add(T element);

	boolean remove(T pattern);

	default boolean removeIf(Predicate<T> predicate) {
		Iterator<T> it = iterator();
		int oldSize = size();
		while (it.hasNext()) {
			T obj = it.next();
			if (predicate.test(obj)) {
				it.remove();
			}
		}
		return oldSize > size();
	}

	boolean isEmpty();

	int size();

	boolean contains(T pattern);

	default T[] toArray(T[] ar) {
		if (ar.length < size()) {
			ar = Arrays.copyOf(ar, size());
		}
		Iterator<T> it = iterator();
		int index = 0;
		while (it.hasNext()) {
			ar[index++] = it.next();
		}

		Arrays.fill(ar, size(), ar.length, null);

		return ar;
	}

	default Stream<T> stream() {
		return StreamSupport.stream(this.spliterator(), false);
	}

	default Stream<T> parallelStream() {
		return StreamSupport.stream(this.spliterator(), true);
	}

	default T[] toArrayShuffling(T[] array) {
		T[] arr = toArray(array);
		
		return new Random().ints(0, size()).distinct().limit(size()).mapToObj(s -> arr[s]).toArray(s -> arr);
	}
}