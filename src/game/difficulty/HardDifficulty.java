package game.difficulty;

import game.Main;
import game.TicTacToeStateEvaluator;
import game.player.ComputerPlayer;
import game.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class HardDifficulty extends MediumDifficulty {

    boolean isMaximizing;
    private Map<String, Integer> scores = new HashMap<>();

    public HardDifficulty(char[][] board, boolean isMaximizing) {
        super(board);
        initScores();
        this.isMaximizing = isMaximizing;
    }

    //fixing choosing best score
    @Override
    public void makeComputerMove(ComputerPlayer firstPlayer) {
        int bestScore;
        BiFunction<Integer, Integer, Boolean> comparator;
        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            comparator = (x, y) -> x > y;
        } else {
            bestScore = Integer.MAX_VALUE;
            comparator = (x, y) -> x < y;
        }

        int score;
        int[] bestMove = new int[2];
        char counterSign = getCounterSign(firstPlayer);
        ComputerPlayer secondPlayer = new ComputerPlayer(counterSign, board, new HardDifficulty(board, !isMaximizing));
        for (int i = 0; i < Main.BOARD_SIZE; i++) {
            for (int j = 0; j < Main.BOARD_SIZE; j++) {
                if (isFieldEmpty(i, j)) {
                    firstPlayer.set(i, j, firstPlayer.getSign());
                    score = minmax(secondPlayer, firstPlayer, 1, board);
                    firstPlayer.set(i, j, ' ');
                    if (comparator.apply(score, bestScore)) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        firstPlayer.set(bestMove[0], bestMove[1], firstPlayer.getSign());
    }

    private int minmax(ComputerPlayer actualPlayer, ComputerPlayer mockPlayer, int depth, char[][] board) {

        String result = TicTacToeStateEvaluator.getState(board);
        if (scores.containsKey(result)) {
            return scores.get(result) -depth;
        }
        int bestScore;

        BiFunction<Integer, Integer, Boolean> comparator;
        if (actualPlayer.getDifficulty().isMaximizng()) {
            bestScore = Integer.MIN_VALUE;
            comparator = (x, y) -> x > y;
        } else {
            bestScore = Integer.MAX_VALUE;
            comparator = (x, y) -> x < y;
        }

        for (int i = 0; i < Main.BOARD_SIZE; i++) {
            for (int j = 0; j < Main.BOARD_SIZE; j++) {
                if (isFieldEmpty(i, j)) {
                    actualPlayer.set(i, j, actualPlayer.getSign());
                    int score = minmax(mockPlayer, actualPlayer, depth + 1, board);
                    actualPlayer.set(i, j, ' ');
                    if (comparator.apply(score, bestScore)) {
                        bestScore = score;
                    }
                }
            }
        }
        return bestScore;
    }

    private char getCounterSign(Player player) {
        if (player.getSign() == 'X') {
            return 'O';
        } else {
            return 'X';
        }
    }

    private void initScores() {
            scores.put("X wins", 10);
            scores.put("O wins", -10);
            scores.put("Draw", 0);
    }

    public boolean isMaximizing() {
        return isMaximizing;
    }
}