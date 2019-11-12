

//the program also prints each rectangle individually. Call the method Rectangle.printRectangles(Rectangle, Rectangle) to print all three rectangles. 


class Rectangle
{
	private int x, y, w, h;
	
	//2
	public Rectangle() {
		
		this.x = 0;
		this.y = 0;
		this.w = 0;
		this.h = 0;
		
	}
	
	//3
	public Rectangle(int left, int bottom, int width, int height) {
		
		set(left, bottom, width, height);
		
	}
	
	//4
	public void set(int xx, int yy, int ww, int hh) {
		
		this.x = xx;
		this.y = yy;
		this.w = ww;
		this.h = hh;
		
		//if dimensions are negative, set value to 0
		if(ww < 0) {
			
			this.w = 0;
			
		}
		
		if(hh < 0) {
			
			this.h = 0;
			
		}
	}
	
	//5
	public String toString() {
		
		return "base: (" + this.x + "," + this.y + ") w:" + this.w + " h:" + this.h;
		
	}
	
	//6
	public int area() {
		
		int area = 0;
		
		area = this.w * this.h;
				
		return area;
		
	}
	
	//7
	public int perimeter() {
		
		int perimeter = 0;
		
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
	
	//8
	public boolean contains(Rectangle r2) {
		
		boolean result = false;
		
		//the top right coordinate for Rectangle 1
		int r1x2 = this.x+this.w;
		int r1y2 = this.y+this.h;
		
		//the top right coordinate for Rectangle 2 (this one is being checked to see if it is inscribed in triangle 1)
		int r2x2 = r2.x+r2.w;
		int r2y2 = r2.y+r2.h;
		
		if(this.x <= r2.x && this.y <= r2.y && r1x2 >= r2x2 && r1y2 >= r2y2) { //checks if all coordinates of the second rectangle fall within those of the first rectangle
				
			result = true;
				
		}
		
		return result;
		
	}

	//9
	public static Rectangle intersection(Rectangle r1, Rectangle r2){
		Rectangle r3 = new Rectangle();
		
		if(((r1.x + r1.w) < r2.x) || ((r2.x +r2.w) < r1.x) || ((r1.y + r1.h) < r2.y) || ((r2.y + r2.h)< r1.y)){ //checks to see if the rectangles do not intersect at all
			
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
	
	//10
	public static int totalPerimeter(Rectangle r1, Rectangle r2) {
		
		int perimeter = 0;
		
		//temporary placeholder rectangles that will be used in operations
		Rectangle a = new Rectangle(); 
		Rectangle b = new Rectangle();//rectangle that is contained
		
		int r1p, r2p, r3p, subtract = 0;
		
	
		//checks to see if the rectangles are contained and there is an edge that is overlapping (this case will change how the perimeter is calculated)
		boolean contain1 = r1.contains(r2);
		boolean contain2 = r2.contains(r1);
		
		Rectangle r3 = new Rectangle(); 
		r3 = r3.intersection(r1, r2); //this is the rectangle formed from the overlapping area of r1 and r2
		
		//calculates perimeter of all rectangles 
		r1p = r1.perimeter();
		r2p = r2.perimeter();
		r3p = r3.perimeter();
		
		//the top right coordinate for rectangle 1
		int r1x2 = r1.x+r1.w;
		int r1y2 = r1.y+r1.h;
		
		//the top right coordinate for rectangle 2 (this one is being checked to see if it is inscribed in triangle 1)
		int r2x2 = r2.x+r2.w;
		int r2y2 = r2.y+r2.h;
				
			
		if(contain1 == true && contain2 == false) { //if r2 is fully contained in r1
				
			perimeter = r1p;
				
		}
		else if(contain2 == true && contain1 == false) {//if r1 is fully contained in r2
				
			perimeter = r2p;
				
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
			
			
			perimeter = r1p + r2p - r3p - subtract;
				
		}
		
		
		return perimeter;
	
	}
	
	public boolean trueContain(Rectangle r2) {
		
		boolean result = false;
		
		//the top right coordinate for rectangle 1
		int r1x2 = this.x+this.w;
		int r1y2 = this.y+this.h;
		
		//the top right coordinate for rectangle 2 (this one is being checked to see if it is inscribed in triangle 1)
		int r2x2 = r2.x+r2.w;
		int r2y2 = r2.y+r2.h;
		
		if(this.x < r2.x && this.y < r2.y && r1x2 > r2x2 && r1y2 > r2y2) {
			result = true;
		}
		
		//System.out.println("true contain: " + result);
		return result;
		
	}
	
	
	//Game-like Features:
	public void printRectangles(Rectangle r2, Rectangle r3) { // make this method be called from the total perimeter function
			
		int[] x = new int[3];
			x[0] = this.x;
			x[1] = r2.x;
			x[2] = r3.x;
		
		int[] y = new int[3];
			y[0] = this.y;
			y[1] = r2.y;
			y[2] = r3.y;
			
		int[] width = new int[3];
			width[0] = this.w;
			width[1] = r2.w;
			width[2] = r3.w;
		
		int[] height = new int[3];
			height[0] = this.h;
			height[1] = r2.h;
			height[2] = r3.h;
			
		int[]perimeter = new int[3];
			perimeter[0] = this.perimeter();
			perimeter[1] = r2.perimeter();
			perimeter[2] = r3.perimeter();
				
		int[] area = new int[3];
			area[0] = this.area();
			area[1] = r2.area();
			area[2] = r3.area();
		
		int totalP = this.totalPerimeter(this, r2);
		
		for(int i = 0; i < 3; i++){
			
			//if the rectangle has a side length of 0, it will not be printed using 'x', instead text will describe its properties
			
			System.out.println("Rectangle " + (i + 1) + ":");
			System.out.println();
			
			if(height[i] == 0 && width[i] == 0) { //if both sides is 0
				
				if(i == 2 && ((this.x + this.w) < r2.x) || ((r2.x +r2.w) < this.x) || ((this.y + this.h) < r2.y) || ((r2.y + r2.h)< this.y) ) { //if r1 and r2 do not overlap and rectangle 3 is being observed
					
					System.out.println("Rectangle 3 does not exist because Rectangles 1 and 2 do not overlap");
					
				}
				else { //present rectangle as a point
					
					System.out.println("Rectangle is just a point at (" + x[i] + "," + y[i] + ")");
					
				}
				
			}
			else if(height[i] == 0 || width[i] == 0) { //if only one side is 0
				
				if(height[i] == 0) { 
					
					System.out.println("Rectangle is just a line that starts (left coordinate) at (" + x[i] + "," + y[i] + ") with a width of " + width[i]);

				}
				else {
					
					System.out.println("Rectangle is just a line that starts (bottom coordinate) at (" + x[i] + "," + y[i] + ") with a height of " + height[i]);
					
				}
				
			}
			else { //if both sides are not 0
				
				for(int j = 0; j < height[i]; j++) { 
					
					if(j == 0 || j == height[i] - 1) { //if top or bottom, all rows have 'x'
						
						for(int k = 0; k < width[i]; k++) { 
							
							System.out.print("x");
							
						}
						
						System.out.println();
					}
					else { //else only extremes have 'x'
						
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
			System.out.println("Width = " + width[i] + "\nHeight = " + height[i] + "\nPerimeter = " + perimeter[i] + "\nArea = " + area[i]); //prints characteristics of rectangles
			System.out.println();
			
		}
	}
}