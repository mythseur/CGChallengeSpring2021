package fr.mythseur;

public class Tree {
    public int cellIndex;
    public int size;
    public boolean isMine;
    public boolean isDormant;

    public Tree(int cellIndex, int size, boolean isMine, boolean isDormant) {
        this.cellIndex = cellIndex;
        this.size = size;
        this.isMine = isMine;
        this.isDormant = isDormant;
    }

    public Tree(Tree tree) {
        this.cellIndex = tree.cellIndex;
        this.size = tree.size;
        this.isMine = tree.isMine;
        this.isDormant = tree.isDormant;
    }

    public void grow() {
        size++;
    }

    public void setDormant() {
        isDormant = true;
    }

    public void reset() {
        isDormant = false;
    }
}