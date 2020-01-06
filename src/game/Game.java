package game;

import game.difficulty.EasyDifficulty;
import game.difficulty.HardDifficulty;
import game.difficulty.MediumDifficulty;
import game.player.ComputerPlayer;
import game.player.HumanPlayer;
import game.player.Player;
import game.player.UserInputLoader;

import java.util.Arrays;
import java.util.Scanner;

public class Game {

    private char[][] board;
    private Scanner scanner;
    private TicTacToeStateEvaluator ticTacToeStateEvaluator;
    private UserInputLoader userInputLoader;

    private Player playerOne;
    private Player playerTwo;

    public Game(Scanner scanner, TicTacToeStateEvaluator ticTacToeStateEvaluator, UserInputLoader userInputLoader) {
        this.scanner = scanner;
        this.ticTacToeStateEvaluator = ticTacToeStateEvaluator;
        this.userInputLoader = userInputLoader;
    }


    public void start(String[] args) {

        String state;
        prepareNewRound(args);
        state = TicTacToeStateEvaluator.getState(board);
        printState(state);

        while (true) {
            playerOne.makeMove();
            state = TicTacToeStateEvaluator.getState(board);
            printState(state);
            if (TicTacToeStateEvaluator.isGameFinished(state)) {
                break;
            }
            playerTwo.makeMove();
            state = TicTacToeStateEvaluator.getState(board);
            printState(state);
            if (TicTacToeStateEvaluator.isGameFinished(state)) {
                break;
            }
        }
    }

    private void prepareNewRound(String[] params) {

        board = prepareEmptyBoard();
        userInputLoader.setBoard(board);

        String playerOneType = params[1];
        String playerTwoType = params[2];

        switch (playerOneType) {
            case "user":
                playerOne = new HumanPlayer('X', board, userInputLoader);
                break;
            case "easy":
                playerOne = new ComputerPlayer('X', board, new EasyDifficulty(board));
                break;
            case "medium":
                playerOne = new ComputerPlayer('X', board, new MediumDifficulty(board));
                break;
            case "hard" :
                playerOne = new ComputerPlayer('X', board, new HardDifficulty(board, true));
                break;
        }
        switch (playerTwoType) {
            case "user":
                playerTwo = new HumanPlayer('O', board, userInputLoader);
                break;
            case "easy":
                playerTwo = new ComputerPlayer('O', board, new EasyDifficulty(board));
                break;
            case "medium":
                playerTwo = new ComputerPlayer('O', board, new MediumDifficulty(board));
                break;
            case "hard" :
                playerTwo = new ComputerPlayer('O', board, new HardDifficulty(board, false));
                break;
        }
    }

    private void printState(String state) {
        System.out.println("---------");
        for (char[] boardRow : board) {
            System.out.print("| ");
            for (char boardElement : boardRow) {
                System.out.print(boardElement + " ");
            }
            System.out.print("| \n");
        }
        System.out.println("---------");
        System.out.println(state);
    }

    private char[][] prepareEmptyBoard() {
        char[][] board = new char[Main.BOARD_SIZE][Main.BOARD_SIZE];
        for (char[] boardRow : board) {
            Arrays.fill(boardRow, ' ');
        }
        return board;
    }

}
