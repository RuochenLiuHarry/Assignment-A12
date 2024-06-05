/**
 * The UiTest class contains the main method to start the Battleship application.
 * This class initializes the Menu class and displays the main menu.
 */
public class UiTest {

	/**
     * The main method to start the application.
     * This method creates an instance of the Menu class and calls its menu method
     * to display the main menu of the Battleship game.
     *
     * @param args Command line arguments
     */
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.menu();
	}

}