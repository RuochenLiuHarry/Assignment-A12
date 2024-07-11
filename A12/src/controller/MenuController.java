package controller;

import model.Game;
import model.Player;
import view.GameBoardView;
import view.MenuView;
import view.SettingsView;

public class MenuController {
    private MenuView view;

    public MenuController(MenuView view) {
        this.view = view;
        initializeListeners();
    }

    private void initializeListeners() {
        view.getPveButton().addActionListener(e -> startPVEGame());
        view.getPvpButton().addActionListener(e -> startPVPGame());
        view.getSettingsButton().addActionListener(e -> openSettings());
        view.getQuitButton().addActionListener(e -> quitGame());
    }

    private void startPVEGame() {
        Player player1 = new Player("Player 1", false);
        Player player2 = new Player("Computer", true);
        Game game = new Game(player1, player2);
        GameBoardView gameBoardView = new GameBoardView();
        GameController gameController = new GameController(game, gameBoardView);
        gameController.startGame();
        view.setVisible(false);
    }

    private void startPVPGame() {
        // Implement PVP game start
    }

    private void openSettings() {
        SettingsView settingsView = new SettingsView();
        SettingsController settingsController = new SettingsController(settingsView);
        settingsView.setVisible(true);
    }

    private void quitGame() {
        System.exit(0);
    }

    public void showMenu() {
        view.setVisible(true);
    }
}