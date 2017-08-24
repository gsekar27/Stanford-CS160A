/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;
import acm.util.ErrorException;

public class HangmanCanvas extends GCanvas {

 public void run() {
  reset();
 }

 /** Resets the display so that only the scaffold appears */
 public void reset() {
  clearsCanvas();
  addScaffold();
  NoOfIncorrectGuesses = NO_OF_GUESSES;
 }

 /**
  * Updates the word on the screen to correspond to the current
  * state of the game.  The argument string shows what letters have
  * been guessed so far; unguessed letters are indicated by hyphens.
  */
 public void displayWord(String word) {
  currentGuess.setLabel(word);
 }

 /**
  * Updates the display to correspond to an incorrect guess by the
  * user.  Calling this method causes the next body part to appear
  * on the scaffold and adds the letter to the list of incorrect
  * guesses that appears at the bottom of the window.
  */
 public void noteIncorrectGuess(char letter) {

  switch (NoOfIncorrectGuesses) {
   case 8:
    addHead();
    break;
   case 7:
    addBody();
    break;
   case 6:
    addLeftArm();
    break;
   case 5:
    addRightArm();
    break;
   case 4:
    addLeftLeg();
    break;
   case 3:
    addRightLeg();
    break;
   case 2:
    addLeftFoot();
    break;
   case 1:
    addRightFoot();
    break;
   default:
    throw new ErrorException("Illegal guesses left");
  };

  NoOfIncorrectGuesses--;
  inCorrectGuesses.setLabel(inCorrectGuesses.getLabel() + Character.toString(letter));
 }

 /** Removes all the items in canvas if any */
 private void clearsCanvas() {
  this.removeAll();
  this.setBounds(300, 0, 300, 1000);
 }

 /** Adds the scaffold alone to the canvas */
 private void addScaffold() {
  GLine scaffold = new GLine(TOP_LEFT_X, TOP_LEFT_Y, TOP_LEFT_X, TOP_LEFT_Y + SCAFFOLD_HEIGHT);
  GLine beam = new GLine(TOP_LEFT_X, TOP_LEFT_Y, TOP_LEFT_X + BEAM_LENGTH, TOP_LEFT_Y);
  GLine rope = new GLine(TOP_LEFT_X + BEAM_LENGTH, TOP_LEFT_Y, TOP_LEFT_X + BEAM_LENGTH, TOP_LEFT_Y + ROPE_LENGTH);
  currentGuess = new GLabel("", TOP_LEFT_X, CURRENT_GUESS_Y);
  inCorrectGuesses = new GLabel("", TOP_LEFT_X, INCORRECT_GUESSES_Y);

  add(scaffold);
  add(beam);
  add(rope);
  add(currentGuess);
  add(inCorrectGuesses);
 }


 /** Creates the head of the Hangman and adds to HangmanCanvas*/
 private void addHead() {
  GOval head = new GOval(2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
  add(head, (TOP_LEFT_X + BEAM_LENGTH) - HEAD_RADIUS, TOP_LEFT_Y + ROPE_LENGTH);
 }

 /** Creates the body of the Hangman and adds to Hangman Canvas*/
 private void addBody() {
  GLine body = new GLine(TOP_LEFT_X + BEAM_LENGTH, TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS), TOP_LEFT_X + BEAM_LENGTH, TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + BODY_LENGTH);
  add(body);
 }

 /**	Creates the left arm of the hangman and adds to Hangman Canvas*/
 private void addLeftArm() {
  GLine upperArm = new GLine(TOP_LEFT_X + BEAM_LENGTH, TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD,
   TOP_LEFT_X + BEAM_LENGTH - (UPPER_ARM_LENGTH / 2), TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD);
  GLine lowerArm = new GLine(TOP_LEFT_X + BEAM_LENGTH - (UPPER_ARM_LENGTH / 2), TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD,
   TOP_LEFT_X + BEAM_LENGTH - (UPPER_ARM_LENGTH / 2), TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);

  add(upperArm);
  add(lowerArm);
 }

