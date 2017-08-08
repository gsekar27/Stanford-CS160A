/*
 * File: Pyramid.java
 * Name: Ganmani Sekar
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 15;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT =6;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 7;
	
	public void run() {
		/* You fill this in. */
		for(int i= 1,n = BRICKS_IN_BASE; i <= BRICKS_IN_BASE; i++,n--) {
			
			double startXPos = getRowXPos(n);
			double startYPos = getHeight() - (i * BRICK_HEIGHT);
			makeNextRow(startXPos,startYPos,n);
		}
	}
	
	private double getRowXPos(int bricksInRow) {	
		return (getWidth() - (bricksInRow * BRICK_WIDTH))/2;
	}
	
	private void makeNextRow(double startXPos, double StartYPos, int NumOfBricksInRow){
		
		for(int i = NumOfBricksInRow; i > 0; i--){
			add(new GRect(startXPos,StartYPos, BRICK_WIDTH, BRICK_HEIGHT));
			startXPos += BRICK_WIDTH;
			
		}
	}
}

