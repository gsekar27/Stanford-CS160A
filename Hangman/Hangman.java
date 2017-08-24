/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
 private static final int NO_OF_GUESSES = 8;
 private static final int APPLICATION_WIDTH = 600;
 private static final int APPLICATION_HEIGHT = 1000;

 public void init() {
  canvas = new HangmanCanvas();
  add(canvas);
 }
 public void run() {
  selectWord();
  StartGame();

  while (!gameOver) {
   playGame();
  }
  finalGameStatus();

 }


 /** Selects a secret word from the HangmanLexicon Class */
 private void selectWord() {

  HangmanLexicon lex = new HangmanLexicon();
  rgen = RandomGenerator.getInstance();
  //rgen.setSeed(1);
  secretWord = lex.getWord(rgen.nextInt(0, lex.getWordCount() - 1));
  //println("you selected word is :" + secretWord);	
 }

 /** plays the Hangman game*/
 private void playGame() {
  String guessedLetter;
  guessedLetter = getGuess();
  if (isCorrectGuess(guessedLetter)) {
   updateGuessedWord(guessedLetter);
  } else {
   updateGuessesLeft(guessedLetter);
  }
  gameStatusToConsole();

 }

 /** Sets the console and updates the instance variable */
 private void StartGame() {
  resetGame();
  println("Welcome  to Hangman!");
  gameStatusToConsole();
 }

 /** Update the instance variable */
 private void resetGame() {
  setSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT));
  canvas.reset();
  guessesLeft = NO_OF_GUESSES;
  gameOver = false;
  guessedWord = "";

  for (int i = 0; i < secretWord.length(); i++)
   guessedWord += "-";
 }

 /** Prints the game status to console */
 private void gameStatusToConsole() {
  canvas.displayWord(guessedWord);
  if (guessedWord.equals(secretWord)) {
   gameOver = true;
   return;
  }
  println("The word now looks like this: " + guessedWord);
  println("you have " + guessesLeft + " guesses left.");
 }

 /** Gets the guess letter from the player */
 private String getGuess() {
  String temp;
  while (true) {
   temp = readLine("Your guess: ");

   //player must enter a letter as a guess
   if ((temp.length() != 1)) {
    println("Guess is illegal");
   } else {
    break;
   }
  }
  return temp.toUpperCase();
 }

 /** Checks if the guess by player and returns true if the guess is correct else false */
 private boolean isCorrectGuess(String guess) {
  if (secretWord.indexOf(guess) == -1) return false;
  return true;
 }

 /** Update the instance variables  when the guess is wrong*/
 private void updateGuessesLeft(String guess) {
  println("Ther are no " + guess + "'s in the word.");
  canvas.noteIncorrectGuess(guess.charAt(0));
  guessesLeft--;
  if (guessesLeft == 0) gameOver = true;
 }

 /** Updates the game status buy showing the correct guess*/
 private void updateGuessedWord(String guess) {
  int index = secretWord.indexOf(guess);

  //Search and updates for all occurrence of the guess
  while (index != -1) {
   guessedWord = guessedWord.substring(0, index) + guess + guessedWord.substring(index + 1);
   index = secretWord.indexOf(guess, index + 1);
  }
 }

 /** updates the final status once the game is over*/
 private void finalGameStatus() {
  if (guessedWord.equals(secretWord)) {
   println("That guess is correct.");
   println("You guessed the word: " + secretWord);
   println("You win");
  } else {
   println("You're completely hung.");
   println("the word was: " + secretWord);
   println("You lose.");
  }
 }


 /** Private instance variable*/
 private String secretWord;
 private String guessedWord;
 private int guessesLeft;
 private boolean gameOver;
 private RandomGenerator rgen;
 private HangmanCanvas canvas;


}
