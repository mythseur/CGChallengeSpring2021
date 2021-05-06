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
        Optional<Action> maxTreeSize3 = possibleActions.stream().filter(action -> action.type == EAction.COMPLETE).max(Comparator.comparingInt(action -> board.get(action.targetCellIdx).richess));
        if (maxTreeSize3.isPresent())
            return maxTreeSize3.get();

        Optional<Action> maxTreeSize2 = possibleActions.stream().filter(action -> action.type == EAction.GROW).filter(action -> trees.stream().filter(tree -> tree.cellIndex == action.targetCellIdx).findFirst().get().size == 2).max(Comparator.comparingInt(action -> board.get(action.targetCellIdx).richess));
        if (maxTreeSize2.isPresent())
            return maxTreeSize2.get();

        Optional<Action> maxTreeSize1 = possibleActions.stream().filter(action -> action.type == EAction.GROW).filter(action -> trees.stream().filter(tree -> tree.cellIndex == action.targetCellIdx).findFirst().get().size == 1).max(Comparator.comparingInt(action -> board.get(action.targetCellIdx).richess));
        return maxTreeSize1.orElseGet(() -> possibleActions.get(0));

    }

}
