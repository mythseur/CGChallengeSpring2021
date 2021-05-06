package fr.mythseur;

import java.util.Scanner;


class Player {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        Game game = new Game();

        int numberOfCells = in.nextInt();
        for (int i = 0; i < numberOfCells; i++) {
            int index = in.nextInt(); // 0 is the center cell, the next cells spiral outwards
            int richness = in.nextInt(); // 0 if the cell is unusable, 1-3 for usable cells
            int neigh0 = in.nextInt(); // the index of the neighbouring cell for each direction
            int neigh1 = in.nextInt();
            int neigh2 = in.nextInt();
            int neigh3 = in.nextInt();
            int neigh4 = in.nextInt();
            int neigh5 = in.nextInt();
            int neighs[] = new int[]{neigh0, neigh1, neigh2, neigh3, neigh4, neigh5};
            Cell cell = new Cell(index, richness, neighs);
            game.board.add(cell);
        }

        while (true) {
            game.day = in.nextInt(); // the game lasts 24 days: 0-23
            game.nutrients = in.nextInt(); // the base score you gain from the next COMPLETE action
            game.mySun = in.nextInt(); // your sun points
            game.myScore = in.nextInt(); // your current score
            game.opponentSun = in.nextInt(); // opponent's sun points
            game.opponentScore = in.nextInt(); // opponent's score
            game.opponentIsWaiting = in.nextInt() != 0; // whether your opponent is asleep until the next day

            game.trees.clear();
            int numberOfTrees = in.nextInt(); // the current amount of trees
            for (int i = 0; i < numberOfTrees; i++) {
                int cellIndex = in.nextInt(); // location of this tree
                int size = in.nextInt();  // size of this tree: 0-3
                boolean isMine = in.nextInt() != 0; // 1 if this is your tree
                boolean isDormant = in.nextInt() != 0; // 1 if this tree is dormant
                Tree tree = new Tree(cellIndex, size, isMine, isDormant);
                game.trees.add(tree);
            }

            game.possibleActions.clear();
            int numberOfPossibleActions = in.nextInt();
            in.nextLine();
            for (int i = 0; i < numberOfPossibleActions; i++) {
                String possibleAction = in.nextLine();
                game.possibleActions.add(Action.parse(possibleAction));
            }

            Action action = game.getNextAction();
            System.out.println(action);
        }
    }
}