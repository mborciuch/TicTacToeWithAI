package game.player;

import game.difficulty.Difficulty;

public class ComputerPlayer extends Player {

    private Difficulty difficulty;

    public ComputerPlayer(char sign, char[][] board, Difficulty difficulty) {
        super(sign, board);
        this.difficulty = difficulty;
    }

    public void makeMove() {
        difficulty.makeComputerMove(this);
    }

    @Override
    public void set(int x, int y, char sign) {
        board[x][y] = sign;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
