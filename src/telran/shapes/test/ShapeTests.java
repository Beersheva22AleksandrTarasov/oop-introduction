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
	
}
