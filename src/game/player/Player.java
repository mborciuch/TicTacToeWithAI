package game.player;

abstract public class Player {

    char[][] board;
    private char sign;

    Player(char sign, char[][] board) {
        this.sign = sign;
        this.board = board;
    }

    public char getSign() {
        return sign;
    }

    public abstract void makeMove();

    public void set(int x, int y, char sign){
        board[x][y] = sign;
    }

}
