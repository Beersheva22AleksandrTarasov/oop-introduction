package telran.recursion;

public class LinearRecursion {

	public static long factorial(int n) {
		long result = 0;

		if (n < 0) {
			result = factorial(-n);

		} else if (n < 2) {
			result = 1;
		} else {
			result = n * factorial(n - 1);
		}
		return result;
	}

	static public int powerClassWork(int a, int b) {
		if (b < 0) {
			throw new IllegalArgumentException();
		}
		int res = 1;
		if (b == 1) {
			res = a;
		} else if (b > 1) {
			res = a * powerClassWork(a, b - 1);
		}
		return res;
	}

	static public int power(int a, int b) {
		if (b < 0) {
			throw new IllegalArgumentException();
		}
		int result = 1;

		if (b > 0) {
			result = powerStep(a, power(a, b - 1));
		}
		return result;
	}

	private static int powerStep(int a, int step) {
		int result = 0;
		if(step < 0) {
			result = powerStep(-a, -step);
		}
		if (step > 0) {
			result = a + powerStep(a, step - 1);
		}
		return result;
	}

	static public long sum(int ar[]) {

		return sum(0, ar);
	}

	private static long sum(int firstIndex, int[] ar) {
		long res = 0;
		if (firstIndex < ar.length) {
			res = ar[firstIndex] + sum(firstIndex + 1, ar);
		}
		return res;
	}

	public static long square(int x) {
		long result = 0;
		if (x < 0) {
			result = square(-x);
		} else if (x > 0) {
			result = x + x - 1 + square(x - 1);
		}
		return result;

	}

	public static int[] reverse(int ar[]) {

		return reverse(0, ar.length - 1, ar);

	}

	private static int[] reverse(int firstIndex, int lastIndex, int[] ar) {

		if (firstIndex < lastIndex) {
			swap(ar, firstIndex, lastIndex);
			reverse(firstIndex + 1, lastIndex - 1, ar);
		}

		return ar;
	}

	private static void swap(int[] ar, int firstIndex, int lastIndex) {
		int tmp = ar[firstIndex];
		ar[firstIndex] = ar[lastIndex];
		ar[lastIndex] = tmp;

	}

	public static boolean isSubstring(String string, String substr) {
		boolean result = false;
		if (substr.length() <= string.length()) {
			result = isEqual(string, substr) ? true : isSubstring(string.substring(1), substr);
		}
		return result;
	}

	private static boolean isEqual(String string, String substr) {
		boolean result = false;
		if (substr.length() == 0) {
			return true;
		} else if (string.charAt(0) == substr.charAt(0)) {
			return isEqual(string.substring(1), substr.substring(1));

		}
		return result;
	}
}
