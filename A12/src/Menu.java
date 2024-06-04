
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
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(51, 204, 255)); // Light blue
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        
        // Adding column labels
        for (int col = 0; col < 10; col++) {
            JLabel label = new JLabel(String.valueOf((char) ('A' + col)), SwingConstants.CENTER);
            label.setForeground(Color.WHITE); // Set text color to white
            gbc.gridx = col + 1;
            gbc.gridy = 0;
            panel.add(label, gbc);
        }

        // Adding row labels and buttons
        gridButtons = new JButton[10][10];
        for (int row = 0; row < 10; row++) {
            JLabel label = new JLabel(String.valueOf(row + 1), SwingConstants.CENTER);
            gbc.gridx = 0;
            gbc.gridy = row + 1;
            panel.add(label, gbc);

            for (int col = 0; col < 10; col++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(40, 40)); // Size of button
                button.setEnabled(false); // Disable the button
                button.setBackground(new Color(51, 204, 255)); // Set background color to light blue
                button.setBorder(new LineBorder(Color.WHITE)); // Set white border
                gridButtons[row][col] = button;
                gbc.gridx = col + 1;
                gbc.gridy = row + 1;
                panel.add(button, gbc);
            }
        }

        gameBoardFrame.add(panel);
        gameBoardFrame.pack();
        gameBoardFrame.setVisible(true);
        
    }
}