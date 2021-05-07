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

    public Cell(Cell cell) {
        this.index = cell.index;
        this.richess = cell.richess;
        this.neighbours = cell.neighbours;
    }

    public void update(Cell cell) {
        this.index = cell.index;
        this.richess = cell.richess;
        this.neighbours = cell.neighbours;
    }
}
