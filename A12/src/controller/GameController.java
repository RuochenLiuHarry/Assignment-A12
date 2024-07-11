package controller;

import model.Game;
import model.Player;
import model.Ship;
import view.GameBoardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.util.Random;

public class GameController {
    private Game game;
    private GameBoardView view;
    private int currentShipIndex;
    private boolean placingShips;

    public GameController(Game game, GameBoardView view) {
        this.game = game;
        this.view = view;
        this.currentShipIndex = 0;
        this.placingShips = true;
        initializeListeners();
    }

    private void initializeListeners() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                int finalRow = row;
                int finalCol = col;

                view.getGridButtons()[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (placingShips) {
                            placePlayerShip(finalRow, finalCol);
                        }
                    }
                });

                view.getComputerGridButtons()[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!placingShips && game.getCurrentTurn() == game.getPlayer1()) {
                            makeMove(finalRow, finalCol);
                        }
                    }
                });
            }
        }

        view.getSurrenderButton().addActionListener(e -> surrender());
        view.getSettingButton().addActionListener(e -> openSettings());
        view.getSwapButton().addActionListener(e -> swapBoards());
        view.getQuitButton().addActionListener(e -> quitGame());
    }

    private void placePlayerShip(int row, int col) {
        if (currentShipIndex >= game.getPlayer1Ships().size()) {
            return;
        }
        Ship currentShip = game.getPlayer1Ships().get(currentShipIndex);
        boolean horizontal = isShipHorizontal(currentShipIndex, false);
        if (canPlaceShip(row, col, currentShip.getSize(), horizontal)) {
            currentShip.placeShip(view, row, col, horizontal, false);
            updateShipView(row, col, currentShip, horizontal, false);
            currentShipIndex++;
            if (currentShipIndex == game.getPlayer1Ships().size()) {
                view.showMessage("All ships placed. Click 'Swap' to start the game.");
                placingShips = false;
                placeComputerShips();
            }
        } else {
            view.showMessage("Ships cannot overlap or go out of bounds. Try again.");
        }
    }

    private boolean canPlaceShip(int startX, int startY, int size, boolean horizontal) {
        if (horizontal) {
            if (startY + size > 10) return false;
            for (int i = 0; i < size; i++) {
                if (view.getGridButtons()[startX][startY + i].getIcon() != null) {
                    return false;
                }
            }
        } else {
            if (startX + size > 10) return false;
            for (int i = 0; i < size; i++) {
                if (view.getGridButtons()[startX + i][startY].getIcon() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isShipHorizontal(int shipIndex, boolean isComputer) {
        if (isComputer) {
            return game.getPlayer1Ships().get(shipIndex).isHorizontal();
        } else {
            switch (shipIndex) {
                case 0: return true; // Aircraft Carrier
                case 1: return false; // Battleship
                case 2: return false; // Submarine
                case 3: return false; // Cruiser
                case 4: return true; // Destroyer
                default: return true;
            }
        }
    }

    private void updateShipView(int x, int y, Ship ship, boolean horizontal, boolean isComputer) {
        if (isComputer) {
            return; // Do not display ship icons on the computer board
        }

        JButton[][] buttons = view.getGridButtons();
        ImageIcon[] icons = ship.getIcons();
        for (int i = 0; i < ship.getSize(); i++) {
            buttons[x][y].setIcon(horizontal ? icons[0] : icons[0]);
            if (i == ship.getSize() - 1) {
                buttons[x][y].setIcon(horizontal ? icons[2] : icons[2]);
            } else if (i != 0) {
                buttons[x][y].setIcon(horizontal ? icons[1] : icons[1]);
            }
            if (horizontal) {
                y++;
            } else {
                x++;
            }
        }
    }

    private void placeComputerShips() {
        Random random = new Random();
        for (int i = 0; i < game.getPlayer2Ships().size(); i++) {
            Ship ship = game.getPlayer2Ships().get(i);
            boolean placed = false;
            boolean horizontal = game.getPlayer1Ships().get(i).isHorizontal();
            while (!placed) {
                int startX = random.nextInt(10);
                int startY = random.nextInt(10);
                if (canPlaceComputerShip(startX, startY, ship.getSize(), horizontal)) {
                    ship.placeShip(view, startX, startY, horizontal, true);
                    markBoardWithShip(game.getPlayer2Board(), startX, startY, ship.getSize(), horizontal);
                    placed = true;
                }
            }
        }
    }

    private boolean canPlaceComputerShip(int startX, int startY, int size, boolean horizontal) {
        boolean[][] board = game.getPlayer2Board();
        if (horizontal) {
            if (startY + size > 10) return false;
            for (int i = 0; i < size; i++) {
                if (board[startX][startY + i]) return false;
            }
        } else {
            if (startX + size > 10) return false;
            for (int i = 0; i < size; i++) {
                if (board[startX + i][startY]) return false;
            }
        }
        return true;
    }

    private void markBoardWithShip(boolean[][] board, int startX, int startY, int size, boolean horizontal) {
        for (int i = 0; i < size; i++) {
            if (horizontal) {
                board[startX][startY + i] = true;
            } else {
                board[startX + i][startY] = true;
            }
        }
    }

    private void makeMove(int x, int y) {
        if (game.getCurrentTurn().isComputer()) {
            return; // Prevent player from making moves during computer's turn
        }

        boolean isHit = game.makeMove(x, y);
        updateView(x, y, isHit);

        if (!isHit) {
            view.showMessage("Miss! Click 'OK' to end your turn.");
            game.switchTurn();
            makeComputerMove(); // Computer makes a move after player misses
        }
    }

    private void makeComputerMove() {
        // Simple AI: random moves
        int x, y;
        boolean isHit;
        do {
            x = (int) (Math.random() * 10);
            y = (int) (Math.random() * 10);
            isHit = game.makeMove(x, y);
        } while (isHit);

        updateView(x, y, isHit);

        if (!isHit) {
            game.switchTurn();
        }
    }

    private void updateView(int x, int y, boolean isHit) {
        if (game.getCurrentTurn() == game.getPlayer1()) {
            if (isHit) {
                view.getComputerGridButtons()[x][y].setIcon(new ImageIcon("hit.png"));
            } else {
                view.getComputerGridButtons()[x][y].setIcon(new ImageIcon("miss.png"));
            }
        } 
        if (game.getCurrentTurn() == game.getPlayer2()) {
            if (isHit) {
                view.getGridButtons()[x][y].setIcon(new ImageIcon("hit.png"));
            } else {
                view.getGridButtons()[x][y].setIcon(new ImageIcon("miss.png"));
            }
        }
        view.updateTurnLabel(game.getCurrentTurn().getName());
        view.updateScores(game.getPlayer1().getScore(), game.getPlayer2().getScore());
    }

    private void surrender() {
        game.quitGame();
        view.showMessage(game.getCurrentTurn().getName() + " surrendered!");
        // Handle game over
    }

    private void openSettings() {
        // Open settings view
    }

    private void swapBoards() {
        if (placingShips) {
            view.showMessage("Place all ships before starting the game.");
            return;
        }
        view.toggleBoard();
    }

    private void quitGame() {
        game.quitGame();
        view.dispose();
        // Return to main menu
    }

    public void startGame() {
        game.startGame();
        view.setVisible(true);
    }
}
