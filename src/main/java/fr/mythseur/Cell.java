package fr.mythseur;

class Cell {
    int index;
    int richess;
    int[] neighbours;

    public Cell(int index, int richess, int[] neighbours) {
        this.index = index;
        this.richess = richess;
        this.neighbours = neighbours;
    }
}
