package model;

public class Ship {
    private String name;
    private int size;
    private boolean isSunk;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.isSunk = false;
    }

    public String getName() { return name; }
    public int getSize() { return size; }
    public boolean isSunk() { return isSunk; }
    public void setSunk(boolean isSunk) { this.isSunk = isSunk; }
}