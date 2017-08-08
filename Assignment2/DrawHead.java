
/**
 * Draws a Face structure to the canvas
 * takes a constant head width, height, eye radius and mouth width and height
 */
import java.awt.Color;

import acm.graphics.*;
import acm.program.*;

public class DrawHead extends GraphicsProgram{
	
	/* constants for head dimension*/
	private static final double HEAD_WIDTH =100;
	private static final double HEAD_HEIGHT = 150;
	
	/* constant declaration for the eye radius*/
	private static final double EYE_RADIUS=10;
	
	/* constant for the mouth dimension*/
	private static final double MOUTH_WIDTH = 50;
	private static final double MOUTH_HEIGHT = 20;
	
	/* Colour declaration for individual elements */
	private static final Color HEAD_COLOR = Color.GRAY;
	private static final Color MOUTH_COLOR = Color.WHITE;
	private static final Color EYE_COLOR = Color.GREEN;
	
	
	public void run(){		
	
		///Calculate the x and y coordinates of head , eye centre  and mouth
		
		//Head is centre in the canvas
		double head_xpos  = (getWidth() - HEAD_WIDTH)/2;
		double head_ypos = (getHeight() - HEAD_HEIGHT)/2;
		
		//left eye centred in the one quarter from both edges of head in x-dim and one quarter from the top edge of head in y-dim
		double leftEyeCentre_xpos = head_xpos + (0.25 * (HEAD_WIDTH));
		double leftEyeCentre_ypos = head_ypos + (0.25 * (HEAD_HEIGHT));
		double rightEyeCentre_xpos = head_xpos + (0.75 * (HEAD_WIDTH));
		double rightEyeCentre_ypos = leftEyeCentre_ypos;
		
		//mouth is centred in the w.r.t to x dim of head and one quarter from the bottom of the head
		double mouth_xpos = ((head_xpos + (HEAD_WIDTH + head_xpos))/2) - (MOUTH_WIDTH/2);
		double mouth_ypos = ((head_ypos + HEAD_HEIGHT) - (0.25 * HEAD_HEIGHT) -(MOUTH_HEIGHT/2));
		
		
		//add face
		addHead(head_xpos,head_ypos);
		addEye(leftEyeCentre_xpos,leftEyeCentre_ypos);
		addEye(rightEyeCentre_xpos,rightEyeCentre_ypos);
		addMouth(mouth_xpos,mouth_ypos);
		
	}
	
	/**
	 * Adds the head to the canvas
	 * @param cx is the x-position of head
	 * @param cy is the y-position of head
	 */
	private void addHead(double x, double y){
		GRect head = new GRect(x,y,HEAD_WIDTH,HEAD_HEIGHT);
		head.setFilled(true);
		head.setFillColor(HEAD_COLOR);
		add(head);
	}
	
	/**
	 * Adds eye to the canvas
	 * @param cx is the x-position of centre of eye
	 * @param cy is the y-position of centre of eye
	 */
	private void addEye(double cx, double cy){
		//Create and add eyes to canvas
		GOval Eye = new GOval(cx - EYE_RADIUS,cy - EYE_RADIUS,2*EYE_RADIUS,2*EYE_RADIUS);
		Eye.setFilled(true);
		Eye.setFillColor(EYE_COLOR);
		add(Eye);	
	}
	
	
	/**
	 * Adds the mouth to the canvas
	 * @param x is the x-position of the mouth
	 * @param y is the y-position of the mouth
	 */
	private void addMouth(double x, double y) {
		//Create and add mouth to canvas
		GRect mouth = new GRect(x,y,MOUTH_WIDTH,MOUTH_HEIGHT);
		mouth.setFilled(true);
		mouth.setFillColor(MOUTH_COLOR);
		add(mouth);
		
	}

}
