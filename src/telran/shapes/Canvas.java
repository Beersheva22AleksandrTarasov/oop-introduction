package telran.shapes;

import java.util.Arrays;

public class Canvas extends Shape {
	private Shape[] shapes;
	private String direction = "row";
	private int margin = 5;

	public Canvas(int width, int height, Shape[] shapes) {
		super(width, height);
		this.shapes = shapes;

	}

	private void changeHeight(int height, Shape[] shapes) {
		for (Shape form : shapes) {
			form.setHeight(height);
		}
	}

	private void changeWidth(int width, Shape[] shapes) {
		for (Shape form : shapes) {
			form.setWidth(width);
		}
	}

	@Override
	public String[] presentation(int offset) {

		String[] resultArray;
		Shape[] copyArrayShapes = shapes.clone();

		if (direction == "row") {

			changeHeight(getHeight(), copyArrayShapes);
			resultArray = new String[getHeight()];
			Arrays.fill(resultArray, getOffset(offset));
			buildShapesRow(resultArray, copyArrayShapes, offset);

		} else {
			
			changeWidth(getWidth(), copyArrayShapes);
			resultArray = new String[getHeightAllShapes(copyArrayShapes) + (copyArrayShapes.length - 1)];
			Arrays.fill(resultArray, getOffset(offset));
			buildShapesColumn(resultArray, copyArrayShapes, offset);

		}

		return resultArray;
	}

	private void buildShapesColumn(String[] resultArray, Shape[] shapes, int offset) {
		int value = 0;
		for (Shape form : shapes) {
			String[] helperForColumn = form.presentation(0);
			int formLength = helperForColumn.length;

			for (int i = 0; i < formLength; i++, value++) {
				resultArray[value] += helperForColumn[i];
			}
			if (form != shapes[shapes.length - 1]) {
				resultArray[value++] = setSpaceOrEnter();
			}
		}

	}

	private void buildShapesRow(String[] result, Shape[] shapes, int offset) {
		for (Shape form : shapes) {
			String[] helperForRow = form.presentation(0);
			for (int i = 0; i < getHeight(); i++) {
				result[i] += (form.equals(shapes[shapes.length - 1])) ? helperForRow[i]
						: helperForRow[i] + setSpaceOrEnter();
			}
		}
	}

	private int getHeightAllShapes(Shape[] shapes) {
		int result = 0;
		for (Shape form : shapes) {
			result += form.getHeight();
		}
		return result;
	}

	private String setSpaceOrEnter() {
		return direction == "row" ? getOffset(margin) : "\n".repeat(margin - 1);
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction.matches("row|column") ? direction : "row";
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = (margin < 1) ? 1 : margin;
	}

}
