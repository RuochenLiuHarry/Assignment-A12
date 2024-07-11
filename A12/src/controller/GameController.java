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
                        } else {
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
        // Define the orientation of the ships based on index and player/computer
        if (isComputer) {
            switch (shipIndex) {
                case 0: return true; // Aircraft Carrier
                case 1: return false; // Battleship
                case 2: return true; // Submarine
                case 3: return false; // Cruiser
                case 4: return true; // Destroyer
                default: return true;
            }
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
        JButton[][] buttons = isComputer ? view.getComputerGridButtons() : view.getGridButtons();
        ImageIcon[] icons = ship.getIcons();
        for (int i = 0; i < ship.getSize(); i++) {
            if (i == 0) {
                buttons[x][y].setIcon(horizontal ? icons[0] : icons[0]);
            } else if (i == ship.getSize() - 1) {
                buttons[x][y].setIcon(horizontal ? icons[2] : icons[2]);
            } else {
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
            boolean horizontal = isShipHorizontal(i, true);
            while (!placed) {
                int startX = random.nextInt(10);
                int startY = random.nextInt(10);
                if (canPlaceShip(startX, startY, ship.getSize(), horizontal)) {
                    ship.placeShip(view, startX, startY, horizontal, true);
                    placed = true;
                }
            }
        }
    }

    private void makeMove(int x, int y) {
        if (game.getCurrentTurn().isComputer()) {
            return; // Prevent player from making moves during computer's turn
        }

        boolean isHit = game.makeMove(x, y);
        updateView(x, y, isHit);

        if (game.getCurrentTurn().isComputer()) {
            makeComputerMove();
        }
    }

    private void makeComputerMove() {
        // Simple AI: random moves
        int x, y;
        do {
            x = (int) (Math.random() * 10);
            y = (int) (Math.random() * 10);
        } while (!game.makeMove(x, y));

        updateView(x, y, true);
    }

    private void updateView(int x, int y, boolean isHit) {
        if (isHit) {
            view.getGridButtons()[x][y].setIcon(new ImageIcon("hit.png"));
        } else {
            view.getGridButtons()[x][y].setIcon(new ImageIcon("miss.png"));
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
        if (currentShipIndex < game.getPlayer1Ships().size()) {
            view.showMessage("Place all ships before starting the game.");
            return;
        }
        game.placeComputerShips();
        game.startGame();
        view.showMessage("Game started! It's Player 1's turn.");
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
