package model;

import javax.swing.ImageIcon;
import view.GameBoardView;
import javax.swing.JButton;

public class Ship {
    private String name;
    private int size;
    private boolean isSunk;
    private ImageIcon[] icons;

    public Ship(String name, int size, ImageIcon bow, ImageIcon middle, ImageIcon stern) {
        this.name = name;
        this.size = size;
        this.isSunk = false;
        this.icons = new ImageIcon[]{bow, middle, stern};
    }

    public String getName() { return name; }
    public int getSize() { return size; }
    public boolean isSunk() { return isSunk; }
    public void setSunk(boolean isSunk) { this.isSunk = isSunk; }
    public ImageIcon[] getIcons() { return icons; }

    public void placeShip(GameBoardView view, int startX, int startY, boolean horizontal, boolean isComputer) {
        JButton[][] buttons = isComputer ? view.getComputerGridButtons() : view.getGridButtons();
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                buttons[startX][startY].setIcon(horizontal ? icons[0] : icons[0]);
            } else if (i == size - 1) {
                buttons[startX][startY].setIcon(horizontal ? icons[2] : icons[2]);
            } else {
                buttons[startX][startY].setIcon(icons[1]);
            }
            if (horizontal) {
                startY++;
            } else {
                startX++;
            }
        }
    }
}
