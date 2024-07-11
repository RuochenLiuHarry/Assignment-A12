import view.GameBoardView;
import controller.GameController;
import model.Game;
import model.Player;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Player 1", false);
        Player player2 = new Player("Computer", true);
        Game game = new Game(player1, player2);
        GameBoardView gameBoardView = new GameBoardView();
        GameController gameController = new GameController(game, gameBoardView);
        gameController.startGame();
    }
}
