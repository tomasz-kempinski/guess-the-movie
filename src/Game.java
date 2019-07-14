import java.util.Scanner;

public class Game extends MovieList { // Game class uses method created in MovieList class

    // Fields
    private String movieTitle;
    private String titleToGuess;
    private String wrongLetters;
    private int wrongGuesses;
    private boolean win;
    boolean gameOver = false;

    // Game constructor

    Game(){
        movieTitle = "";
        wrongLetters = " ";
        wrongGuesses = 0;

        movieTitle = chosenTitle();
        movieTitle = movieTitle.trim();
        movieTitle = movieTitle.toLowerCase();
        titleToGuess = movieTitle.replaceAll("[a-z0-9]", "_");
        showMessages();
    }

    // Method checking for the game ending conditions
    boolean endGame() {
        gameOver = false;
        if (wrongGuesses == 10) { // If the limit of wrong guesses is reached then the game is lost
            gameOver = true;
        }
        if (win) { // If the movie's title was guessed then the game is won
            gameOver = true;
        }
        return gameOver;
    }

    // Method that checks letters guessed by a player in the movie's title
    void checkLetter() {
        char letter;
        String typedLetters;
        boolean triedLetter = false;

        Scanner playersInput = null;
        try {
            playersInput = new Scanner(System.in);  // New scanner for taking input letters form a player
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (playersInput == null) throw new AssertionError();
        typedLetters = playersInput.next().toLowerCase();
        letter = typedLetters.charAt(0);

        for (int i = 0; i < movieTitle.length(); i++) {
            if (movieTitle.charAt(i) == letter) {  // Replaces all "_" with correctly guessed letter
                if (titleToGuess.charAt(i) == letter && !triedLetter) { // Checks if the letter has been already guessed
                    System.out.println("You have already guessed the letter '" + letter + "'.");
                }
                titleToGuess = replaceCharAt(titleToGuess, i, letter);
                triedLetter = true;
            }
        }
        if (triedLetter) { // Checks winning condition when the letter was correctly guessed
            win = true;
            for (int i = 0; i < movieTitle.length(); i++) {
                if (titleToGuess.charAt(i) == '_') { // If there are any "_" left then there is no win
                    win = false;
                }
            }
        } else {
            // The guessed letter is incorrect
            for (int i = 0; i < wrongLetters.length(); i++) {  // Checks if the letter has been already tried
                if (wrongLetters.charAt(i) == letter) {
                    System.out.println("You have already tried the letter '" + letter + "' and it is incorrect.");
                    triedLetter = true;
                    break;
                }
            }
            if (!triedLetter) { // If the incorrect letter wasn't repeated then it's being added to the wrongLetters
                wrongLetters = wrongLetters + letter + " ";
                wrongGuesses++; // Updates number of wrong guesses
            }
        }
    }

    // Method to replace character "_" with a letter when it is guessed correctly
    private String replaceCharAt(String str, int position, char replaceChar){
        if (str == null){
            return str;
        }else if (position < 0 || position >= str.length()){ // Checking for out of bounds condition
            return str;
        }
        char[] chars = str.toCharArray(); // stores string str in an array of characters
        chars[position] = replaceChar;     // replaces char at position with character replaceChar
        return String.valueOf(chars);       // returns a String containing characters from char array
    }

    // Method that displays messages accordingly to the current game status
    void showMessages() {
        if (wrongGuesses == 10) { // Limit od wrong guesses is reached and the game is lost
            System.out.println("\nYou have guessed only: '" + titleToGuess + "'");
            System.out.println("\nGAME OVER! YOU LOSE!");
        } else if (win) { // Player wins the game
            System.out.println("\nThe movie's title '" + titleToGuess.toUpperCase() + "' is correct.");
            System.out.println("\no" +
                    "CONGRATULATIONS! YOU WIN!");
        } else { // Game still goes on
            System.out.println("\nYou are guessing: " + titleToGuess);
            System.out.println("You have guessed (" + wrongGuesses + ") wrong letters:" + wrongLetters);
            System.out.println("You have " + (10 - wrongGuesses) + " guesses left.");
            System.out.print("\nGuess a letter: ");
        }

    }
}
