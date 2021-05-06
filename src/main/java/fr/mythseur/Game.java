package fr.mythseur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Game {
    public int day;
    public int nutrients;
    public List<Cell> board;
    public List<Action> possibleActions;
    public List<Tree> trees;
    public int mySun, opponentSun;
    public int myScore, opponentScore;
    public boolean opponentIsWaiting;
    public Map<Integer, Integer> shadows;

    public Game() {
        board = new ArrayList<>();
        possibleActions = new ArrayList<>();
        trees = new ArrayList<>();
        shadows = new HashMap<>();
    }

    public Action getNextAction() {
        // TODO: MONTE-CARLO ALEATOIRE, on wait quand plus rien
        // TODO : Remplir la liste sur la base du referee
        return possibleActions.get(0);
    }

    public void computeShadows() {
        shadows.clear();
        trees.forEach(tree -> {
            for (int i = 1; i <= tree.size; i++) {
                Cell neighbor = getNeighbor(tree.cellIndex, day % 6, i);
                shadows.compute(neighbor.index, (key, value) -> value == null ? tree.size : Math.max(value, tree.size));
            }
        });
    }

    private Cell getNeighbor(int cellIndex, int orientation, int dist) {
        Cell currentCell = board.get(cellIndex);

        for (int i = 0; i <= dist; i++) {
            currentCell = board.get(currentCell.neighbours[orientation]);
        }
        return currentCell;
    }
}
