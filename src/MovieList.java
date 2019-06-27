import java.io.File;
import java.util.Scanner;

public class MovieList {

    //Method that returns a random int number between 1 nad the number of lines
    private int randomMovie (int numberOfLines) {
        double randomNumber = Math.random() * numberOfLines;
        randomNumber = randomNumber + 1;
        return (int) randomNumber;
    }

        /* Method that creates a new String array with all the string elements copied
        from a current array plus a new string element added at the end */
    private String[] addNewElement(String[] currentArray, String newElement) {
        String[] newArray = new String[currentArray.length + 1];
        System.arraycopy(currentArray, 0, newArray, 0, currentArray.length);
        newArray[currentArray.length] = newElement;
        return newArray;
    }

    // Method that chose a random movie's title from a movie list
    public String chosenTitle() {
        String title = "";
        String[] allTitles = {" "}; // String array containing movies' titles
        int lineCount = 0;

        try {
            File file = new File("movies.txt");
            Scanner scanner = new Scanner(file);       // new scanner to read titles from the movie's list

            while (scanner.hasNextLine()) {
                allTitles = addNewElement(allTitles, scanner.nextLine()); // adds new title to allTitles
                lineCount++; // updates a number of lines in the movies' list
            }
            scanner.close();

            // using the method randomMovie with an argument of the lines counted from movies' list
            int lineNumber = randomMovie(lineCount);
            title = allTitles[lineNumber];        // chooses random title from allTitles
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return title;
    }
}
