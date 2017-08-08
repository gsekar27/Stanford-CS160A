/*
 * File: Target.java
 * Name: Ganmani Sekar
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	
	/** One Inch to pixel units**/
	private static final int ONE_INCH_TO_PIXEL = 72;
	
	/**Color for filled circles**/
	private static final Color CIRCLE_COLOR = Color.RED;
	
	public void run() {
		/* You fill this in. */
		addCircle(ONE_INCH_TO_PIXEL * 1, Color.RED);
		addCircle(ONE_INCH_TO_PIXEL * 0.65, Color.WHITE);
		addCircle(ONE_INCH_TO_PIXEL * 0.3, Color.RED);
		
	}
	/*
	 * Adds the circle to the center of the canvas. Takes parameter radius in pixels and boolean input
	 * filled to specify if the circle to be filled or not add color or not.
	 */
	private void addCircle(double circleRadius, Color circleColor){
		
		double diameter = 2 * circleRadius;
		GOval circle = new GOval((getWidth() - diameter)/2,(getHeight() - diameter)/2, diameter, diameter);
		
		circle.setFilled(true);
		circle.setFillColor(circleColor);
		add(circle);
		
	}
}
