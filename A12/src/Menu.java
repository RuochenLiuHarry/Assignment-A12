import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class Menu extends JFrame {

    private JFrame frame;
    private JFrame mainMenu;
    private JButton pveButton;
    private JButton pvpButton;
    private JButton settingsButton;
    private JButton quitButton;
    private JButton surrenderButton;
    private JButton swapButton;
    private String victoryMessage;
    private JButton confirmButton;
    private JButton cancelButton;
    private JDialog dialog;
    private String message;
    private Game game;



    public void menu() {
    	mainMenu = new JFrame();
        mainMenu.setTitle("Battleship");
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu. getContentPane().setBackground(new Color(51, 204, 255));

        mainMenu.setLayout(new BorderLayout());

        ImageIcon logoIcon = new ImageIcon("logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        mainMenu.add(logoLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(51, 204, 255));
        buttonPanel.setLayout(new GridLayout(4, 1, 0, 10));

        JLabel text = new JLabel("Click button to start the game", JLabel.CENTER);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Arial", Font.BOLD, 30));

        pveButton = new JButton("PVE");
        pveButton.addActionListener(e -> startPVE());

        pvpButton = new JButton("PVP");
        settingsButton = new JButton("Settings");
        settingsButton.addActionListener(e -> settingMenu());
        quitButton = new JButton("Quit");

        buttonPanel.add(pveButton);
        buttonPanel.add(pvpButton);
        buttonPanel.add(settingsButton);
        buttonPanel.add(quitButton);

        mainMenu.add(text, BorderLayout.CENTER);
        mainMenu.add(buttonPanel, BorderLayout.SOUTH);
        mainMenu.pack();
        mainMenu.setVisible(true);
    }

    private void startPVE() {
    	mainMenu.setVisible(false);
        if (game != null) {
            game.getGameBoard().dispose();
        }

        Player player1 = new Player("Player 1", false);
        Player player2 = new Player("Computer", true);
        game = new Game(player1, player2);
        game.startGame("PVE");

        // Show game board
        game.getGameBoard().setVisible(true);
        // Play turns in a loop or handle turn switching
    }

    public void settingMenu() {
    	mainMenu.setVisible(false);
        JFrame gameSet = new JFrame("Game Settings");
        gameSet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameSet.getContentPane().setBackground(new Color(51, 204, 255));

        gameSet.setLayout(new BorderLayout());

        ImageIcon logoIcon = new ImageIcon("logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        gameSet.add(logoLabel, BorderLayout.NORTH);

        JLabel text = new JLabel("Click button to change the settings", JLabel.CENTER);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Arial", Font.BOLD, 30));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(51, 204, 255));
        buttonPanel.setLayout(new GridLayout(3, 1, 0, 10));

        JButton langButton = new JButton("Language");
        JButton soundButton = new JButton("Sound");
        JButton quitButton = new JButton("back");

        buttonPanel.add(langButton);
        buttonPanel.add(soundButton);
        buttonPanel.add(quitButton);

        gameSet.add(text, BorderLayout.CENTER);
        gameSet.add(buttonPanel, BorderLayout.SOUTH);
        gameSet.pack();
        gameSet.setVisible(true);
    }

    public void victory() {
        JFrame win = new JFrame("You Won");
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.getContentPane().setBackground(new Color(51, 204, 255));

        JLabel text = new JLabel("You Won!", JLabel.CENTER);
        JLabel text1 = new JLabel("(Click to continue)", JLabel.CENTER);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Arial", Font.BOLD, 40));
        text1.setForeground(Color.WHITE);
        text1.setFont(new Font("Arial", Font.BOLD, 40));

        win.add(text, BorderLayout.NORTH);
        win.add(text1, BorderLayout.SOUTH);
        win.pack();
        win.setVisible(true);
    }

    public void fail() {
        JFrame lose = new JFrame("You Lose");
        lose.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lose.getContentPane().setBackground(new Color(51, 204, 255));

        JLabel text = new JLabel("You Lose", JLabel.CENTER);
        JLabel text1 = new JLabel("(Click to continue)", JLabel.CENTER);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Arial", Font.BOLD, 40));
        text1.setForeground(Color.WHITE);
        text1.setFont(new Font("Arial", Font.BOLD, 40));

        lose.add(text, BorderLayout.NORTH);
        lose.add(text1, BorderLayout.SOUTH);
        lose.pack();
        lose.setVisible(true);
    }

    public void showConfirmation(String message) {
        // Show confirmation dialog with the provided message
    }
}
