package game.player;

public class HumanPlayer extends Player {

    private UserInputLoader userInputLoader;

    public HumanPlayer(char sign, char[][] board, UserInputLoader userInputLoader) {
        super(sign, board);
        this.userInputLoader = userInputLoader;
    }

    @Override
    public void makeMove() {
        int[] coordinates = userInputLoader.getUserCoordinates();
        set(coordinates[0], coordinates[1], getSign());
    }

}
