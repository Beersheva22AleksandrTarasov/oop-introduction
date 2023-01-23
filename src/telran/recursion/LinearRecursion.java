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
		int res = 0;
		if (step > 0) {
			res = a + powerStep(a, step - 1);
		}
		return res;
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
		if (x <= 0) {
			throw new IllegalArgumentException();
		}
		long result = 0;
		if (x == 1) {
			result = x;
		} else if (x > 1) {
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
		if (substr.length() == 0 || substr.length() > string.length()) {
			throw new IllegalArgumentException();
		}

		return isSubstring(string, substr, string.length(), substr.length());
	}

	private static boolean isSubstring(String string, String substr, int stringLength, int substrLength) {

		if (substrLength == 0) {
			return true;
		}
		if (stringLength > 0) {
			if (string.charAt(0) == substr.charAt(0)) {
				return isSubstring(string.substring(1, stringLength), substr.substring(1, substrLength),
						stringLength - 1, substrLength - 1);
			}

			return isSubstring(string.substring(1, stringLength), substr, stringLength - 1, substrLength);

		}
		return false;
	}
}
