package fr.mythseur;

import java.util.ArrayList;
import java.util.List;

class Game {
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
        // TODO: write your algorithm here
        return possibleActions.get(0);
    }

}
