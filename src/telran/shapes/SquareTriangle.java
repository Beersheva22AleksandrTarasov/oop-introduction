package telran.shapes;

public class SquareTriangle extends Square {

	private boolean isLeftDiagonal;

	protected SquareTriangle(int width, boolean isLeftDiagonal) {
		super(width);
		this.isLeftDiagonal = isLeftDiagonal;
	}

	@Override
	public String[] presentation(int offset) {

		String res[] = new String[getWidth()];
		String line = getLine(offset);
		res[0] = isLeftDiagonal ? symbol + getOffset(getWidth() - 1) : getOffset(getWidth() - 1) + symbol;

		int lastLine = getHeight() - 1;
		res[lastLine] = line;
		
		for (int i = 1; i < lastLine; i++) {
			res[i] = isLeftDiagonal ? getMiddleLineLeft(offset, i) : getMiddleLineRight(offset, i);
		}

		
		return res;
	}

	private String getMiddleLineLeft(int offset, int i) {
		return getOffset(offset) + symbol + getOffset(i - 1) + symbol + getOffset(getWidth() - i - 1);
	}

	private String getMiddleLineRight(int offset, int i) {
		return getOffset(offset) + getOffset(getWidth() - (i + 1)) + symbol + getOffset(i - 1) + symbol;
	}

}
