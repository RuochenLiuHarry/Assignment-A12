import java.awt.Color;
import javax.swing.ImageIcon;

public class Ship {
    private String name;
    private int size;
    private boolean isSunk;
    private ImageIcon bowIcon;
    private ImageIcon midHullIcon;
    private ImageIcon sternIcon;

    public Ship(String name, int size, ImageIcon bowIcon, ImageIcon midHullIcon, ImageIcon sternIcon) {
        this.name = name;
        this.size = size;
        this.isSunk = false;
        this.bowIcon = bowIcon;
        this.midHullIcon = midHullIcon;
        this.sternIcon = sternIcon;
    }

    public void placeShip(GameBoard gameBoard, int startX, int startY, boolean horizontal) {
        for (int i = 0; i < size; i++) {
            if (horizontal) {
                if (i == 0) {
                    gameBoard.getGridButtons()[startX][startY + i].setIcon(bowIcon);
                } else if (i == size - 1) {
                    gameBoard.getGridButtons()[startX][startY + i].setIcon(sternIcon);
                } else {
                    gameBoard.getGridButtons()[startX][startY + i].setIcon(midHullIcon);
                }
            } else {
                if (i == 0) {
                    gameBoard.getGridButtons()[startX + i][startY].setIcon(bowIcon);
                } else if (i == size - 1) {
                    gameBoard.getGridButtons()[startX + i][startY].setIcon(sternIcon);
                } else {
                    gameBoard.getGridButtons()[startX + i][startY].setIcon(midHullIcon);
                }
            }
            gameBoard.getGridButtons()[startX + (horizontal ? 0 : i)][startY + (horizontal ? i : 0)].setBackground(Color.GRAY);
        }
    }

    public boolean checkIfSunk() {
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
