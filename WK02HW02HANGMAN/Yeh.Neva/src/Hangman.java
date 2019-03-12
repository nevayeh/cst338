/**
 * Author: Neva Yeh (CST 338 S3)
 * Title: Hangman.java
 * Date: February 1, 2019
 * Abstract: This simulates a game of Hangman. The user enters the word that will be guessed.
 *           They will have 4 "lives" to incorrectly guess and can choose to either guess or
 *           use a hint. Using a hunt will reveal the first undiscovered letter, but will take
 *           away a "life" in return.
 *
 *           In my verison, the user is able to enter any message, but keep in mind that any
 *           special characters will be removed. (Ex: Dave's House becomes Daves House)
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author: Neva Yeh
 */
public class Hangman {

    private StringBuffer word; // "Answer key"
    private ArrayList<String> foundWord = new ArrayList<>(); // What the player sees
    private int wordLength;
    private int guessesLeft = 4;

    /**
     * Constructor for class
     * Sets all values accordingly
     * Sanitizes initial input and stores _'s and #'s for letters and spaces, respectively
     * (No default constructor because it is always given a parameter in this case)
     *
     * @param word The word that the user inputs to be guessed
     */
    public Hangman(String word) {
        this.word = new StringBuffer(word.toUpperCase()); // Stores as all uppercase letters
        wordLength = word.length();

        // Sanitizes input and updates word length if sanitized
        setWord(sanitizeChars(this.word));
        setWordLength(this.word.length());

        // "Initializes" what the player sees, holds respective values in an ArrayList
        for (int i = 0; i < wordLength; i++) {
            if (this.word.charAt(i) == ' ') {
                foundWord.add("#");
            }
            else {
                foundWord.add("_");
            }
        }
    }

    /**
     * (Helper function)
     * Removes any special characters from the user's inital inputted word
     * Checked characters: those with ASCII values: 33-47, 58-64, 91-96, 123-126
     *
     * @param wordToSanitize The word that the user entered in the beginning
     * @return The sanitized word StringBuffer (ex: it's easy gets returned as "its easy")
     */
    private StringBuffer sanitizeChars(StringBuffer wordToSanitize) {
        ArrayList<String> indexesToDelete = new ArrayList<>();

        for (int i = 0; i < wordToSanitize.length(); i++) {
            int ascii = (int) wordToSanitize.charAt(i);
            if ((ascii >= 33 && ascii <= 47) ||
                    (ascii >= 58 && ascii <= 64) ||
                    (ascii >= 91 && ascii <= 96) ||
                    (ascii >= 123 && ascii <= 126)) {
                indexesToDelete.add(Integer.toString(i));
            }
        }

        // Sanitizes from the back of the StringBuffer so as to not affects the
        // index of proceeding deletions
        for (int i = indexesToDelete.size(); i > 0; i--) {
            wordToSanitize.deleteCharAt(Integer.parseInt(indexesToDelete.get(i - 1)));
        }

        return wordToSanitize;
    }

    /**
     * Checks if the user has guessed the entire word
     *
     * @return Boolean value based on whether the word was solved or not
     */
    public boolean checkWin() {
        ArrayList<String> wordShown = getFoundWord();
        int letterCount = 0;

        for (int i = 0; i < wordShown.size(); i++) {
            if (!wordShown.get(i).equals("_") && !wordShown.get(i).equals("#")) {
                letterCount++;
            }
        }

        if (letterCount == wordShown.size()) {
            return true;
        }
        return false;
    }

    /**
     * Creates a string that the user sees
     * (Ex: word is "HOME" but user only guessed "E" --> "_ _ _ E"
     *
     * @return String value of what the user has solved so far
     */
    public String wordToDisplay() {
        String result = "";
        for (int i = 0; i < wordLength; i++) {
            result += foundWord.get(i) + " ";
        }
        return result;
    }

    /**
     * Checks if the user's guess is within the word
     *
     * @param letter The user's guess
     * @return Boolean value based on whether the user's guess is contained
     *         within the word or not
     */
    public boolean letterInWord(String letter) {
        if (word.toString().contains(letter)) {
            return true;
        }
        return false;
    }

    /**
     * Updates the word after confirming that the user has guessed correctly
     * Also called after a hint is used
     *
     * @param letter The user's guess / the letter given from the hint
     */
    public void updateWord(String letter) {
        StringBuffer wordAnswer = getWord();
        ArrayList<String> wordShown = getFoundWord();

        ArrayList<String> indexesToUpdate = new ArrayList<>();

        for (int i = 0; i < wordShown.size(); i++) {
            if (wordAnswer.charAt(i) == letter.charAt(0)) {
                indexesToUpdate.add(Integer.toString(i));
            }
        }

        for (int i = indexesToUpdate.size(); i > 0; i--) {
            int updateIndex = Integer.parseInt(indexesToUpdate.get(i - 1));
            wordShown.add(updateIndex, letter);
            wordShown.remove(updateIndex + 1);
        }
    }

