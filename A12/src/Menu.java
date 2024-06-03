
import java.awt.Dimension;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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
		setSize(new Dimension(1920, 1080));
		setMinimumSize(new Dimension(1920, 1080));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameBoard(); // Testing gameboard
        
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