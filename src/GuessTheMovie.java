public class GuessTheMovie {
    public static void main(String [] args) {
        Game game = new Game();
        while (!game.endGame()) {
            game.checkLetter();
            game.showMessages();
        }
    }
}
