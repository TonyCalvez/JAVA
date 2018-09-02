public class YourName {
	public static void main(String[] args) {

		System.out.println("TonyCALVEZ");

	}
}

public class DataTypesInt {
	public static void main(String[] args) {

		System.out.println(2);

	}
}

public class DataTypesBoolean {
	public static void main(String[] args) {

		System.out.println(true);

	}
}

public class DataTypesChar {
	public static void main(String[] args) {

		System.out.println('L');

	}
}

public class Variables {
	public static void main(String[] args) {

		int myNumber = 42;
		boolean isFun = true; 
		char movieRating = 'A'; 
    int myLuckyNumber = 7;

	}
}

public class Comments {
	public static void main(String[] args) {

		// I'm a single line comment! System.out.println("Noise!"); 
		
  /*

  Hello, 
  Java! 

  */

		
	}
}

public class Arithmetic {
	public static void main(String[] args) {
    int sum = 34 + 113;
    int difference = 91 - 205;
    int product = 2 * 8; 
    int quotient = 45 / 3;
    int myNumber = sum + difference;
    int myRemainder = 16 % 7;
		System.out.println(myNumber);

	}
}

public class RelationalOperators {
	public static void main(String[] args) {
		
    
		System.out.println("Main Operator:");
    boolean less = (1 < 2);
    boolean lessequal = (1 <= 2);
    boolean greater = (1 > 2);
    boolean greaterequal = (1 >= 2);
    
    System.out.println(greaterqual);

	}
}

public class EqualityOperators {
	public static void main(String[] args) {

    char myChar = 'A';
    int myInt = -2;
    System.out.println(myChar == myInt);
    
		System.out.println();
	}
}

public class And {
	public static void main(String[] args) {
		int Temperature = 15;
		System.out.println( 10 < Temperature && Temperature < 22);
		
	}
}

public class Or {
	public static void main(String[] args) {
		int Temperature = 15;
		System.out.println( 10 < Temperature || Temperature > 22);
		
	}
}

public class If {
	public static void main(String[] args) {
	int temperature = 15;
		if (temperature < 19) {

			System.out.println("Access granted.");

		}
    else {

			System.out.println("Success!");

		}
		
	}
}

public class Ternary {
	public static void main(String[] args) {
		
		int fuelLevel = 3;

		char canDrive = (fuelLevel > 0) ? 'Y' : 'N';
		System.out.println(canDrive);

	}
}

public class Switch {
	public static void main(String[] args) {
		
		char penaltyKick = 'X';

		switch (penaltyKick) {

			case 'L': System.out.println("Messi shoots to the left and scores!");
								break; 
			case 'R': System.out.println("Messi shoots to the right and misses the goal!");
								break;
			case 'C': System.out.println("Messi shoots down the center, but the keeper blocks it!");
								break;
			default:
				System.out.println("Messi is in position...");

		}

	}
}
import java.util.HashMap;

public class RestaurantForEach {
	public static void main(String[] args) {

		HashMap<String, Integer> restaurantMenu = new HashMap<String, Integer>();

		restaurantMenu.put("Turkey Burger", 13);
		restaurantMenu.put("Naan Pizza", 11);
		restaurantMenu.put("Cranberry Kale Salad", 10);

		System.out.println(restaurantMenu.size());

		for (String item : restaurantMenu.keySet()) {

			System.out.println("A " + item + " costs " + restaurantMenu.get(item) + " dollars.");

		}

	}
}

import java.util.ArrayList;

public class TemperaturesForEach {
	public static void main(String[] args) {

		ArrayList<Integer> weeklyTemperatures = new ArrayList<Integer>();
		weeklyTemperatures.add(78);
		weeklyTemperatures.add(67);
		weeklyTemperatures.add(89); 
		weeklyTemperatures.add(94);
		
		for (Integer temperature : weeklyTemperatures) {
    	System.out.println(temperature);
		}
	
	}
}
