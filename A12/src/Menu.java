
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Menu extends JFrame {
	
    private JButton[][] gridButtons;
	
	public Menu() {
		setTitle("Battleship");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.blue);
        
        setLayout(new BorderLayout());
        
        ImageIcon logoIcon = new ImageIcon("logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        add(logoLabel, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLUE);

        buttonPanel.setLayout(new GridLayout(4, 1, 0, 10)); // 4 rows, 1 col, 0 pixel Horizontal, 10 pixel Vertical
        buttonPanel.setLayout(new GridLayout(4, 1, 0, 10));
        
        JLabel Text = new JLabel("Click button to start the game", JLabel.CENTER);
        Text.setForeground(Color.WHITE);
        Text.setFont(new Font("Arial", Font.BOLD, 30));
        
        JButton pveButton = new JButton("PVE");
        JButton pvpButton = new JButton("PVP");
        JButton settingsButton = new JButton("settings");
        JButton quitButton = new JButton("quit");

        buttonPanel.add(pveButton);
        buttonPanel.add(pvpButton);
        buttonPanel.add(settingsButton);
        buttonPanel.add(quitButton);
        
        add(Text, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        GameBoard(); // Testing gameboard
        settingMenu(); // Open setting Menu
        
	}
	
	private void settingMenu() {
		JFrame gameSet = new JFrame("game settings");
        gameSet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameSet.getContentPane().setBackground(Color.blue);
        
        gameSet.setLayout(new BorderLayout());
        
        ImageIcon logoIcon = new ImageIcon("logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        gameSet.add(logoLabel, BorderLayout.NORTH);
        
        JLabel Text = new JLabel("Click button to start the game", JLabel.CENTER);
        Text.setForeground(Color.WHITE);
        Text.setFont(new Font("Arial", Font.BOLD, 30));
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLUE);
        buttonPanel.setLayout(new GridLayout(4, 1, 0, 10));
        
        
        JButton langButton = new JButton("Language");
        JButton soundButton = new JButton("sound");
        JButton quitButton = new JButton("quit");

        
        buttonPanel.add(langButton);
        buttonPanel.add(soundButton);
        buttonPanel.add(quitButton);

        
        gameSet.add(Text, BorderLayout.CENTER);
        gameSet.add(buttonPanel, BorderLayout.SOUTH);
        gameSet.pack();
        gameSet.setVisible(true);
		
		
		
	}
	
    private void GameBoard() {
    	JFrame gameBoardFrame = new JFrame("Game Board");
        gameBoardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameBoardFrame.setLocationRelativeTo(null);
        gameBoardFrame.setResizable(false);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(51, 204, 255)); // Light blue
        
        // Top panel for the Title
        ImageIcon titleImage = new ImageIcon("logo.png"); 
        JLabel titleLabel = new JLabel(titleImage);
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        
        // Left panel for (Your fleet) and (Score board)
        JPanel leftPanel = new JPanel(new GridLayout(2, 1)); // 2 rows, 1 col
        leftPanel.setBackground(new Color(0, 51, 102));	// Dark blue
        JLabel yourFleetLabel = new JLabel("Your fleet", SwingConstants.CENTER);
        yourFleetLabel.setForeground(Color.WHITE);
        JLabel scoreBoardLabel = new JLabel("Score board", SwingConstants.CENTER);
        scoreBoardLabel.setForeground(Color.WHITE);
        leftPanel.add(yourFleetLabel);
        leftPanel.add(scoreBoardLabel);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        
        
        // Right panel for (Enemy fleet) and (Chat)
        JPanel rightPanel = new JPanel(new GridLayout(2, 1)); // 2 rows, 1 col
        rightPanel.setBackground(new Color(0, 51, 102));	// Dark blue
        JLabel enemyFleetLabel = new JLabel("Enemy fleet", SwingConstants.CENTER);
        enemyFleetLabel.setForeground(Color.WHITE);
        JLabel chatLabel = new JLabel("Chat", SwingConstants.CENTER);
        chatLabel.setForeground(Color.WHITE);
        rightPanel.add(enemyFleetLabel);
        rightPanel.add(chatLabel);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        
        
        // Bottom panel for the buttons
        JPanel bottomPanel = new JPanel(new GridLayout(1, 4, 10, 0)); // 1 row, 4 col, 10 horizontal pixel, 0 vertical pixel
        bottomPanel.setBackground(new Color(0, 51, 102)); // Dark blue
        JButton surrenderButton = new JButton("Surrender");
        JButton settingButton = new JButton("Setting");
        JButton swapButton = new JButton("Swap");
        JButton quitButton = new JButton("Quit");
        bottomPanel.add(surrenderButton);
        bottomPanel.add(settingButton);
        bottomPanel.add(swapButton);
        bottomPanel.add(quitButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        
        // Center panel for the game board grid
        JPanel gridPanel = new JPanel(new GridLayout(11, 11, 2, 2)); 
        gridPanel.setBackground(new Color(51, 204, 255));	// Light blue
        
        // Add an empty label in the top-left corner
        gridPanel.add(new JLabel(""));
        
        // Add column labels
        for (int col = 0; col < 10; col++) {
            JLabel label = new JLabel(String.valueOf((char) ('A' + col)), SwingConstants.CENTER);
            label.setForeground(Color.WHITE); // Set text color to white
            gridPanel.add(label);
        }
        
        gridButtons = new JButton[10][10];
        for (int row = 0; row < 10; row++) {
            // Add row label
            JLabel label = new JLabel(String.valueOf(row + 1), SwingConstants.CENTER);
            label.setForeground(Color.BLACK); // Set text color to white
            gridPanel.add(label);
            
            for (int col = 0; col < 10; col++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(40, 40)); // Size of button
                button.setEnabled(false); // Disable the button
                button.setBackground(new Color(51, 204, 255)); // Set background color to light blue
                button.setBorder(new LineBorder(Color.WHITE)); // Set white border
                gridButtons[row][col] = button;
                gridPanel.add(button);
            }
        }
        
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        gameBoardFrame.add(mainPanel);
        gameBoardFrame.pack();
        gameBoardFrame.setVisible(true);
    }
}