/*
 * File: Breakout.java
 * -------------------
 * Name: Ganmani Sekar
 * Section Leader: None
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

 /** Width and height of application window in pixels */
 public static final int APPLICATION_WIDTH = 400;
 public static final int APPLICATION_HEIGHT = 600;

 /** Dimensions of game board (usually the same) */
 private static final int WIDTH = APPLICATION_WIDTH;
 private static final int HEIGHT = APPLICATION_HEIGHT;

 /** Dimensions of the paddle */
 private static final int PADDLE_WIDTH = 60;
 private static final int PADDLE_HEIGHT = 10;

 /** Offset of the paddle up from the bottom */
 private static final int PADDLE_Y_OFFSET = 30;

 /** Number of bricks per row */
 private static final int NBRICKS_PER_ROW = 10;

 /** Number of rows of bricks */
 private static final int NBRICK_ROWS = 10;

 /** Separation between bricks */
 private static final int BRICK_SEP = 4;

 /** Width of a brick */
 private static final int BRICK_WIDTH =
  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

 /** Height of a brick */
 private static final int BRICK_HEIGHT = 8;

 /** Radius of the ball in pixels */
 private static final int BALL_RADIUS = 10;

 /** Offset of the top brick row from the top */
 private static final int BRICK_Y_OFFSET = 70;

 /** Number of turns */
 private static final int NTURNS = 3;


 /* Method: run() */

 /** Runs the Break out program. */
 public void run() {
  setup();
  pause(delay + delay);
  playGame();
 }

 /** Sets the initial set for break out*/
 private void setup() {

  setApplication();
  createBricks();
  setupPaddle();
 }

 /** Sets the application window*/
 private void setApplication() {

  setSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT));
  setTitle("Break Out");
  setBackground(Color.lightGray);

  //Sets the status of the game in application
  turnsLeft = new GLabel("");
  turnsLeft.setFont("SansSerif-bold-18");
  updateGameStatusBar();

  //Todo: make it dynamic
  add(turnsLeft, (0.60 * WIDTH), (0.10 * HEIGHT));


 }

 /**Break out game rules are set and game is played */
 private void playGame() {

   setupBall();
   updateGameStatusBar();
   while (gameOn) {
    moveBall();
    checkForCollision();
    pause(delay);
   }

   gameStatus();
  }
  /** Adds the set of bricks to the canvas needed for the initial setup of break out */
 private void createBricks() {
  double start_x;
  double x;
  double y = BRICK_Y_OFFSET;
  Color brickColor;

  start_x = (WIDTH - ((NBRICKS_PER_ROW * BRICK_WIDTH) +
   ((NBRICKS_PER_ROW - 1) * BRICK_SEP))) / 2;
  println("Debug Start_x: " + start_x);

  for (int i = 1; i <= NBRICK_ROWS; i++) {
   x = start_x;
   switch (i) {
    case 1:
    case 2:
     brickColor = Color.RED;
     break;
    case 3:
    case 4:
     brickColor = Color.ORANGE;
     break;
    case 5:
    case 6:
     brickColor = Color.YELLOW;
     break;
    case 7:
    case 8:
     brickColor = Color.GREEN;
     break;
    case 9:
    case 10:
     brickColor = Color.CYAN;
     break;
    default:
     brickColor = Color.GRAY;
     break;

   }

   for (int j = 1; j <= NBRICKS_PER_ROW; j++) {
    addBrick(BRICK_WIDTH, BRICK_HEIGHT, x, y, brickColor);
    //Updates the x position of the brick in new column
    x += BRICK_WIDTH + BRICK_SEP;
   }
   //updates the y position of the brick in new row
   y += BRICK_HEIGHT + BRICK_SEP;
  }
 }

 /** 
  * @param h height of the brick
  * @param w width of the brick
  * @param x x-coordinate of the brick top left corner
  * @param y y-coordinate of the brick top left corner
  * @param color of the brick
  * @result adds a brick to the canvas
  */
 private void addBrick(double h, double w, double x, double y, Color color) {
  GRect rect = new GRect(h, w);
  rect.setFilled(true);
  rect.setFillColor(color);
  add(rect, x, y);
 }

 /** Adds the paddle to the application and tracks the mouse */
 private void setupPaddle() {

  //Add the paddle to the canvas
  paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
  paddle.setFilled(true);
  add(paddle, rgen.nextDouble(0, WIDTH), HEIGHT - (PADDLE_Y_OFFSET + PADDLE_HEIGHT));

  //Track the mouse
  addMouseListeners();

 }

 /** Called when mouse is moved to move the paddle along with the mouse */
 public void mouseMoved(MouseEvent e) {

  if (gameOn == false) return;
  double dx = e.getX() - paddle.getX();

  //moves the paddle only when paddle x coordinates are within the width of the application after displacement
  if ((dx + paddle.getX()) >= 0 && (dx + paddle.getX() + PADDLE_WIDTH) <= WIDTH) paddle.move(dx, 0);

 }

 /** Sets the ball for the break out game */
 private void setupBall() {

  //Add the ball to the application
  ball = new GOval(ballDiameter, ballDiameter);
  ball.setFilled(true);
  ball.setFillColor(Color.MAGENTA);
  add(ball, (WIDTH - ballDiameter) / 2, (HEIGHT - ballDiameter) / 2);

  noOfTurnsLeft--;
  //set the initial velocity
  vx = rgen.nextDouble(1.0, 3.0);
  if (rgen.nextBoolean(0.5)) vx = -vx;
  vy = +3.0;

  //println("debug ball x ps " + ball.getX() + "ball y pos " + ball.getY());
 }

 /** Moves the ball */
 private void moveBall() {
  ball.move(vx, vy);
 }

 /** Check for Collision  against walls or brick and update the game status and ball velocity*/
 private void checkForCollision() {

  //When the ball hits the bottom , if the user has turns left, it restarts else he looses
  if (ball.getY() > HEIGHT - ballDiameter) {
   remove(ball);
   if (noOfTurnsLeft == 0) gameOn = false;
   else {
    pause(delay);
    setupBall();
    updateGameStatusBar();
   }
   return;
  }

  GObject collider;
  collider = getCollidingObject();
  if (collider != null) {
   bounceClip.play();
   //when ball hits the brick, the brick will be removed
   if (collider != paddle) {
    remove(collider);
    NoOfBricksLeft--;
    if (hasWon()) {
     gameOn = false;
     return;
    }
   }

   //update y velocity only when ball is still above the paddle
   if (ball.getY() + ballDiameter <= paddle.getY() + PADDLE_HEIGHT) vy = -vy;
  }

  checkForCollisionWithWall();

 }

 /** returns true if the player has win else false */
 private boolean hasWon() {
  return NoOfBricksLeft == 0;
 }

 /** updates the status label */
 private void updateGameStatusBar() {
   turnsLeft.setLabel("Turns Left: " + noOfTurnsLeft);
  }
  /** returns the object the ball collides with if any else returns null*/
 private GObject getCollidingObject() {
  if (getElementAt(ball.getX(), ball.getY()) != null)
   return getElementAt(ball.getX(), ball.getY());
  else if (getElementAt(ball.getX() + ballDiameter, ball.getY()) != null)
   return getElementAt(ball.getX() + ballDiameter, ball.getY());
  else if (getElementAt(ball.getX(), ball.getY() + ballDiameter) != null)
   return getElementAt(ball.getX(), ball.getY() + ballDiameter);
  else if (getElementAt(ball.getX() + ballDiameter, ball.getY() + ballDiameter) != null)
   return getElementAt(ball.getX() + ballDiameter, ball.getY() + ballDiameter);
  else
   return null;
 }

 /** checks if colliding with wall and updates the ball velocity */
 private void checkForCollisionWithWall() {
  if (ball.getY() < 0) {
   bounceClip.play();
   vy = -vy;
  } else if ((ball.getX() > WIDTH - ballDiameter) || (ball.getX() < 0)) {
   bounceClip.play();
   vx = -vx;
  }
  return;
 }

 /** Displays the final game status message */
 private void gameStatus() {
  GLabel status;

  if (hasWon()) status = new GLabel("You Won!!!");
  else status = new GLabel("You Lost!!!");

  double x = (WIDTH - status.getWidth()) / 2;
  double y = (HEIGHT - status.getAscent()) / 2;
  status.setFont("SansSerif-bold-18");
  add(status, x, y);
 }

 /** starts the game on a mouse click **/
 /*
 	public void mouseClicked(MouseEvent e){
 		
 		print("debug" + noOfTurnsLeft);
 		if(gameOn == false && noOfTurnsLeft == 3){
 			noOfTurnsLeft--;
 			setupBall();
 			gameOn = true;
 			print("debug goint ot play the game");
 			playGame();
 		}
 	}
 	*/

 /** private instance variable */

 /**Random generator */
 private RandomGenerator rgen = RandomGenerator.getInstance();

 /** paddle for the game */
 private GRect paddle;

 /** ball for the game */
 private GOval ball;

 /** ball diameter */
 private double ballDiameter = 2 * BALL_RADIUS;

 /** Number of the bricks left in the application */
 private int NoOfBricksLeft = NBRICKS_PER_ROW * NBRICK_ROWS;

 /** Velocity of the ball */
 private double vx;
 private double vy;

 /** game status variable */
 private boolean gameOn = true;

 /** delays every move */
 private int delay = 8;

 /** number of turns left for Player */
 private int noOfTurnsLeft = NTURNS;

 /** Status label for the player */
 private GLabel turnsLeft;

 /** Audio when the ball bounces */
 private AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");


}
