package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.shapes.*;

class ShapeTests {

	@Test
	@Disabled
	void rectangleTest() {
		Rectangle rectangle = new Rectangle(10, 5);
		assertEquals(10, rectangle.getWidth());
		assertEquals(5, rectangle.getHeight());
		displayStrings(rectangle.presentation(20));
		Rectangle.setSymbol("#");
		displayStrings(rectangle.presentation(20));
	}

	private void displayStrings(String strings[]) {
		for (String str : strings) {
			System.out.println(str);
		}
		System.out.println();
	}

	@Test
	@Disabled
	void squareTest() {
		int width = 10;
		Square square = new Square(width);
		assertEquals(10, square.getWidth());
		displayStrings(square.presentation(20));
		Square.setSymbol("+");
		square.setHeight(5);
		displayStrings(square.presentation(20));
	}

	@Test
	@Disabled
	void triangleTest() {
		int width = 10;

		SquareLeftTriangle leftTriangle = new SquareLeftTriangle(width);
		SquareRightTriangle rightTriangle = new SquareRightTriangle(width);

		displayStrings(leftTriangle.presentation(20));
		displayStrings(rightTriangle.presentation(20));
		SquareLeftTriangle.setSymbol("$");
		leftTriangle.setHeight(5);
		rightTriangle.setHeight(5);
		displayStrings(leftTriangle.presentation(20));
		displayStrings(rightTriangle.presentation(20));

	}
	Shape[] shapes = { new Rectangle(10, 3), new Square(10), new SquareLeftTriangle(10), new SquareRightTriangle(10)};
	Canvas canvas = new Canvas(10, 20, shapes);
	Canvas canvas1 = new Canvas(5, 15, shapes);
	Canvas canvas2 = new Canvas(15, 25, shapes);

	@Test
	@Disabled
	void canvasInRowTest() {
		Canvas canvas = new Canvas(10, 4, shapes);
		canvas.setMargin(3);
		displayStrings(canvas.presentation(2));
		Shape[] shapes = { new Rectangle(10, 3), new Square(10), canvas1};
		Canvas canvas1 = new Canvas(10, 4, shapes);
		canvas1.setMargin(5);
		displayStrings(canvas1.presentation(1));
	}

	@Test
//	@Disabled
	void canvasInColumnTest() {
		Canvas canvas = new Canvas(10, 4, shapes);
		canvas.setDirection("column");
		this.canvas.setDirection("column");
		canvas.setMargin(1);
		displayStrings(canvas.presentation(2));
		Shape[] shapes = { new Rectangle(10, 3), new Square(10), canvas2};
		Canvas canvas2 = new Canvas(10, 4, shapes);
		canvas2.setMargin(1);
		displayStrings(canvas2.presentation(3));

	}
}
