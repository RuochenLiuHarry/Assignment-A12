package controller;

import model.Game;
import model.Player;
import view.GameBoardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {
    private Game game;
    private GameBoardView view;

    public GameController(Game game, GameBoardView view) {
        this.game = game;
        this.view = view;
        initializeListeners();
    }

    private void initializeListeners() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                final int x = i;
                final int y = j;
                view.getGridButtons()[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        makeMove(x, y);
                    }
                });
            }
        }

        view.getSurrenderButton().addActionListener(e -> surrender());
        view.getSettingButton().addActionListener(e -> openSettings());
        view.getSwapButton().addActionListener(e -> swapBoards());
        view.getQuitButton().addActionListener(e -> quitGame());
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
            view.getGridButtons()[x][y].setBackground(java.awt.Color.RED);
        } else {
            view.getGridButtons()[x][y].setBackground(java.awt.Color.GRAY);
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
        // Implement board swapping logic if needed
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