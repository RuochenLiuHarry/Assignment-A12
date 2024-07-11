package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private Player player1;
    private Player player2;
    private boolean isRunning;
    private int volume;
    private String language;
    private String instructions;
    private List<Ship> player1Ships;
    private List<Ship> player2Ships;
    private boolean[][] player1Board;
    private boolean[][] player2Board;
    private boolean[][] player1Hits;
    private boolean[][] player2Hits;
    private Player currentTurn;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.volume = 50;
        this.language = "English";
        this.player1Ships = new ArrayList<>();
        this.player2Ships = new ArrayList<>();
        this.player1Board = new boolean[10][10];
        this.player2Board = new boolean[10][10];
        this.player1Hits = new boolean[10][10];
        this.player2Hits = new boolean[10][10];
        initializeShips();
    }

    private void initializeShips() {
        player1Ships.add(new Ship("Aircraft Carrier", 5));
        player1Ships.add(new Ship("Battleship", 4));
        player1Ships.add(new Ship("Submarine", 3));
        player1Ships.add(new Ship("Cruiser", 3));
        player1Ships.add(new Ship("Destroyer", 2));

        player2Ships.addAll(player1Ships);
    }

    public void startGame() {
        isRunning = true;
        currentTurn = player1;
    }

    public void quitGame() {
        isRunning = false;
    }

    public boolean makeMove(int x, int y) {
        boolean[][] targetBoard = (currentTurn == player1) ? player2Board : player1Board;
        boolean[][] hitsBoard = (currentTurn == player1) ? player1Hits : player2Hits;

        if (hitsBoard[x][y]) {
            return false; // Already hit
        }

        hitsBoard[x][y] = true;
        boolean isHit = targetBoard[x][y];

        if (!isHit) {
            switchTurn();
        }

        return isHit;
    }

    public void placeShip(Player player, Ship ship, int x, int y, boolean isHorizontal) {
        boolean[][] board = (player == player1) ? player1Board : player2Board;

        if (canPlaceShip(board, ship.getSize(), x, y, isHorizontal)) {
            for (int i = 0; i < ship.getSize(); i++) {
                if (isHorizontal) {
                    board[x][y + i] = true;
                } else {
                    board[x + i][y] = true;
                }
            }
        }
    }

    private boolean canPlaceShip(boolean[][] board, int size, int x, int y, boolean isHorizontal) {
        if (isHorizontal) {
            if (y + size > 10) return false;
            for (int i = 0; i < size; i++) {
                if (board[x][y + i]) return false;
            }
        } else {
            if (x + size > 10) return false;
            for (int i = 0; i < size; i++) {
                if (board[x + i][y]) return false;
            }
        }
        return true;
    }

    public void placeComputerShips() {
        Random random = new Random();
        for (Ship ship : player2Ships) {
            boolean placed = false;
            while (!placed) {
                int x = random.nextInt(10);
                int y = random.nextInt(10);
                boolean isHorizontal = random.nextBoolean();
                if (canPlaceShip(player2Board, ship.getSize(), x, y, isHorizontal)) {
                    placeShip(player2, ship, x, y, isHorizontal);
                    placed = true;
                }
            }
        }
    }

    private void switchTurn() {
        currentTurn = (currentTurn == player1) ? player2 : player1;
    }

    public void changeVolume(int volume) {
        this.volume = volume;
    }

    public void changeLanguage(String language) {
        this.language = language;
    }

    // Getters and setters
    public Player getPlayer1() { return player1; }
    public Player getPlayer2() { return player2; }
    public boolean isRunning() { return isRunning; }
    public int getVolume() { return volume; }
    public String getLanguage() { return language; }
    public String getInstructions() { return instructions; }
    public Player getCurrentTurn() { return currentTurn; }
    public boolean[][] getPlayer1Board() { return player1Board; }
    public boolean[][] getPlayer2Board() { return player2Board; }
    public boolean[][] getPlayer1Hits() { return player1Hits; }
    public boolean[][] getPlayer2Hits() { return player2Hits; }
}