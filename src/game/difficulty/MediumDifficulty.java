package game.difficulty;

import game.Main;
import game.player.ComputerPlayer;
import game.player.Player;

import java.util.Random;

public class MediumDifficulty extends EasyDifficulty{

    public MediumDifficulty(char[][] board) {
        super(board);
    }

    @Override
    public void makeComputerMove(ComputerPlayer player) {

        RowAnalyzer rowAnalyzer;
        char sign = player.getSign();
        char oppositeSign = sign == 'X' ? 'O' : 'X';

        for(int x = 0; x < Main.BOARD_SIZE; x++){
            rowAnalyzer = RowAnalyzer.row(board, x);
            if(requestLast(rowAnalyzer,player, sign, oppositeSign)){
                return;
            }

        }
        for (int y = 0; y < Main.BOARD_SIZE; y++){
            rowAnalyzer = RowAnalyzer.col(board, y);
            if(requestLast(rowAnalyzer,player, sign, oppositeSign)){
                return;
            }
        }

        rowAnalyzer = RowAnalyzer.diagonalLeftTop(board);
        if (requestLast(rowAnalyzer, player, sign, oppositeSign)){
            return;
        }

        rowAnalyzer = RowAnalyzer.diagonalRightTop(board);
        if (requestLast(rowAnalyzer, player, sign, oppositeSign)){
            return;
        }
        super.makeComputerMove(player);
    }

    private boolean requestLast(RowAnalyzer rowAnalyzer, Player player, char sign, char oppositeSign) {
        int[] coordinates = rowAnalyzer.getOneLeftOrNull(sign);
        if (coordinates != null) {
            player.set(coordinates[0], coordinates[1], sign);
            return true;
        }
        coordinates = rowAnalyzer.getOneLeftOrNull(oppositeSign);
        if (coordinates != null) {
            player.set(coordinates[0], coordinates[1], sign);
            return true;
        }
        return false;
    }
}
