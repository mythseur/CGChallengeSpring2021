package fr.mythseur;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class Game {
    public int day;
    public int nutrients;
    public List<Cell> board;
    public List<Action> possibleActions;
    public List<Tree> trees;
    public int mySun, opponentSun;
    public int myScore, opponentScore;
    public boolean opponentIsWaiting;

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
