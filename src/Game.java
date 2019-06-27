import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {

    private String movieTitle;
    private String titleToGuess;
    private String wrongLetters;
    private int wrongGuesses;
    private boolean win;
    private int lineNumber;
    boolean gameOver = false;

    //Constructor

    Game () {
        movieTitle = "";
        wrongLetters = " ";
        wrongGuesses = 0;
        lineNumber = 0;

        movieTitle = chosenTitle();
        movieTitle = movieTitle.trim();
        movieTitle = movieTitle.toLowerCase();
        titleToGuess = movieTitle.replaceAll("[a-z0-9]", "_");
        showMessage();
    }

    private int numberOfLines() {
        int lineCount = 0;

        try{
            File file = new File("movies.txt");
            Scanner scanner1 = new Scanner(file);

            while (scanner1.hasNextLine()) {
                lineCount++;
            }
            scanner1.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File not found.");
        }
        return lineCount;
    }

    private int randomMovie() {
        double randomNumber = Math.random() * numberOfLines() + 1;
        return (int) randomNumber;
    }

    private String[] addNewElement(String[] currentArray, String newElement) {
        String[] newArray = new String[currentArray.length + 1];
        System.arraycopy(currentArray, 0, newArray, 0, currentArray.length);
        newArray[currentArray.length] = newElement;
        return newArray;
    }

    private String chosenTitle() {
        String title;
        String [] titleList = {" "};

        try {
            File list = new File("movies.txt");
            Scanner scanner2 = new Scanner(list);

            while (scanner2.hasNextLine()) {
                titleList = addNewElement(titleList, scanner2.nextLine());
            }
            scanner2.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        lineNumber = randomMovie();
        title = titleList[lineNumber];
        return title;
    }

    public void checkLetter() {
        char letter;
        String keepGuessing;
        boolean foundLetter = false;

        Scanner playersInput = new Scanner(System.in);
        playersInput = null;

        keepGuessing = playersInput.next().toLowerCase();
        letter = keepGuessing.charAt(0);

        for (int i = 0; i < movieTitle.length(); i++) {
            if (movieTitle.charAt(i) == letter) {
                if (titleToGuess.charAt(i) == letter && !foundLetter) {
                    System.out.println("The letter '" + letter + "' has been already guessed.");
                }
                titleToGuess = replaceCharAt(titleToGuess, i, letter);
                foundLetter = true;
            }
        }
        if (foundLetter) {
            win = true;
            for (int i = 0; i < movieTitle.length(); i++) {
                if (titleToGuess.charAt(i) == '_') {
                    win = false;
                }
            }
        } else {
            for (int i = 0; i < wrongLetters.length(); i++) {
                if (wrongLetters.charAt(i) == letter) {
                    System.out.println("The letter '" + letter + "'has been already guessed incorrectly.");
                    foundLetter = true;
                    break;
                }
            }
            if (!foundLetter) {
                wrongLetters = wrongLetters + letter + " ";
                wrongGuesses++;
            }
        }
    }

    boolean endGame(){
        gameOver = false;
        if (wrongGuesses == 10){
            gameOver = true;
        }else if (win){
            gameOver = true;
        }
        return gameOver;
    }

    private String replaceCharAt(String str, int position, char replaceChar){
        if (str == null){
            return str;
        }else if (position < 0 || position >= str.length()){
            return str;
        }
        char[] chars = str.toCharArray();
        chars[position] = replaceChar;
        return String.valueOf(chars);
    }

    public void  showMessage(){
        if (wrongGuesses == 10){
            System.out.println("You have guessed only: '" + titleToGuess + "'");
            System.out.println("Game Over! You lose.");
        }else  if(win){
            System.out.println("Congratulations! You win!");
            System.out.println("The movie title '" + titleToGuess.toUpperCase() + "'is correct.");
        }else {
            System.out.println("You are guessing: " + titleToGuess);
            System.out.println("You have guessed (" + wrongGuesses + ") wrong letters:" + wrongLetters);
            System.out.println("Guess a letter: ");
        }
    }
}