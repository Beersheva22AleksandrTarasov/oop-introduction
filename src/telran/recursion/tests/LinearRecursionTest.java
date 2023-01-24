package telran.recursion.tests;

import static org.junit.jupiter.api.Assertions.*;
import static telran.recursion.LinearRecursion.*;

import org.junit.jupiter.api.Test;

public class LinearRecursionTest {
	@Test
	void fTest() {
		f(6);
	}

	void f(int a) {
		if (a > 5) {
			f(a - 1);
		}
	}

	@Test
	void factorialTest() {
		assertEquals(24, factorial(4));
		assertEquals(24 * 5 * 6, factorial(6));
		assertEquals(24, factorial(-4));
	}

	@Test
	void powerClassWorkTest() {
		assertEquals(1, powerClassWork(1000, 0));
		assertThrowsExactly(IllegalArgumentException.class, () -> powerClassWork(1000, -1));
		assertEquals(1000, powerClassWork(10, 3));
		assertEquals(-1000, powerClassWork(-10, 3));
	}

	@Test
	void sumTest() {
		int ar[] = { 1, 2, 3, 4, 5, 6 };
		assertEquals(21, sum(ar));
		assertEquals(0, sum(new int[0]));
	}

	@Test
	void reverseTest() {
		int ar[] = { 1, 2, 3, 4, 5, 6 };
		int expected[] = { 6, 5, 4, 3, 2, 1 };
		int ar1[] = { 1, 2, 3, 4, 5, 6, 7 };
		int expected1[] = { 7, 6, 5, 4, 3, 2, 1 };
		reverse(ar);
		reverse(ar1);
		assertArrayEquals(expected, ar);
		assertArrayEquals(expected1, ar1);

	}

	@Test
	void squareTest() {
		assertEquals(1, square(1));
		assertEquals(100, square(10));
		assertEquals(100, square(-10));
	}

	@Test
	void powerTest() {

		assertEquals(1, power(1000, 0));
		assertThrowsExactly(IllegalArgumentException.class, () -> power(1000, -1));
		assertEquals(1000, power(10, 3));
		assertEquals(-1000, power(-10, 3));
	}

	@Test
	void isSubstringTest() {
		assertTrue(isSubstring("Hello", "lo"));
		assertTrue(isSubstring("Hello", "Hello"));
		assertFalse(isSubstring("Hello", "rec"));
		assertFalse(isSubstring("Hello", "elo"));
	}
}
