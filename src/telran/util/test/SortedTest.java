package telran.util.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import org.junit.jupiter.api.*;

import telran.util.Sorted;

public abstract class SortedTest extends SetTest {
	protected static final int N_ELEMNTS = 100000;
	protected static final int N_RUNS = 10000;
	private Random gen = new Random();
	Sorted<Integer> sorted;

	@Override
	@BeforeEach
	void setUp() throws Exception {
		super.setUp();
		sorted = (Sorted<Integer>) collection;
	}

	@Override
	@Test
	void testIterator() {
		Integer expected[] = Arrays.copyOf(numbers, numbers.length);
		Arrays.sort(expected);
		Integer actual[] = new Integer[expected.length];
		int index = 0;
		for (Integer num : sorted) {
			actual[index++] = num;
		}
		assertArrayEquals(expected, actual);

	}

	// {-5, 10, 15, 100, 120, 134, 280 };
	@Test
	void floorTest() {
		assertEquals((Integer) 100, sorted.floor(100));
		assertNull(sorted.floor(-10));
		assertEquals((Integer) 15, sorted.floor(20));
		assertEquals((Integer) 280, sorted.floor(300));
		assertEquals((Integer) 100, sorted.floor(115));
	}

	@Test
	void ceilingTest() {
		assertEquals((Integer) 100, sorted.ceiling(100));
		assertNull(sorted.ceiling(281));
		assertEquals((Integer) 15, sorted.ceiling(13));
		assertEquals((Integer) 10, sorted.ceiling(0));
		assertEquals((Integer) (-5), sorted.ceiling(-10));
		assertEquals((Integer) 280, sorted.ceiling(150));
	}

	@Test
	void firstTest() {
		assertEquals((Integer) (-5), sorted.first());
	}

	@Test
	void lastTest() {
		assertEquals((Integer) 280, sorted.last());
	}

	@Test
	@Disabled
	void performanceTestSortedAdding() {
		Sorted<Integer> sorted = getSortedCollection();
		IntStream.range(0, N_ELEMNTS).forEach(i -> sorted.add(i));
		runPerformanceTest(sorted);
	}

	protected void runPerformanceTest(Sorted<Integer> sorted) {

		for (int i = 0; i < N_RUNS; i++) {
			sorted.floor(gen.nextInt());
		}
	}

	protected abstract Sorted<Integer> getSortedCollection();

	@Test
	void performanceTestRandomAdding() {
		Sorted<Integer> sorted = getSortedCollection();
		IntStream.range(0, N_ELEMNTS).forEach(i -> sorted.add(gen.nextInt()));
		runPerformanceTest(sorted);
	}
}
