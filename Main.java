import java.util.Scanner;

public class Main {

	static Scanner input = new Scanner(System.in);


	public static void main(String args[]) {
		int left, bottom, width, height, area, perimeter;
		boolean contain;

		//Gets the properties of Rectangle 1 from the user
		System.out.println("Enter the properties of Rectangle 1:");
		System.out.print("x-coordinate of the bottom left corner: ");
		left = Integer.valueOf(inputs());
		System.out.print("y-coordinate of the bottom left corner: ");
		bottom = Integer.valueOf(inputs());
		System.out.print("Width: ");
		width = Integer.valueOf(inputs());
		System.out.print("Height: ");
		height = Integer.valueOf(inputs());

		Rectangle r1 = new Rectangle(left, bottom, width, height); //creates a Rectangle object for Rectangle 1

		System.out.println();

		//Gets the properties of Rectangle 2 from the user
		System.out.println("Enter the properties of Rectangle 2:");
		System.out.print("x-coordinate of the bottom left corner: ");
		left = Integer.valueOf(inputs());
		System.out.print("y-coordinate of the bottom left corner: ");
		bottom = Integer.valueOf(inputs());
		System.out.print("Width: ");
		width = Integer.valueOf(inputs());
		System.out.print("Height: ");
		height = Integer.valueOf(inputs());

		Rectangle r2 = new Rectangle(left, bottom, width, height); //creates a Rectangle object for Rectangle 2

		//outputs the total perimeter of Rectangle 1 and 2
		System.out.println();
		System.out.println("The total perimeter of the polygon made by Rectangle 1 and 2 is " + r1.totalPerimeter(r1, r2));

		System.out.println();

		//Creates Rectangle 3, which is the rectangle formed from the intersection of Rectangle 1 and 2
		Rectangle r3 = new Rectangle();
		r3 = r3.containedRectangle(r1, r2);

		//prints all the three rectangles
		r1.printRectangles(r2, r3);

	}

	public static String inputs() { //this method collects the input from the user
		String userInput = "";
		boolean done = false;

		while(done == false) {
			userInput = input.next(); //collects input as a string so the program does not crash if the user does not input a number
			done = isValid(userInput);
		}
		return userInput;
	}

	public static boolean isValid(String input){ //this method checks if the input is valid
		boolean result = true;
		int length = input.length();
		char firstChar = input.charAt(0);
		char secondChar;
		int ACSII1 = (int) firstChar;
		int ACSII2 = 0;

		if(ACSII1 == 45 || (ACSII1 >= 48 && ACSII1 <= 57)) { //If the first character is a "-" (for negative numbers) or a number (for positive numbers)
			for(int i = 1; i < length; i++) { //checks if every character is a number
				secondChar = input.charAt(i);
				ACSII2 = (int) secondChar;
				if(ACSII2 < 48 || ACSII2 > 57) { //if at least one character is not a number
					result = false;
				}
			}
		}
		else {
			System.out.println("Invalid input. Ensure input is an integer");
			result = false;
		}
		return result;
	}
}
