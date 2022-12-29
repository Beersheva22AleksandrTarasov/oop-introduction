package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Predicate;

import org.junit.jupiter.api.Disabled;

import telran.util.*;

import org.junit.jupiter.api.Test;

class ArrayListTest {

	@Test
//	@Disabled
	void arrayListStringsTest() {
		ArrayList<String> list = new ArrayList<String>(8);
		String[] str = { "Alex",  "Max", "Eva", "Alex", "Stas", "Lev" };

		for (String s : str) {
			list.add(s);
		}

		list.add(2, "Dima");
		
		Predicate<String> pred = s -> s.contains("M");
	    list.remove(2);
		list.remove("Lev");
//		System.out.println(list.removeIf(pred));

		assertFalse(list.isEmpty());
		assertTrue(list.contains("Alex"));
		assertFalse(list.contains("Viktor"));
		assertEquals(0, list.indexOf("Alex"));
		assertEquals(-1, list.indexOf("Lena"));
		assertEquals(3, list.lastIndexOf("Alex"));
		assertEquals(-1, list.lastIndexOf("Lena"));
	}

	@Test
//	@Disabled
	void arrayIntegerListTest() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Integer[] numbers = { 1, 2, -29, 235, 7, 76, -12, 7, 25 };
		Integer[] expected = { 1, 2, -29, 235, 7, 76, -12, 7, 25  };

		for (Integer s : numbers) {
			list.add(s);
		}

		list.add(2, 77);
		list.remove(2);
//		list.removeIf(t -> t < 0);
		assertFalse(list.isEmpty());
		assertTrue(list.contains(1));
		assertFalse(list.contains(0));
		assertEquals(5, list.indexOf(76));
		assertEquals(-1, list.indexOf(-28));
		assertEquals(7, list.lastIndexOf(7));
		assertEquals(-1, list.lastIndexOf(0));

		assertArrayEquals(expected, numbers);	
	}

}
