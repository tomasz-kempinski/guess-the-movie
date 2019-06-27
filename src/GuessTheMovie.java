public class GuessTheMovie {
    public static void main(String [] args) {
        System.out.println("Welcome to the 'Guess The Movie' game.");
        System.out.println("Try to guess the movie's title within 10 tries.");
        Game game = new Game();
        while (!game.endGame()) {
            game.checkLetter();
            game.showMessages();
        }
    }
}
