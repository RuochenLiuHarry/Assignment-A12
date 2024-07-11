import java.awt.Color;

import javax.swing.ImageIcon;

public class Player {
    private String name;
    private int score;
    private boolean isComputer;

    public Player(String name, boolean isComputer) {
        this.name = name;
        this.score = 0;
        this.isComputer = isComputer;
    }

    public void placeShips(GameBoard gameBoard, Ship ship, int startX, int startY, boolean horizontal) {
        // Logic for placing ships on the game board
        ship.placeShip(gameBoard, startX, startY, horizontal, false);
    }

    public void makeMove(GameBoard gameBoard, int x, int y) {
        // Logic for making a move on the game board
        gameBoard.updateBoard(this, x, y);
    }

    public void makeComputerMove(GameBoard gameBoard) {
        // Simple AI logic for making a move
        int x = (int) (Math.random() * 10);
        int y = (int) (Math.random() * 10);
        ImageIcon hitIcon = gameBoard.getHitIcon();
        ImageIcon missIcon = gameBoard.getMissIcon();
        
        while (gameBoard.getGridButtons()[x][y].getIcon() == hitIcon || gameBoard.getGridButtons()[x][y].getIcon() == missIcon) {
            x = (int) (Math.random() * 10);
            y = (int) (Math.random() * 10);
        }
        makeMove(gameBoard, x, y);
    }

    public void comms(String message) {
        // Logic for communication
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isComputer() {
        return isComputer;
    }
}

