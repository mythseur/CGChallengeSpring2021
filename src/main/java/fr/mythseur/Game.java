package fr.mythseur;

import java.util.*;


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

    public Action getNextAction(long startTime, Game copyGame, boolean firstTurn) {
        Action bestAction = new Action(EAction.WAIT, null, null);
        double bestScore = Double.NEGATIVE_INFINITY;
        while (System.currentTimeMillis() - startTime < (firstTurn ? 995 : 95)) {
            this.copy(copyGame);
            Action currentAction = null;
            for (int i = 0; i < 10; i++) {
                List<Action> possibleMoves = ActionGenerator.getPossibleMoves(copyGame);
                Collections.shuffle(possibleMoves);
                Simulator.doAction(possibleMoves.get(0), copyGame);
                if (i == 0)
                    currentAction = possibleMoves.get(0);
            }

            double score = score(copyGame);

            if (score > bestScore) {
                bestScore = score;
                bestAction = currentAction;
            }

        }
        // TODO: MONTE-CARLO ALEATOIRE, on wait quand plus rien
        // TODO : Remplir la liste sur la base du referee
        return bestAction;
    }

    private double score(Game copyGame) {
        return copyGame.myScore;
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
            if (i > 0)
                currentCell = board.get(currentCell.neighbours[orientation]);
        }
        return currentCell;
    }

    public void copy(Game game) {
        game.day = day;
        game.mySun = mySun;
        game.opponentSun = opponentSun;
        game.myScore = myScore;
        game.opponentScore = opponentScore;
        game.opponentIsWaiting = opponentIsWaiting;
        game.nutrients = nutrients;

        for (int i = 0; i < board.size(); i++) {
            Cell cell = board.get(i);
            if (game.board.isEmpty()) {
                game.board.add(new Cell(cell));
            } else {
                game.board.get(i).update(cell);
            }
        }

        game.trees.clear();

        for (Tree tree : trees) {
            game.trees.add(new Tree(tree));
        }

        game.shadows.clear();
        for (Map.Entry<Integer, Integer> entry : shadows.entrySet()) {
            game.shadows.put(entry.getKey(), entry.getValue());
        }
    }
}
