package game.difficulty;

import game.Main;

//This class is adapted from other repository, therefore it doesn't match Strategy Design Pattern
class RowAnalyzer {

    static final String TYPE_COL = "col";
    static final String TYPE_ROW = "row";
    static final String TYPE_DIAG_TOP_LEFT = "diagtopleft";
    static final String TYPE_DIAG_TOP_RIGHT = "diagtopright";
    char[] row;
    String type;
    int matrixRowPointer;

    RowAnalyzer(char[] row, String type, int matrixRowPointer) {
        this.row = row;
        this.type = type;
        this.matrixRowPointer = matrixRowPointer;
    }

    public RowAnalyzer(char[] row, String type) {
        this.row = row;
        this.type = type;
    }

    static public RowAnalyzer col(char[][] board, int y) {

        char[] row = new char[Main.BOARD_SIZE];
        for (int x = 0; x < row.length; x++) {
            row[x] = board[x][y];
        }
        return new RowAnalyzer(row, TYPE_COL, y);
    }

    static public RowAnalyzer row(char[][] board, int x) {
        return new RowAnalyzer(board[x], TYPE_ROW, x);
    }

    static public RowAnalyzer diagonalLeftTop(char[][] board) {
        char[] row = new char[Main.BOARD_SIZE];
        for (int x = 0; x <  row.length; x++) {
            row[x] = board[x][x];
        }
        return new RowAnalyzer(row, TYPE_DIAG_TOP_LEFT);
    }

    static public RowAnalyzer diagonalRightTop(char[][] board) {
        char[] row = new char[Main.BOARD_SIZE];
        for (int x = row.length- 1; x >= 0; x--) {
            row[x] = board[x][row.length - x - 1];
        }
        return new RowAnalyzer(row, TYPE_DIAG_TOP_RIGHT);
    }

    public int[] getOneLeftOrNull(char symbol) {
        int symbolCounter = 0;
        int emptyCounter = 0;
        int[] position = new int[2];
        for (int i = 0; i < row.length; i++) {
            if (row[i] == symbol) {
                symbolCounter++;
            } else if (row[i] == ' ') {
                emptyCounter++;
                switch (type) {
                    case TYPE_COL:
                        position[0] = i;
                        position[1] = matrixRowPointer;
                        break;
                    case TYPE_ROW:
                        position[0] = matrixRowPointer;
                        position[1] = i;
                        break;
                    case TYPE_DIAG_TOP_LEFT:
                        position[0] = i;
                        position[1] = position[0];
                        break;
                    case TYPE_DIAG_TOP_RIGHT:
                        position[0] = i;
                        position[1] = row.length - i - 1;
                }
            }
        }
        if ((row.length - symbolCounter) == 1 && emptyCounter == 1) {
            return position;
        }
        return null;
    }
}
