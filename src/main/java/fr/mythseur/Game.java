package fr.mythseur;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class Game {
    int day;
    int nutrients;
    List<Cell> board;
    List<Action> possibleActions;
    List<Tree> trees;
    int mySun, opponentSun;
    int myScore, opponentScore;
    boolean opponentIsWaiting;

    public Game() {
        board = new ArrayList<>();
        possibleActions = new ArrayList<>();
        trees = new ArrayList<>();
    }

    Action getNextAction() {
        Optional<Action> max = possibleActions.stream().filter(action -> action.type == EAction.COMPLETE).max(Comparator.comparingInt(action -> board.get(action.targetCellIdx).richess));
        return max.orElseGet(() -> possibleActions.get(0));
    }

}
