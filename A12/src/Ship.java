import javax.swing.ImageIcon;
import javax.swing.JButton;

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

    public void placeShip(GameBoard gameBoard, int startX, int startY, boolean horizontal, boolean isComputer) {
        JButton[][] gridButtons = isComputer ? gameBoard.getComputerGridButtons() : gameBoard.getGridButtons();
        for (int i = 0; i < size; i++) {
            if (horizontal) {
                if (i == 0) {
                    gridButtons[startX][startY + i].setIcon(bowIcon);
                } else if (i == size - 1) {
                    gridButtons[startX][startY + i].setIcon(sternIcon);
                } else {
                    gridButtons[startX][startY + i].setIcon(midHullIcon);
                }
            } else {
                if (i == 0) {
                    gridButtons[startX + i][startY].setIcon(bowIcon);
                } else if (i == size - 1) {
                    gridButtons[startX + i][startY].setIcon(sternIcon);
                } else {
                    gridButtons[startX + i][startY].setIcon(midHullIcon);
                }
            }
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

