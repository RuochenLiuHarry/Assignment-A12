import java.awt.Color;

public class Ship {
    private String name;
    private int size;
    private boolean isSunk;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.isSunk = false;
    }

    public void placeShip(GameBoard gameBoard, int startX, int startY, int endX, int endY) {
        // Logic for placing the ship on the game board
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                gameBoard.getGridButtons()[i][j].setBackground(Color.GRAY); // Placeholder for ship placement logic
            }
        }
    }

    public boolean checkIfSunk() {
        // Logic for checking if the ship is sunk
        return isSunk;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public boolean isSunk() {
        return isSunk;
    }

    public void setSunk(boolean isSunk) {
        this.isSunk = isSunk;
    }
}
