//input your own rectangle dimensions (the main method calls the game like features)


import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		
		Scanner input = new Scanner(System.in);
		
		int left, bottom, width, height, area, perimeter;
		boolean contain;

		System.out.println("Write the characteristics of the first rectangle (bottom left x coordinate, bottom left y coordinate, width, height");
		left = Integer.valueOf(inputs());
		bottom = Integer.valueOf(inputs());
		width = Integer.valueOf(inputs());
		height = Integer.valueOf(inputs());
	
		Rectangle r1 = new Rectangle(left, bottom, width, height);
		
		System.out.println(r1);
		
		area = r1.area();
		perimeter = r1.perimeter();
		
		//rectangle 2
		
		System.out.println();
		
		System.out.println("Write the characteristics of the second rectangle (bottom left x coordinate, bottom left y coordinate, width, height");
		left = Integer.valueOf(inputs());
		bottom = Integer.valueOf(inputs());
		width = Integer.valueOf(inputs());
		height = Integer.valueOf(inputs());
		
		/*
		left = -4;
		bottom = -2;
		width = 3;
		height = 2;
		*/
		
		Rectangle r2 = new Rectangle(left, bottom, width, height);
		
		System.out.println(r2);
		
		System.out.println();
		
		contain =  r1.contains(r2);
		
		Rectangle r3 = new Rectangle();
		
		r3 = r3.intersection(r1, r2);
		
		//for you to refer to when checking r3 in the game like features
		System.out.println("r3 --> " + r3);
		
		perimeter = r1.totalPerimeter(r1, r2);
		
		System.out.println();
		
		r1.printRectangles(r2, r3);
		
	}
	
	public static String inputs() {
		
		Scanner input = new Scanner(System.in);
		
		String userInput = "";
		
		boolean done1 = false;
		
		while(done1 == false) {
			
			userInput = input.next();
			done1 = isValid(userInput);
			//System.out.println(userInput);
			
		}
		
		
		return userInput;
	}
	
	public static boolean isValid(String input){
		
		boolean result = false;
		int length = input.length();
		char firstChar = input.charAt(0);
		char secondChar;
		boolean invalid = false;
		int ACSII1 = (int) firstChar;
		int ACSII2 = 0;
		
		//delete later
		//System.out.println("Length: " + length);
		
		if(ACSII1 == 45) {
			
			for(int i = 1; i < length; i++) {
				
				secondChar = input.charAt(i);
				//delete later
				//System.out.println("Char2: " + secondChar);
				
				ACSII2 = (int) secondChar;
				if(ACSII2 < 48 || ACSII2 > 57) {
					
					invalid = true;
					
				}
			}
		}
		else if(ACSII1 >= 49 && ACSII1 <= 57) {
			
			for(int i = 0; i < length; i++) {
				
				secondChar = input.charAt(i);
				//delete later
				//System.out.println("Char1: " + secondChar);
				
				ACSII2 = (int) secondChar;
				if(ACSII2 < 48 || ACSII2 > 57) {
					
					invalid = true;
					
				}
			}
		}
		else if(ACSII1 == 48) {
			
			if(length != 1) {
				
				invalid = true;
				
			}
		}
		else {
			
			//delete later
			System.out.println("Invalid input. Ensure input is an integer");
			invalid = true;
			
		}
		
		if(invalid == false) {
			
			result = true;
			
		}
		
		//delete later
		//System.out.println(result);
		
		return result;
	}
}
