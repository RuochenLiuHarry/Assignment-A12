package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameBoardView extends JFrame {
    private JPanel gridPanel;
    private JButton[][] gridButtons;
    private JButton[][] computerGridButtons;
    private JLabel turnLabel;
    private JButton surrenderButton;
    private JButton settingButton;
    private JButton swapButton;
    private JButton quitButton;
    private JLabel player1ScoreLabel;
    private JLabel player2ScoreLabel;
    private boolean showingPlayerBoard;

    public GameBoardView() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Battleship");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel for logo
        ImageIcon logoIcon = new ImageIcon("logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        add(logoLabel, BorderLayout.NORTH);

        // Center panel for game board
        gridPanel = new JPanel(new GridLayout(11, 11, 2, 2));
        gridPanel.setBackground(new Color(51, 204, 255));

        gridButtons = new JButton[10][10];
        computerGridButtons = new JButton[10][10];
        String[] colLabel = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

        gridPanel.add(new JLabel("")); // Empty top-left corner
        for (String label : colLabel) {
            gridPanel.add(new JLabel(label, SwingConstants.CENTER));
        }

        for (int i = 0; i < 10; i++) {
            gridPanel.add(new JLabel(String.valueOf(i + 1), SwingConstants.CENTER));
            for (int j = 0; j < 10; j++) {
                gridButtons[i][j] = new JButton();
                gridButtons[i][j].setPreferredSize(new Dimension(40, 40));
                gridButtons[i][j].setBackground(new Color(51, 204, 255));
                gridButtons[i][j].setBorder(new LineBorder(Color.WHITE));
                gridPanel.add(gridButtons[i][j]);

                computerGridButtons[i][j] = new JButton();
                computerGridButtons[i][j].setPreferredSize(new Dimension(40, 40));
                computerGridButtons[i][j].setBackground(new Color(51, 204, 255));
                computerGridButtons[i][j].setBorder(new LineBorder(Color.WHITE));
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        // Bottom panel for buttons
        JPanel bottomPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        bottomPanel.setBackground(new Color(0, 51, 102));

        surrenderButton = new JButton("Surrender");
        settingButton = new JButton("Setting");
        swapButton = new JButton("Swap");
        quitButton = new JButton("Quit");

        bottomPanel.add(surrenderButton);
        bottomPanel.add(settingButton);
        bottomPanel.add(swapButton);
        bottomPanel.add(quitButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Left panel for score
        JPanel leftPanel = new JPanel(new GridLayout(2, 1));
        leftPanel.setPreferredSize(new Dimension(200, 0));
        leftPanel.setBackground(new Color(0, 51, 102));
        player1ScoreLabel = new JLabel("Player 1: 0");
        player2ScoreLabel = new JLabel("Player 2: 0");
        player1ScoreLabel.setForeground(Color.WHITE);
        player2ScoreLabel.setForeground(Color.WHITE);
        leftPanel.add(player1ScoreLabel);
        leftPanel.add(player2ScoreLabel);

        add(leftPanel, BorderLayout.WEST);

        // Right panel for turn display
        JPanel rightPanel = new JPanel(new GridLayout(1, 1));
        rightPanel.setPreferredSize(new Dimension(200, 0));
        rightPanel.setBackground(new Color(0, 51, 102));
        turnLabel = new JLabel("Current Turn: Player 1");
        turnLabel.setForeground(Color.WHITE);
        rightPanel.add(turnLabel);

        add(rightPanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);

        showingPlayerBoard = true;
        showPlayerBoard();  // Initially show player board
    }

    public JButton[][] getGridButtons() { return gridButtons; }
    public JButton[][] getComputerGridButtons() { return computerGridButtons; }
    public JButton getSurrenderButton() { return surrenderButton; }
    public JButton getSettingButton() { return settingButton; }
    public JButton getSwapButton() { return swapButton; }
    public JButton getQuitButton() { return quitButton; }

    public void updateTurnLabel(String playerName) {
        turnLabel.setText("Current Turn: " + playerName);
    }

    public void updateScores(int player1Score, int player2Score) {
        player1ScoreLabel.setText("Player 1: " + player1Score);
        player2ScoreLabel.setText("Player 2: " + player2Score);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showPlayerBoard() {
        remove(gridPanel);
        gridPanel = new JPanel(new GridLayout(11, 11, 2, 2));
        gridPanel.setBackground(new Color(51, 204, 255));

        gridPanel.add(new JLabel("")); // Empty top-left corner
        String[] colLabel = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for (String label : colLabel) {
            gridPanel.add(new JLabel(label, SwingConstants.CENTER));
        }

        for (int i = 0; i < 10; i++) {
            gridPanel.add(new JLabel(String.valueOf(i + 1), SwingConstants.CENTER));
            for (int j = 0; j < 10; j++) {
                gridPanel.add(gridButtons[i][j]);
            }
        }

        add(gridPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void showComputerBoard() {
        remove(gridPanel);
        gridPanel = new JPanel(new GridLayout(11, 11, 2, 2));
        gridPanel.setBackground(new Color(51, 204, 255));

        gridPanel.add(new JLabel("")); // Empty top-left corner
        String[] colLabel = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for (String label : colLabel) {
            gridPanel.add(new JLabel(label, SwingConstants.CENTER));
        }

        for (int i = 0; i < 10; i++) {
            gridPanel.add(new JLabel(String.valueOf(i + 1), SwingConstants.CENTER));
            for (int j = 0; j < 10; j++) {
                gridPanel.add(computerGridButtons[i][j]);
            }
        }

        add(gridPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void toggleBoard() {
        if (showingPlayerBoard) {
            showComputerBoard();
        } else {
            showPlayerBoard();
        }
        showingPlayerBoard = !showingPlayerBoard;
    }

    public boolean isShowingPlayerBoard() {
        return showingPlayerBoard;
    }
}
