package telran.recursion;

public class MdArray<T> {
	private MdArray<T>[] array;
	private T value;

	public MdArray(int dimensions[], T value) {
		this(dimensions, 0, value);
	}

	@SuppressWarnings("unchecked")
	public MdArray(int[] dimensions, int firstDim, T value) {
		if (firstDim == dimensions.length) {
			this.value = value;
			array = null;
		} else {
			this.value = null;
			array = new MdArray[dimensions[firstDim]];
			for (int i = 0; i < array.length; i++) {
				array[i] = new MdArray<>(dimensions, firstDim + 1, value);
			}
		}
	}
}
