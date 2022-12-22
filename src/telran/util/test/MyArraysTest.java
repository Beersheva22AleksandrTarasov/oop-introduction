package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.MyArrays;

class MyArraysTest {

	@Test
	@Disabled
	void sortTest() {
		String[] strings = {"abcd", "lmn", "zz"};
		String[] expected = {"zz", "lmn", "abcd"};
		Integer [] ar = {3, 2, 1};
		
		MyArrays.sort(strings, new StringLengthComparator());
		assertArrayEquals(expected, strings);
		MyArrays.sort(ar, (a, b) -> a - b);
		
	}
	
	@Test 
	void evenOddTest() {
	Integer numbers[] = {13, 2, -8, 47, 100, 10, 7};
	Integer expected[] = {-8, 2, 10, 100, 47, 13, 7};
	MyArrays.sort(numbers, new EvenOddComparator());
	assertArrayEquals(expected, numbers);
	}


}
