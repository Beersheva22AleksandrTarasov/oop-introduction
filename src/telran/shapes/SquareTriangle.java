package telran.shapes;

public class SquareTriangle extends Square {

	private boolean isLeftDiagonal;

	public SquareTriangle(int width, boolean isLeftDiagonal) {
		super(width);
		this.isLeftDiagonal = isLeftDiagonal;
	}

	public String[] presentation(int offset) {

		String res[] = new String[getWidth()];
		String line = getLine(offset);
		if (isLeftDiagonal) {
			res[0] = getOffset(offset) + symbol;
		} else {
			res[0] = getOffset(offset + getWidth() - 1) + symbol;
		}

		int lastLine = getHeight() - 1;
		res[lastLine] = line;

		for (int i = 1; i < lastLine; i++) {
			res[i] = getMiddleLine(offset, i);
		}
		return res;
	}

	private String getMiddleLine(int offset, int i) {

		String leftTriangle = getOffset(offset) + symbol + getOffset(i - 1) + symbol;
		String rightTriangle = getOffset(offset) + getOffset(getWidth() - (i + 1)) + symbol + getOffset(i - 1) + symbol;
		return isLeftDiagonal ? leftTriangle : rightTriangle;

	}

}