 /**	Creates the right arm of the hangman and adds to Hangman Canvas*/
 private void addRightArm() {
  GLine upperArm = new GLine(TOP_LEFT_X + BEAM_LENGTH, TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD,
   TOP_LEFT_X + BEAM_LENGTH + (UPPER_ARM_LENGTH / 2), TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD);

  GLine lowerArm = new GLine(TOP_LEFT_X + BEAM_LENGTH + (UPPER_ARM_LENGTH / 2), TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD,
   TOP_LEFT_X + BEAM_LENGTH + (UPPER_ARM_LENGTH / 2), TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);

  add(upperArm);
  add(lowerArm);

 }

 /**	Creates the Left Leg of the hangman and adds to Hangman Canvas*/
 private void addLeftLeg() {
  GLine upperLeg = new GLine(TOP_LEFT_X + BEAM_LENGTH, TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + BODY_LENGTH,
   TOP_LEFT_X + BEAM_LENGTH - (HIP_WIDTH / 2), TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + BODY_LENGTH);

  GLine lowerLeg = new GLine(TOP_LEFT_X + BEAM_LENGTH - (HIP_WIDTH / 2), TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + BODY_LENGTH,
   TOP_LEFT_X + BEAM_LENGTH - (HIP_WIDTH / 2), TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + BODY_LENGTH + LEG_LENGTH);

  add(upperLeg);
  add(lowerLeg);

 }

 /**	Creates the right leg of the hangman and adds to Hangman Canvas*/
 private void addRightLeg() {
  GLine upperLeg = new GLine(TOP_LEFT_X + BEAM_LENGTH, TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + BODY_LENGTH,
   TOP_LEFT_X + BEAM_LENGTH + (HIP_WIDTH / 2), TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + BODY_LENGTH);

  GLine lowerLeg = new GLine(TOP_LEFT_X + BEAM_LENGTH + (HIP_WIDTH / 2), TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + BODY_LENGTH,
   TOP_LEFT_X + BEAM_LENGTH + (HIP_WIDTH / 2), TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + BODY_LENGTH + LEG_LENGTH);

  add(upperLeg);
  add(lowerLeg);
 }

 /**	Creates the left foot of the hangman and adds to Hangman Canvas*/
 private void addLeftFoot() {
  GLine foot = new GLine(TOP_LEFT_X + BEAM_LENGTH - (HIP_WIDTH / 2), TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + BODY_LENGTH + LEG_LENGTH,
   TOP_LEFT_X + BEAM_LENGTH - (HIP_WIDTH / 2) - FOOT_LENGTH, TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + BODY_LENGTH + LEG_LENGTH);

  add(foot);
 }

 /**	Creates the right foot of the hangman and adds to Hangman Canvas*/
 private void addRightFoot() {
  GLine foot = new GLine(TOP_LEFT_X + BEAM_LENGTH + (HIP_WIDTH / 2), TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + BODY_LENGTH + LEG_LENGTH,
   TOP_LEFT_X + BEAM_LENGTH + (HIP_WIDTH / 2) + FOOT_LENGTH, TOP_LEFT_Y + ROPE_LENGTH + (2 * HEAD_RADIUS) + BODY_LENGTH + LEG_LENGTH);

  add(foot);

 }

 /* Constants for the simple version of the picture (in pixels) */
 private static final int SCAFFOLD_HEIGHT = 360;
 private static final int BEAM_LENGTH = 144;
 private static final int ROPE_LENGTH = 18;
 private static final int HEAD_RADIUS = 36;
 private static final int BODY_LENGTH = 144;
 private static final int ARM_OFFSET_FROM_HEAD = 28;
 private static final int UPPER_ARM_LENGTH = 72;
 private static final int LOWER_ARM_LENGTH = 44;
 private static final int HIP_WIDTH = 36;
 private static final int LEG_LENGTH = 108;
 private static final int FOOT_LENGTH = 28;
 private static final int APPLICATION_WIDTH = 300;
 private static final int APPLICATION_HEIGHT = 1000;
 private static final double TOP_LEFT_X = 0.10 * APPLICATION_WIDTH;
 private static final double TOP_LEFT_Y = 0.10 * APPLICATION_HEIGHT;
 private static final double CURRENT_GUESS_Y = 0.60 * APPLICATION_HEIGHT;
 private static final double INCORRECT_GUESSES_Y = 0.65 * APPLICATION_HEIGHT;
 private static final int NO_OF_GUESSES = 8;


 /** private instance variable */
 private GLabel currentGuess;
 private GLabel inCorrectGuesses;
 private int NoOfIncorrectGuesses;

}
