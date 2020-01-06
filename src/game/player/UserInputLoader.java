package game.player;

import java.util.Scanner;

public class UserInputLoader {

    Scanner scanner;
    private char[][] board;

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public int[] getUserCoordinates() {

        boolean isReady = false;
        String input;
        int x = 0;
        int y = 0;
        do {
            System.out.println("Enter the coordinates:");
            input = scanner.nextLine();
            if (isInputValid(input)) {
                x = Character.getNumericValue(input.charAt(0));
                y = Character.getNumericValue(input.charAt(2));
                int[] coordinatesToCheck = {x, y};
                if (isBoardCellEmpty(coordinatesToCheck)) {
                    isReady = true;
                }
            }
        } while (!isReady);

        int[] coordinatesToConvert = {x, y};
        return convertCoordinates(coordinatesToConvert);
    }

    private int[] convertCoordinates(int[] coordinates) {
        int[] newCoordinates = new int[2];
        newCoordinates[0] = convertToX(coordinates[1]);
        newCoordinates[1] = convertToY(coordinates[0]);
        return newCoordinates;
    }

    private int convertToY(int x) {
        return x - 1;
    }

    private int convertToX(int y) {
        return 3 - y;
    }

    private boolean isInputValid(String input) {

        String x = String.valueOf(input.charAt(0));
        String y = String.valueOf(input.charAt(2));

        if (!isNumeric(x)) {
            System.out.println("You should enter numbers!");
            return false;
        }
        if (!isNumeric(y)) {
            System.out.println("You should enter numbers!");
            return false;
        }

        int xNumber = Integer.valueOf(x);
        int yNumber = Integer.valueOf(y);

        if (xNumber < 1 || xNumber > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }

        if (yNumber < 1 || yNumber > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        return true;
    }

    private boolean isBoardCellEmpty(int[] coordinatesToCheck) {

        int newX = convertToX(coordinatesToCheck[1]);
        int newY = convertToY(coordinatesToCheck[0]);

        if (board[newX][newY] == ' ') {
            return true;
        } else {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
    }

    private boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
