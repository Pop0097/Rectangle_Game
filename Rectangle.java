class Rectangle
{
	private int x, y, w, h;

	//default constructor for the Rectangle object
	public Rectangle() {
		this.x = 0;
		this.y = 0;
		this.w = 0;
		this.h = 0;
	}

	//constructor for the Rectangle object
	public Rectangle(int left, int bottom, int width, int height) {
		set(left, bottom, width, height); //sets values for the rectangle
	}

	//checks if the inputs are valid and modifies invalid inputs
	public void set(int xx, int yy, int ww, int hh) {
		this.x = xx;
		this.y = yy;
		this.w = ww;
		this.h = hh;

		//if dimensions are negative, set value to 0 and the rectangle will be treated as a line
		if(ww < 0) {
			this.w = 0;
		}
		if(hh < 0) {
			this.h = 0;
		}
	}

	public String toString() {
		return "Bottom-left corner coordinates: (" + this.x + "," + this.y + ")\nDimensions: " + this.w + " by " + this.h + "\nPerimeter: " + this.perimeter() + "\nArea: " + this.area();
	}

	//calculates the area of the object
	public int area() {
		return this.w * this.h;

	}

	//calculates the perimeter of the object
	public int perimeter() {
		int perimeter = 0;
		//the first two statements check if the object is a line.
		if(this.w == 0) {
			perimeter = this.h;
		}
		else if(this.h == 0) {
			perimeter = this.w;
		}
		else {
			perimeter = 2*this.w + 2*this.h;
		}
		return perimeter;
	}

	//this function checks if the r2 is fully contained in the rectangle whose instance is passed
	public boolean contains(Rectangle r2) {
		boolean contains = false;

		//the top right coordinate for Rectangle 1
		int r1x2 = this.x+this.w;
		int r1y2 = this.y+this.h;

		//the top right coordinate for Rectangle 2 (this one is being checked to see if it is inscribed in triangle 1)
		int r2x2 = r2.x+r2.w;
		int r2y2 = r2.y+r2.h;

		if(this.x <= r2.x && this.y <= r2.y && r1x2 >= r2x2 && r1y2 >= r2y2) { //checks if all coordinates of the second rectangle fall within those of the first rectangle
			contains = true;
		}
		return contains;
	}

	//Finds the rectangle that is contained by both r1 and r2
	public static Rectangle containedRectangle(Rectangle r1, Rectangle r2){
		Rectangle r3 = new Rectangle();
		if(((r1.x + r1.w) < r2.x) || ((r2.x +r2.w) < r1.x) || ((r1.y + r1.h) < r2.y) || ((r2.y + r2.h)< r1.y)){ //checks to see if the rectangles do not intersect at all			return r3;
			return r3;
		}
		else{ //if it is concluded that they do intersect, coordinates are determined.
			r3.x = Math.max(r1.x, r2.x);
			r3.y = Math.max(r1.y, r2.y);
			r3.w = Math.min((r1.x + r1.w), (r2.x + r2.w)) - r3.x;
			r3.h = Math.min((r1.y + r1.h), (r2.y + r2.h)) - r3.y;
			return r3;
		}
	}

	//Calculates the perimeter of the polygon made by r1 and r2. If r1 and r2 do not overlapp, the sum of their perimeters is returned
	public static int totalPerimeter(Rectangle r1, Rectangle r2) {
		int totalPerimeter = 0;

		int r1Perimeter, r2Perimeter, r3Perimeter, subtract = 0;

		//checks to see if the rectangles are contained and there is an edge that is overlapping (this case will change how the perimeter is calculated)
		boolean contain1 = r1.contains(r2);
		boolean contain2 = r2.contains(r1);

		Rectangle r3 = new Rectangle();
		r3 = r3.containedRectangle(r1, r2); //this is the rectangle formed from the overlapping area of r1 and r2

		//calculates perimeter of all rectangles
		r1Perimeter = r1.perimeter();
		r2Perimeter = r2.perimeter();
		r3Perimeter = r3.perimeter();

		//the top right coordinate for rectangle 1
		int r1x2 = r1.x+r1.w;
		int r1y2 = r1.y+r1.h;

		//the top right coordinate for rectangle 2 (this one is being checked to see if it is inscribed in triangle 1)
		int r2x2 = r2.x+r2.w;
		int r2y2 = r2.y+r2.h;


		if(contain1 == true && contain2 == false) { //if r2 is fully contained in r1
			totalPerimeter = r1Perimeter;
		}
		else if(contain2 == true && contain1 == false) {//if r1 is fully contained in r2
			totalPerimeter = r2Perimeter;
		}
		else{//if rectangles are not contained in any way or if rectangles do not overlap
			if((r1.h  == 0 || r2.h == 0) || (r1.w == 0 || r2.w == 0) ) {
				subtract = 0;
			}
			else {
				if(r1.x == r2x2 || r1x2 == r2.x) {
					subtract += r3.h;
				}
				if(r1.y == r2y2 || r1y2 == r2.y) {
					subtract += r3.w;
				}
			}
			totalPerimeter = r1Perimeter + r2Perimeter - r3Perimeter - subtract;

		}
		return totalPerimeter;
	}

	//prints the rectangles using texts (one 'x' represents one unit)
	public void printRectangles(Rectangle r2, Rectangle r3) {
		int[] x = {this.x, r2.x, r3.x};
		int[] y = {this.y, r2.y, r3.y};
		int[] width = {this.w, r2.w, r3.w};
		int[] height = {this.h, r2.h, r3.h};
		Rectangle[] rectangles = {this, r2, r3};
		boolean Rectangle3Exists = true;

		for(int i = 0; i < 3; i++){ //this for loop prints out all three rectangles
			//if the object has a side length of 0, it will not be printed using 'x'. Instead, text will describe its properties
			System.out.println("Rectangle " + (i + 1) + ":");
			System.out.println();

			if(height[i] == 0 && width[i] == 0) { //if both sides have a magnitude of 0
				if(i == 2 && ((this.x + this.w) < r2.x) || ((r2.x +r2.w) < this.x) || ((this.y + this.h) < r2.y) || ((r2.y + r2.h)< this.y) ) { //if r1 and r2 do not intersect
					System.out.println("Rectangle 3 does not exist because Rectangles 1 and 2 do not intersect");
					Rectangle3Exists = false;
				}
				else { //presents object as a point
					System.out.println("The rectangle is a point at (" + x[i] + "," + y[i] + ")");
				}
			}
			else if(height[i] == 0 || width[i] == 0) { //if only one side has a magnitude of 0
				if(height[i] == 0) {  //horizontal line
					System.out.println("The rectangle is a line that has its left-most coordinate (" + x[i] + "," + y[i] + ") with a length of " + width[i]);
				}
				else {	//vertical line
					System.out.println("The rectangle is a line that has its bottom-most coordinate (" + x[i] + "," + y[i] + ") with a length of " + height[i]);
				}
			}
			else { //if both sides have a magnitude greater than 0
				for(int j = 0; j < height[i]; j++) {
					if(j == 0 || j == height[i] - 1) { //if the program is printing the bottom or top most row
						for(int k = 0; k < width[i]; k++) {
							System.out.print("x");
						}
						System.out.println();
					}
					else {
						System.out.print("x");
						for(int l = 0; l < width[i]-2; l++) {
							System.out.print(" ");
						}
						if(width[i] > 1) {
							System.out.print("x");
						}
						System.out.println();
					}
				}
			}
			System.out.println();
			if(!(i == 2 && !Rectangle3Exists)) { //does not print the Rectangle 3 if it is not defined
				System.out.println(rectangles[i]); 
			}
			System.out.println();
		}
	}
}
