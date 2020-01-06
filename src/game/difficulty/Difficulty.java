package game.difficulty;

import game.player.ComputerPlayer;
import game.player.Player;

public interface Difficulty {

    void makeComputerMove(ComputerPlayer player);

    boolean isMaximizng();
}
