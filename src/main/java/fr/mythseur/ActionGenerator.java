package fr.mythseur;

import java.util.ArrayList;
import java.util.List;

public class ActionGenerator {

    public static List<Action> getPossibleMoves(Game game) {
        List<Action> actions = new ArrayList<>();

        int seedCost = Simulator.getCostFor(0, game);

        game.trees.stream().filter(tree -> tree.isMine)
                .forEach(tree -> {
                    //TODO Mettre des Seeds
                    int growthCost = Simulator.getCostFor(tree.size, game);
                    if (growthCost <= game.mySun && !tree.isDormant) {
                        if (tree.size == 3) {
                            actions.add(new Action(EAction.COMPLETE, null, tree.cellIndex));
                        } else {
                            actions.add(new Action(EAction.GROW, null, tree.cellIndex));
                        }
                    }
                });

        if (actions.isEmpty())
            actions.add(new Action(EAction.WAIT, null, null));
        return actions;
    }
}
