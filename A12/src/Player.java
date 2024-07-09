import java.awt.Color;

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
        ship.placeShip(gameBoard, startX, startY, horizontal);
    }

    public void makeMove(GameBoard gameBoard, int x, int y) {
        // Logic for making a move on the game board
        gameBoard.updateBoard(this, x, y);
    }

    public void makeComputerMove(GameBoard gameBoard) {
        // Simple AI logic for making a move
        int x = (int) (Math.random() * 10);
        int y = (int) (Math.random() * 10);
        while (gameBoard.getGridButtons()[x][y].getBackground() == Color.RED || gameBoard.getGridButtons()[x][y].getBackground() == Color.WHITE) {
            x = (int) (Math.random() * 10);
            y = (int) (Math.random() * 10);
        }
        makeMove(gameBoard, x, y);
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
