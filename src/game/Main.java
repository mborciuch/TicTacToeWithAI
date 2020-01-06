package game;

import game.Game;
import game.TicTacToeStateEvaluator;
import game.player.UserInputLoader;

import java.util.Scanner;

public class Main {

    public static final int BOARD_SIZE = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToeStateEvaluator ticTacToeStateEvaluator = new TicTacToeStateEvaluator();
        UserInputLoader userInputLoader = new UserInputLoader();
        Game game = new Game(scanner, ticTacToeStateEvaluator, userInputLoader);
        userInputLoader.setScanner(scanner);

        while (true) {
            System.out.println("Input command:");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            String[] arguments = input.split(" ");
            if (arguments.length == 3) {
                if (arguments[0].equals("start")) {
                    switch (arguments[1]) {
                        case "user":
                        case "easy":
                        case "medium":
                        case "hard":
                            break;
                        default:
                            System.out.println("Bad parameters!");
                    }
                    switch (arguments[2]) {
                        case "user":
                        case "easy":
                        case "medium":
                        case "hard":
                            break;
                        default:
                            System.out.println("Bad parameters!");
                    }
                }
                game.start(arguments);
            } else {
                System.out.println("Bad parameters!");
            }
        }
    }
}

