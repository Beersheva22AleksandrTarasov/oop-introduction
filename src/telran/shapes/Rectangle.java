package telran.shapes;

public class Rectangle extends Shape {

	public Rectangle(int width, int height) {
		super(width, height);
	}

	@Override
	public String[] presentation(int offset) {

		String result[] = new String[getHeight()];
		String line = getLine(offset);
		result[0] = line;
		int lastLine = getHeight() - 1;
		result[lastLine] = getOffset(offset) + line;
		for (int i = 1; i < lastLine; i++) {
			result[i] = this.getMiddleLine(offset);
		}
		return result;
	}

	private String getMiddleLine(int offset) {

		return symbol + getOffset(getWidth() - 2) + symbol;
	}

	protected String getLine(int offset) {

		return symbol.repeat(getWidth());
	}
	
	protected String getOffset(int offset) {
		return " ".repeat(offset);
	}

}
