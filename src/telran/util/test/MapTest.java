package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.Map;

abstract class MapTest {
	Map<Integer, String> map;

	@BeforeEach
	void setUp() throws Exception {
		map.put(1, "One");
		map.put(2, "Two");
		map.put(3, "Three");
	}

	@Test
	void getTest() {
		assertEquals("One", map.get(1));
		assertNull(map.get(1000));
	}

	@Test
	void getOrDefaultTest() {
		assertEquals("One", map.getOrDefault(1, "One"));
		assertEquals("Thousand", map.getOrDefault(1000, "Thousand"));
	}

	@Test
	void putTest() {
		assertEquals("One", map.put(1, "First"));
		assertEquals("First", map.get(1));
		assertNull(map.put(4, "Four"));
		assertEquals("Four", map.get(4));
	}

	@Test
	void putIfAbsentTest() {
		assertEquals("Zero", map.putIfAbsent(1, "Zero"));
		assertNull(map.putIfAbsent(5, "Five"));
		assertEquals("Five", map.get(5));
	}

	@Test
	void containsKeyTest() {
		assertTrue(map.containsKey(1));
		assertFalse(map.containsKey(1000));
	}

	@Test
	void containsValueTest() {
		assertTrue(map.containsValue("Three"));
		assertFalse(map.containsValue("Thousand"));
	}

	@Test
	void valuesTest() {
		Collection<String> expected = new LinkedList<>();
		expected.add("One");
		expected.add("Two");
		expected.add("Three");
		assertEquals(expected, map.values());

	}

	@Test
	void removeTest() {
		assertEquals("Two", map.remove(2));
		assertNull(map.remove(2));
		assertNull(map.get(2));
	}

}
