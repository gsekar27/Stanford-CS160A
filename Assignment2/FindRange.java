/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	private static final int SENTINAL = 1;
	public void run() {
		/* You fill this in */
		
		int n = readInt("Enter a number: ");
		int turns = 0;
		while(true) {		
			if(n==SENTINAL) break;
			
			//For even case, divide by 2
			///n = (n%2 == 0)? n/2:3*n + 1;
			println(n + "is " + ((n%2 == 0)?"Odd":"Even") + " So I make " + ((n%2 == 0)?"3n+1":"half") + " : " + ((n%2 == 0)? n/2:3*n + 1) );
			n = (n%2 == 0)? n/2:3*n + 1;
			turns += 1;
		}
		println("The process took " + turns + "to reach 1");
	}
}

