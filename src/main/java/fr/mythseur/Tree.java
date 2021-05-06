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
}