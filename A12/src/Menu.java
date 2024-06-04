
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

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
		leftPanel.setPreferredSize(new Dimension(150, 0)); // Width left panel 150 pixels
		leftPanel.setBackground(new Color(0, 51, 102));	// Dark blue

		// (Your fleet) panel
		JPanel yourFleetPanel = new JPanel();
		yourFleetPanel.setBackground(new Color(0, 51, 102)); // Dark blue
		yourFleetPanel.setBorder(new LineBorder(Color.WHITE, 2)); // Add border
		JLabel yourFleetLabel = new JLabel("Your fleet");
		yourFleetLabel.setForeground(Color.WHITE);
		yourFleetPanel.add(yourFleetLabel);

		// (Scoreboard) panel
		JPanel scoreBoardPanel = new JPanel();
		scoreBoardPanel.setBackground(new Color(0, 51, 102)); // Dark blue
		scoreBoardPanel.setBorder(new LineBorder(Color.WHITE, 2)); // Add border
		JLabel scoreBoardLabel = new JLabel("Score board");
		scoreBoardLabel.setForeground(Color.WHITE);
		scoreBoardPanel.add(scoreBoardLabel);

		// Adding components to panels
		leftPanel.add(yourFleetPanel, BorderLayout.NORTH);
		leftPanel.add(scoreBoardPanel, BorderLayout.CENTER);
		mainPanel.add(leftPanel, BorderLayout.WEST);



		// Right panel for (Enemy fleet) and (Chat)
		JPanel rightPanel = new JPanel(new GridLayout(2, 1)); // 2 rows, 1 col
		rightPanel.setPreferredSize(new Dimension(150, 0)); // Width right panel, 150 pixels
		rightPanel.setBackground(new Color(0, 51, 102));	// Dark blue

		// (Enemy fleet) panel
		JPanel enemyFleetPanel = new JPanel();
		enemyFleetPanel.setBackground(new Color(0, 51, 102)); // Dark blue
		enemyFleetPanel.setBorder(new LineBorder(Color.WHITE, 2)); // Add border
		JLabel enemyFleetLabel = new JLabel("Enemy fleet");
		enemyFleetLabel.setForeground(Color.WHITE);
		enemyFleetPanel.add(enemyFleetLabel);

		// (Chat) panel
		JPanel chatPanel = new JPanel();
		chatPanel.setBackground(new Color(0, 51, 102)); // Dark blue
		chatPanel.setBorder(new LineBorder(Color.WHITE, 2)); // Add border
		JLabel chatLabel = new JLabel("Chat");
		chatLabel.setForeground(Color.WHITE);
		chatPanel.add(chatLabel);

		// Adding components to panels
		rightPanel.add(enemyFleetPanel, BorderLayout.NORTH);
		rightPanel.add(chatPanel, BorderLayout.CENTER);
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

		// Empty label in the top-left corner
		gridPanel.add(new JLabel(""));

		// Column labels
		for (int col = 0; col < 10; col++) {
			JLabel label = new JLabel(String.valueOf((char) ('A' + col)));
			label.setForeground(Color.WHITE); // Text color white
			gridPanel.add(label);
		}

		gridButtons = new JButton[10][10];
		for (int row = 0; row < 10; row++) {
			// Row label
			JLabel label = new JLabel(String.valueOf(row + 1), SwingConstants.CENTER); // Centers the row numbers
			label.setForeground(Color.BLACK); // Text color black
			gridPanel.add(label);

			for (int col = 0; col < 10; col++) {
				JButton button = new JButton();
				button.setPreferredSize(new Dimension(40, 40)); // Size of button
				button.setEnabled(false); // Disable the button
				button.setBackground(new Color(51, 204, 255)); // Light blue
				button.setBorder(new LineBorder(Color.WHITE)); // White border
				gridButtons[row][col] = button;
				gridPanel.add(button);
			}
		}


		// Three-segment ship vertically
		ImageIcon NvertShipBow = new ImageIcon("bow_north.png");
		ImageIcon vertMidHull = new ImageIcon("midhull_vert.png");
		ImageIcon SvertShipBow = new ImageIcon("bow_south.png");
		gridButtons[2][2].setIcon(NvertShipBow);
		gridButtons[3][2].setIcon(vertMidHull);
		gridButtons[4][2].setIcon(SvertShipBow);
		
		// Three-segment ship horizontally
		ImageIcon EhorizShipBow = new ImageIcon("bow_east.png");
		ImageIcon horizMidHull = new ImageIcon("midhull_horiz.png");
		ImageIcon WvertShipBow = new ImageIcon("bow_west.png");
		gridButtons[7][7].setIcon(EhorizShipBow);
		gridButtons[7][6].setIcon(horizMidHull);
		gridButtons[7][5].setIcon(WvertShipBow);
		
		// Hit and Miss
		ImageIcon hit = new ImageIcon("hit.png");
		ImageIcon miss = new ImageIcon("miss.png");
		gridButtons[7][7].setIcon(hit); // Hit three-segment ship horiz
		gridButtons[2][2].setIcon(hit); // Hit three-segment ship vert
		gridButtons[8][2].setIcon(miss);
		gridButtons[4][8].setIcon(miss);
		gridButtons[3][5].setIcon(miss);


		mainPanel.add(gridPanel, BorderLayout.CENTER);
		gameBoardFrame.add(mainPanel);
		gameBoardFrame.pack();
		gameBoardFrame.setVisible(true);
	}
}