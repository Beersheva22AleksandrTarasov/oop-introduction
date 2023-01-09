package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

class LinkedListTest extends ListTest {
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new LinkedList<>();
		super.setUp();
	}

	@Test
	void hasLoopTest() {
		LinkedList<Integer> listTest = new LinkedList<>();
		Integer[] numbers = { 10, 100, -5, 134, 280, 120, 15 };
		for (Integer num : numbers) {
			listTest.add(num);
		}
		assertFalse(listTest.hasLoop());
		listTest.setNext(5, 3);
		assertTrue(listTest.hasLoop());
	}

}