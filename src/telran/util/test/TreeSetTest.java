package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.TreeSet;

public class TreeSetTest extends SortedTest {
	TreeSet<Integer> tree;

	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new TreeSet<Integer>();
		super.setUp();
		tree = (TreeSet<Integer>) collection;
	}

	@Test
	void displayRotatatedTest() {
		tree.displayTreeRotated();
	}

	@Test
	void heightTreeTest() {
		assertEquals(4, tree.height());
	}

	@Test
	void widthTreeTest() {
		assertEquals(4, tree.width());
	}

	@Test
	void inversionTest() {
		// {10, 100, -5, 134, 280, 120, 15};
		tree.inversion();
		Integer expected[] = { 280, 134, 120, 100, 15, 10, -5 };
		Integer actual[] = new Integer[expected.length];
		int index = 0;
		for (Integer num : tree) {
			actual[index++] = num;
		}
		assertArrayEquals(expected, actual);
		assertTrue(tree.contains(280));
	}
}
