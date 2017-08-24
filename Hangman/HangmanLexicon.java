/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */


import acm.program.*;
import acm.util.*;

import java.io.*;
import java.util.*;


public class HangmanLexicon extends Program {

 private static final String FILENAME = "HangmanLexicon.txt";

 /** Constructor of HangmanLexicon which creates object which has list of words */
 public HangmanLexicon() {
  wordList = new ArrayList < String > ();
  BufferedReader rd = openFile();
  while (true) {
   String line = "";

   try {
    line = rd.readLine();
   } catch (IOException ex) {
    throw new ErrorException(ex);
   }
   if (line == null) break;
   //println("the word is line"+ line);
   wordList.add(line);
  }
  //readFile(rd);
 }

 /** Returns the number of words in the lexicon. */
 public int getWordCount() {
  return wordList.size();
 }

 /** Returns the word at the specified index. */
 public String getWord(int index) {
  return wordList.get(index);
 }

 /** opens the file of word list */
 private BufferedReader openFile() {
  BufferedReader r = null;
  while (r == null) {
   try {
    r = new BufferedReader(new FileReader(FILENAME));
   } catch (IOException ex) {
    throw new ErrorException(ex);
   }
  }
  return r;
 }


 /** instance variable to store the words from the file to ArrayList */

 private ArrayList < String > wordList;
}
