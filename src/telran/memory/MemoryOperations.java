package telran.memory;

public class MemoryOperations {
	public static int getMaxAvaibleMemory() {
		int res = Integer.MAX_VALUE;
		boolean running = true;
		byte ar[] = null;
		int help = 0;
		while (running) {
			try {
				ar = new byte[res];
				running = false;

			} catch (Throwable e) {
				help = maxValueSearch(res);
				res = help;
			}
		}
		return res;
	}

	public static int maxValueSearch(int number) {
		int left = 0;
		int right = 0;
		int res = 0;
		int middle = (left + number) / 2;
		if (left < middle) {

			res = middle;
		} else if (res < middle) {
			right = middle - 1;
			res = right;
		} else {
			left = middle + 1;
			res = left;
		}

		return res;
	}

}