    /**
     * Simulates a hint being used
     * Will compute and display the hint, update the word, and take away a "life"
     */
    public void useHint() {
        System.out.println("OK! The hint is " + getHintLetter());
        updateWord(getHintLetter());
        decreaseRemainingGuesses();
    }

    /**
     * (Helper function)
     * Finds out which letter should be returned when the user asks for a hint
     *
     * @return String containing the first yet-to-be-discovered letter of the word
     */
    private String getHintLetter() {
        StringBuffer wordAnswer = getWord();
        ArrayList<String> wordShown = getFoundWord();

        for (int i = 0; i < wordShown.size(); i++) {
            if (wordShown.get(i) == "_") {
                return wordAnswer.charAt(i) + "";
            }
        }

        return "";
    }

    /**
     * Creates the string of the answer
     * Called after both winning and losing
     *
     * @return String containing word(s) initially entered at beginning
     */
    public String displayAnswer() {
        String word = getWord().toString();
        String result = "";

        for (int i = 0; i < getWordLength(); i++) {
            result += word.charAt(i) + " ";
        }

        return result;
    }

    /**
     * Decreases the number of remaining guesses
     * Called after incorrectly guessing and using hint
     */
    public void decreaseRemainingGuesses() {
        setGuessesLeft(getGuessesLeft() - 1);
    }

    /** Returns word */
    public StringBuffer getWord() {
        return word;
    }

    /** Sets word */
    public void setWord(StringBuffer word) {
        this.word = word;
    }

    /** Returns foundWord */
    public ArrayList<String> getFoundWord() {
        return foundWord;
    }

    /** Returns wordLength */
    public int getWordLength() {
        return wordLength;
    }

    /** Sets wordLength */
    public void setWordLength(int wordLength) {
        this.wordLength = wordLength;
    }

    /** Returns guessesLeft */
    public int getGuessesLeft() {
        return guessesLeft;
    }

    /** Sets guessesLeft */
    public void setGuessesLeft(int guessesLeft) {
        this.guessesLeft = guessesLeft;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("--------- Welcome to Hangman ---------");
        System.out.print("\nEnter a word: ");
        String word = in.nextLine();

        Hangman hangman = new Hangman(word);

        while (hangman.getGuessesLeft() >= 0) {
            // Checks if out of turns
            // If so, displays lose message and ends program
            if (hangman.getGuessesLeft() == 0) {
                System.out.println("\nYou failed. The word was " + hangman.displayAnswer());
                System.exit(1);
            }
            else {
                // Checks for win first
                // If so, displays win message and ends program
                if (hangman.checkWin()) {
                    System.out.println("Congratulations! The word was " + hangman.displayAnswer());
                    System.exit(1);
                }
                else {
                    System.out.println("\nSo far, the word is: " + hangman.wordToDisplay());
                    System.out.println("You have " + hangman.getGuessesLeft() + " incorrect guesses left.");
                    System.out.print("Enter either 1 for guessing or 2 for a hint: ");
                    String userChoice = in.nextLine();

                    // Input validation
                    // Making sure "1" or "2"
                    while (!userChoice.equals("1") && !userChoice.equals("2")) {
                        System.out.println("Incorrect input.");
                        System.out.print("Enter either 1 for guessing or 2 for a hint: ");
                        userChoice = in.nextLine();
                    }
                    // -------- User chooses to guess --------
                    if (userChoice.equals("1")) {
                        System.out.print("Enter your guess: ");
                        String userGuess = in.nextLine().toUpperCase();

                        // Input Validation
                        // Making sure capital letter between A and Z via ASCII values
                        while (!(userGuess.length() == 1 &&
                                (int) userGuess.charAt(0) >= 65 &&
                                (int) userGuess.charAt(0) <= 90)) {
                            System.out.println("Incorrect input.");
                            System.out.print("Enter your guess: ");
                            userGuess = in.nextLine().toUpperCase();
                        }

                        if (hangman.letterInWord(userGuess)) {
                            System.out.println("That's right! " + userGuess + "  is in the word.");
                            hangman.updateWord(userGuess);

                        } else {
                            System.out.println("Sorry, " + userGuess + "  isn't in the word.");
                            hangman.decreaseRemainingGuesses();
                        }
                    }
                    // -------- User chooses to see a hint --------
                    else {
                        hangman.useHint();
                    }
                }
            }
        }
    }
}
