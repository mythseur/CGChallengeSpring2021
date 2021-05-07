package fr.mythseur;

import java.util.*;
import java.util.stream.Collectors;

public class ActionGenerator {

    public static List<Action> getPossibleMoves(Game game) {
        List<Action> actions = new ArrayList<>();

        int seedCost = Simulator.getCostFor(0, game);

        Set<Integer> collect = game.trees.stream().map(tree -> tree.cellIndex).collect(Collectors.toSet());
        List<Action> seedActions = new ArrayList<>();
        game.trees.stream().filter(tree -> tree.isMine)
                .forEach(tree -> {
                    //TODO Mettre des Seeds
                    //for all Cells atteignable dans la range, ajouter un Seed
                    if (!tree.isDormant) {
                        if (tree.size > 0 && game.mySun >= seedCost) {
                            for (Cell cell : getCellsInRange(tree, game)) {
                                if (!collect.contains(cell.index)) {
                                    seedActions.add(new Action(EAction.SEED, tree.cellIndex, cell.index));
                                }
                            }
                        }

                        int growthCost = Simulator.getCostFor(tree.size, game);
                        if (growthCost <= game.mySun) {
                            if (tree.size == 3) {
                                actions.add(new Action(EAction.COMPLETE, null, tree.cellIndex));
                            } else {
                                actions.add(new Action(EAction.GROW, null, tree.cellIndex));
                            }
                        }
                    }
                });

        List<Action> collect1 = seedActions.stream().sorted(Comparator.comparingInt(action -> game.board.get(action.targetCellIdx).richess)).collect(Collectors.toList());
        if (!collect1.isEmpty()) {
            collect1 = collect1.subList(0, Math.min(5, collect1.size()));
            actions.addAll(collect1);
        }


        if (actions.isEmpty())
            actions.add(new Action(EAction.WAIT, null, null));
        return actions;
    }

    private static Set<Cell> getCellsInRange(Tree tree, Game game) {
        Cell start = game.board.get(tree.cellIndex);
        return new HashSet<>(computeCells(game, start, tree.size));
    }

    private static Set<Cell> computeCells(Game game, Cell cell, int remaining) {
        Set<Cell> cells = new HashSet<>();
        for (int index : cell.neighbours) {
            if (index >= 0) {
                Cell current = game.board.get(index);
                cells.add(current);
                if (remaining > 1) {
                    cells.addAll(computeCells(game, current, remaining - 1));
                }
            }
        }
        return cells;
    }
}
