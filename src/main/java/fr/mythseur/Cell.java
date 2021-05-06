package fr.mythseur;

public class Cell {
    public int index;
    public int richess;
    public int[] neighbours;

    public Cell(int index, int richess, int[] neighbours) {
        this.index = index;
        this.richess = richess;
        this.neighbours = neighbours;
    }
}
