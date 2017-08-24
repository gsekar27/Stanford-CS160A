/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.HashMap;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {

 public static void main(String[] args) {
  new Yahtzee().start(args);
 }

 public void run() {
  IODialog dialog = getDialog();
  nPlayers = dialog.readInt("Enter number of players");
  playerNames = new String[nPlayers];
  for (int i = 1; i <= nPlayers; i++) {
   playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
  }
  display = new YahtzeeDisplay(getGCanvas(), playerNames);
  //display.waitForPlayerToClickRoll(1);
  playGame();
 }

 private void playGame() {
  //display.printMessage("Kannu is amazing");
  initialize();

  //for(int i = 1; i <= N_SCORING_CATEGORIES;i++){
  for (int i = 1; i <= 3; i++) {
   System.out.println("Turn number" + i);
   for (int j = 1; j <= nPlayers; j++) {
    System.out.println("CurrentPlayer" + playerNames[j - 1]);
    currentPlayer = j;
    currenPlayerPlays();
   }
  }
  announceWinner();
 }

 private void announceWinner() {
  // Display upper score and Bonus
  for (int i = 1; i <= nPlayers; i++) {
   int bonus = (upperScore[i - 1] >= UPPER_BONUS_CRITERIA) ? UPPER_BONUS_VALUE : 0;
   display.updateScorecard(UPPER_SCORE, i, upperScore[i - 1]);
   display.updateScorecard(UPPER_BONUS, i, bonus);
   display.updateScorecard(LOWER_SCORE, i, lowerScore[i - 1]);

   //Update total
   totalScore[i - 1] = upperScore[i - 1] + bonus + lowerScore[i - 1];
   display.updateScorecard(TOTAL, i, totalScore[i - 1]);
  }
  int winnerPlayer = Winner();
  //    	System.out.println("winnerPlayer is" + winnerPlayer);
  //    	System.out.println();
  display.printMessage("Congratulations, " + playerNames[winnerPlayer - 1] +
   ", you're the winner with a total score of " + totalScore[winnerPlayer - 1]);

 }

 private int Winner() {
  int winner = 0;
  for (int i = 1; i < nPlayers; i++) {
   if (totalScore[winner] < totalScore[i]) {
    winner = i;
   }
  }
  return winner + 1;
 }

 private void initialize() {
  upperScore = new int[nPlayers];
  lowerScore = new int[nPlayers];
  totalScore = new int[nPlayers];
  playedCategories = new HashMap < Integer, String > ();
 }

 private void currenPlayerPlays() {

  for (int i = 0; i < 3; i++) {
   playturns(i);
  }
  System.out.println("Updating Score");
  while (!updatePlayerScore());
 }

 private boolean updatePlayerScore() {
  display.printMessage("Select a category for this roll");
  int category = display.waitForPlayerToSelectCategory();
  if (category == UPPER_SCORE || category == LOWER_SCORE || category == TOTAL ||
   isCategoryAlreadyPlayed(category, currentPlayer)) {
   return false;
  }
  HashMap < Integer, Integer > map;
  map = setMap();
  int score = calculateScore(category, map);
  display.updateScorecard(category, currentPlayer, score);

  //Update upperScore and LowerScore
  if (category >= ONES && category <= SIXES) {
   upperScore[currentPlayer - 1] += score;
  }

  if (category >= THREE_OF_A_KIND && category <= CHANCE) {
   lowerScore[currentPlayer - 1] += score;
  }

  //Add to categories already played
  addCategoryToPlayedList(category);

  return true;
 }
 private void addCategoryToPlayedList(int category) {
  if (playedCategories.containsKey(category)) {
   playedCategories.put(category, playedCategories.get(category).concat("|" + playerNames[currentPlayer - 1]));
  } else {
   playedCategories.put(category, playerNames[currentPlayer - 1]);
  }

 }

 private boolean isCategoryAlreadyPlayed(int category, int currentPlayer) {
  if (!playedCategories.containsKey(category)) {
   return false;
  } else if (playedCategories.get(category).indexOf(playerNames[currentPlayer - 1]) == -1) {
   return false;
  }
  return true;
 }

 /**
  * Creates a hash map where key could be any value of the dice  [1,6]
  * and value is the dice count showing that value
  * @return hash map
  */
 private HashMap < Integer, Integer > setMap() {
  HashMap < Integer, Integer > map = new HashMap < Integer, Integer > ();
  for (int i = 0; i < N_DICE; i++) {
   if (map.containsKey(dice[i])) {
    map.put(dice[i], map.get(dice[i]) + 1);
   } else {
    map.put(dice[i], 1);
   }
  }
  return map;
 }

 private int calculateScore(int category, HashMap < Integer, Integer > map) {
  if (category >= ONES && category <= SIXES) {
   if (map.containsKey(category)) {
    return category * map.get(category);
   }
  } else if ((category == THREE_OF_A_KIND && map.containsValue(THREES)) ||
   (category == FOUR_OF_A_KIND && map.containsValue(FOURS)) || (category == CHANCE)) {
   return sumOfDice();
  } else if (category == FULL_HOUSE && isFullHouse(map)) {
   return 25;
  } else if (category == SMALL_STRAIGHT && isSmallStraight(map)) {
   return 30;
  } else if (category == LARGE_STRAIGHT && isLargeStraight(map)) {
   return 40;
  } else if (category == YAHTZEE && isYahtzee(map)) {
   return 50;
  }

  return 0;
 }

 private boolean isYahtzee(HashMap < Integer, Integer > map) {
  return map.containsValue(FIVES);

 }

 private boolean isLargeStraight(HashMap < Integer, Integer > map) {
  if (map.containsKey(1) && map.containsKey(2) &&
   map.containsKey(3) && map.containsKey(4) && map.containsKey(5)) {
   return true;
  } else if (map.containsKey(2) &&
   map.containsKey(3) && map.containsKey(4) && map.containsKey(5) && map.containsKey(6)) {
   return true;
  }
  return false;
 }

 private boolean isSmallStraight(HashMap < Integer, Integer > map) {
  if (map.containsKey(1) && map.containsKey(2) &&
   map.containsKey(3) && map.containsKey(4)) {
   return true;
  } else if (map.containsKey(2) && map.containsKey(3) &&
   map.containsKey(4) && map.containsKey(5)) {
   return true;
  } else if (map.containsKey(3) && map.containsKey(4) &&
   map.containsKey(5) && map.containsKey(6)) {
   return true;
  }
  return false;
 }
 private boolean isFullHouse(HashMap < Integer, Integer > map) {
  return map.containsValue(THREES) && map.containsValue(TWOS);
 }

 /**
  * @return sum of values of dice
  */
 private int sumOfDice() {
  int total = 0;
  for (int i = 0; i < N_DICE; i++) {
   total += dice[i];
  }
  return total;
 }

 private void playturns(int turnNumber) {
  if (turnNumber == 0) {
   display.printMessage(playerNames[currentPlayer - 1] +
    "'s turn! Click \"Roll Dice \" button to roll the dice");
   display.waitForPlayerToClickRoll(currentPlayer);
   dice = rollAllDice();
   display.displayDice(dice);
  } else {
   display.printMessage("Selecr the dice you wish to re-roll and click \"roll Again\"");
   display.waitForPlayerToSelectDice();
   rollSelectedDice();
   display.displayDice(dice);
  }

 }

 private void rollSelectedDice() {
  for (int i = 0; i < N_DICE; i++) {
   if (display.isDieSelected(i)) {
    dice[i] = rgen.nextInt(1, 6);
   }
  }

 }

 private int[] rollAllDice() {
  int[] vals = new int[N_DICE];
  for (int i = 0; i < N_DICE; i++) {
   vals[i] = rgen.nextInt(1, 6);
  }
  return vals;
 }

 /* Private instance variables */
 private int nPlayers;
 private String[] playerNames;
 private YahtzeeDisplay display;
 private RandomGenerator rgen = new RandomGenerator();
 private int currentPlayer;
 private int[] dice;
 private int[] upperScore;
 private int[] lowerScore;
 private int[] totalScore;
 private HashMap < Integer, String > playedCategories;


}
