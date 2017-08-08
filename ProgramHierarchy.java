/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

import javax.swing.text.GapContent;

public class ProgramHierarchy extends GraphicsProgram {	
	
	private static final int RECT_WIDTH = 55;
	private static final int RECT_HEIGHT = 25;
	private static final int SAME_LEVEL_GAP = 10;
	private static final int NUM_OF_LEVEL = 2;
	
	public void run() {
		/* You fill this in. */
		double topLevel_Xpos = (getWidth() - RECT_WIDTH)/2;
		double topLevel_Ypos = (getHeight() - ((NUM_OF_LEVEL + 1)*RECT_HEIGHT))/2;
		double topLine_xpos = topLevel_Xpos + (RECT_WIDTH/2);
		double topLine_ypos = topLevel_Ypos + RECT_HEIGHT;
		
		createTopHierachy(topLevel_Xpos, topLevel_Ypos);
		
		double SubLevel_StartXPos = (getWidth() - ((3* RECT_WIDTH) + (2 * SAME_LEVEL_GAP)))/2;
		double SubLevel_StartYPos = topLevel_Xpos * (NUM_OF_LEVEL);
	
		for(int i = 0; i < 3 ; i++){
			
			switch(i){
				case 0:
					createSubClass(SubLevel_StartXPos, SubLevel_StartYPos ,"Graphic");
					add(new GLine(topLine_xpos,topLine_ypos,SubLevel_StartXPos + RECT_WIDTH/2,SubLevel_StartYPos )); 
					break;
				case 1:
					SubLevel_StartXPos+= RECT_WIDTH + SAME_LEVEL_GAP;
					createSubClass(SubLevel_StartXPos, SubLevel_StartYPos ,"Console");
					add(new GLine(topLine_xpos,topLine_ypos,SubLevel_StartXPos + RECT_WIDTH/2,SubLevel_StartYPos ));
					break;
				case 2:
					SubLevel_StartXPos+= RECT_WIDTH + SAME_LEVEL_GAP;
					createSubClass(SubLevel_StartXPos, SubLevel_StartYPos ,"Dialog");
					add(new GLine(topLine_xpos,topLine_ypos,SubLevel_StartXPos + RECT_WIDTH/2,SubLevel_StartYPos ));
					break;
			}
		}		
	}
	
	/*
	 * Creates the top level hierachy in the center of the canvas 
	 * assuming there is only one sub level
	 */
	private void createTopHierachy(double rect_Xpos, double rect_Ypos ){
		add(new GRect(rect_Xpos, rect_Ypos ,RECT_WIDTH, RECT_HEIGHT ));
		addLabelToBox("Program", rect_Xpos, rect_Ypos);
		}
	
	
	private void createSubClass(double xpos, double ypos, String labl){	
		add( new GRect(xpos, ypos,RECT_WIDTH, RECT_HEIGHT ));
		addLabelToBox(labl, xpos, ypos);	
	}
	
	private void addLabelToBox(String labl, double Box_xpos, double Box_ypos) {
		
		GLabel label = new GLabel(labl);
		double labl_Xpos = ((Box_xpos + RECT_WIDTH + Box_xpos)/2) - (label.getWidth()/2);
		double labl_Ypos = ((Box_ypos + RECT_HEIGHT + Box_ypos)/2) + (label.getAscent()/2);
		println(labl_Xpos);
		add(label, labl_Xpos, labl_Ypos);
	}
}

