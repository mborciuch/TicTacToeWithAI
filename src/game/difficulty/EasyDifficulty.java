package game.difficulty;

import game.Main;
import game.player.ComputerPlayer;
import game.player.Player;

import java.util.Random;

public class EasyDifficulty implements Difficulty {

    char[][] board;
    Random random = new Random();

    public EasyDifficulty(char[][] board) {
        this.board = board;
    }

    @Override
    public void makeComputerMove(ComputerPlayer player) {
        System.out.println("Making move level \"easy\"");
        boolean isMoved = false;
        int i;
        int j;
        do {
            j = random.nextInt(Main.BOARD_SIZE);
            i = random.nextInt(Main.BOARD_SIZE);

            if (isFieldEmpty(i, j)) {
                player.set(i, j, player.getSign());
                isMoved = true;
            }
        } while (!isMoved);
    }

    @Override
    public boolean isMaximizng() {
        return true;
    }

    protected boolean isFieldEmpty( int i, int j){
        return board[i][j] == ' ';
    }
}
