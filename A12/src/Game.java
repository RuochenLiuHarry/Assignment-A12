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
        this.gameBoard = new GameBoard(player1, player2);
        this.volume = 50; // Default volume
        this.language = "English"; // Default language
    }

    public void startGame(String mode) {
        isRunning = true;
        gameBoard.setVisible(true);
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
}
