package fr.mythseur;

public class Simulator {

    public static final int[] TREE_BASE_COST = new int[]{0, 1, 3, 7};

    public static void doAction(Action action, Game game) {
        switch (action.type) {
            case GROW:
                doGrow(action, game);
                break;
            case SEED:
                doSeed(action, game);
                break;
            case COMPLETE:
                doComplete(action, game);
                break;
            case WAIT:
                doWait(game);
                break;
        }
    }

    private static void doWait(Game game) {
        //TODO : Passer un jour, on ne fait un Wait que quand plus rien à faire
        game.trees.forEach(Tree::reset);
        game.day += 1;
        //TODO : Donner les soleils des arbres sans ombres
        game.trees.forEach(tree -> {
            if (!game.shadows.containsKey(tree.cellIndex) || game.shadows.get(tree.cellIndex) < tree.size) {
                game.mySun += tree.size;
            }
        });
    }

    private static void doComplete(Action action, Game game) {
        Tree targetTree = game.trees.get(action.targetCellIdx);
        int growthCost = getCostFor(targetTree.size, game);

        game.mySun -= growthCost;
        game.trees.remove(targetTree);

        Cell cell = game.board.get(action.targetCellIdx);
        int points = game.nutrients;

        if (cell.richess == 2)
            points += 2;
        else if (cell.richess == 3)
            points += 4;

        game.myScore += points;
    }

    private static void doSeed(Action action, Game game) {
        int seedCost = getSeedCost(game);
        game.mySun -= seedCost;

        Tree tree = new Tree(action.targetCellIdx, 0, true, true);
        game.trees.add(tree);

        Tree tree1 = game.trees.get(action.sourceCellIdx);
        tree1.setDormant();
    }

    private static int getSeedCost(Game game) {
        return getCostFor(0, game);
    }

    private static void doGrow(Action action, Game game) {

        Tree targetTree = game.trees.get(action.targetCellIdx);
        int growthCost = getCostFor(targetTree.size, game);

        game.mySun -= growthCost;
        targetTree.grow();
        targetTree.setDormant();
    }

    public static int getCostFor(int size, Game game) {
        int targetSize = size + 1;
        if (targetSize > 3) {
            return 4;
        }
        int baseCost = TREE_BASE_COST[size];
        int count = (int) game.trees.stream().filter(tree -> tree.isMine && tree.size == size).count();
        return baseCost + count;
    }
}
