/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {

		/* You fill this in */
		println("Enter values to compute pythagorean theorem");
		
		int a = readInt("a: ");
		int b = readInt("b: ");
		println("C: " + Math.sqrt(Math.pow(a,2) + Math.pow(b, 2)));
	}
}
