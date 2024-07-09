public class Game {
    private Player player1;
    private Player player2;
    private GameBoard gameBoard;
    private boolean isRunning;
    private int volume;
    private String language;
    private String instructions;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameBoard = new GameBoard();
        this.volume = 50; // Default volume
        this.language = "English"; // Default language
    }

    public void startGame(String mode) {
        isRunning = true;
        // Start the game with the given mode
        if (mode.equals("PVE")) {
            player2.placeShips(gameBoard, new Ship("Battleship", 3), 0, 0, 0, 2);
            // Place other ships for player2
        }
    }

    public void quitGame() {
        isRunning = false;
        // Quit the game
    }

    public void swapGameboard() {
        // Swap the gameboard
    }

    public void surrenderAndRestart() {
        // Surrender and restart the game
    }

    public void changeVolume(int volume) {
        this.volume = volume;
        // Change the game volume
    }

    public void changeLanguage(String language) {
        this.language = language;
        // Change the game language
    }

    public void showInstructions() {
        // Show the game instructions
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public int getVolume() {
        return volume;
    }

    public String getLanguage() {
        return language;
    }

    public String getInstructions() {
        return instructions;
    }

    public void playTurn() {
        // Logic for handling turns
        if (player1.isComputer()) {
            player1.makeComputerMove(gameBoard);
        } else {
            // Wait for player1's move (handled by UI)
        }

        if (player2.isComputer()) {
            player2.makeComputerMove(gameBoard);
        } else {
            // Wait for player2's move (handled by UI)
        }
    }
}
