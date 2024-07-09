import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class GameBoard {
    private JPanel panel;
    private JTextArea messageArea;
    private JTextField inputField;
    private JButton sendButton;
    private int player1Score;
    private int player2Score;
    private int hits;
    private int misses;
    private Player currentTurn;
    private JLabel turnLabel;
    private JButton[][] gridButtons;

    public GameBoard() {
        initializeBoard();
    }

    public void initializeBoard() {
        JFrame gameBoardFrame = new JFrame("Game Board");
        gameBoardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameBoardFrame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(51, 204, 255)); // Light blue

        // Top panel for the Title
        ImageIcon titleImage = new ImageIcon("logo.png"); 
        JLabel titleLabel = new JLabel(titleImage);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Left panel for (Score board)
        JPanel leftPanel = new JPanel(new GridLayout(1, 1)); // 2 rows, 1 col
        leftPanel.setPreferredSize(new Dimension(200, 0)); // Width left panel 200 pixels, 0 height

        // (Scoreboard) panel
        JPanel scoreBoardPanel = new JPanel();
        scoreBoardPanel.setBackground(new Color(0, 51, 102)); // Dark blue
        scoreBoardPanel.setBorder(new LineBorder(Color.WHITE, 2)); // Border, 2 thickness 
        JLabel scoreBoardLabel = new JLabel("Score board");
        scoreBoardLabel.setForeground(Color.WHITE);
        scoreBoardPanel.add(scoreBoardLabel);

        // Adding components to panels
        leftPanel.add(scoreBoardPanel);
        mainPanel.add(leftPanel, BorderLayout.WEST);

        // Right panel for (Chat)
        JPanel rightPanel = new JPanel(new GridLayout(1, 1)); // 2 rows, 1 col
        rightPanel.setPreferredSize(new Dimension(200, 0)); // Width right panel, 200 pixels

        // (Chat) panel
        JPanel chatPanel = new JPanel();
        chatPanel.setBackground(new Color(0, 51, 102)); // Dark blue
        chatPanel.setBorder(new LineBorder(Color.WHITE, 2)); // Border, 2 thickness 
        JLabel chatLabel = new JLabel("Chat");
        chatLabel.setForeground(Color.WHITE);
        chatPanel.add(chatLabel);

        // Adding components to panels
        rightPanel.add(chatPanel);
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
        String[] colLabel = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for (int col = 0; col < 10; col++) {
            JLabel label = new JLabel(colLabel[col]);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setForeground(Color.WHITE); // Text color white
            gridPanel.add(label);
        }

        gridButtons = new JButton[10][10];
        // Row label
        for (int row = 0; row < 10; row++) {
            JLabel label = new JLabel(String.valueOf(row + 1));
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setForeground(Color.BLACK); // Text color black
            gridPanel.add(label);
            
            // Grid
            for (int col = 0; col < 10; col++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(40, 40)); // Size of button
                button.setBackground(new Color(51, 204, 255)); // Light blue
                button.setBorder(new LineBorder(Color.WHITE)); // White border
                gridButtons[row][col] = button;
                gridPanel.add(button);
            }
        }

        // Sample ship placement (for demonstration purposes)
        gridButtons[2][2].setBackground(Color.GRAY); // Vertically placed ship part 1
        gridButtons[3][2].setBackground(Color.GRAY); // Vertically placed ship part 2
        gridButtons[4][2].setBackground(Color.GRAY); // Vertically placed ship part 3

        gridButtons[7][5].setBackground(Color.GRAY); // Horizontally placed ship part 1
        gridButtons[7][6].setBackground(Color.GRAY); // Horizontally placed ship part 2
        gridButtons[7][7].setBackground(Color.GRAY); // Horizontally placed ship part 3

        mainPanel.add(gridPanel, BorderLayout.CENTER);
        gameBoardFrame.add(mainPanel);
        gameBoardFrame.pack();
        gameBoardFrame.setVisible(true);
    }

    public void updateBoard(Player player, int x, int y) {
        // Logic to update the game board with the player's move
        if (gridButtons[x][y].getBackground() == Color.GRAY) {
            gridButtons[x][y].setBackground(Color.RED); // Hit
        } else {
            gridButtons[x][y].setBackground(Color.WHITE); // Miss
        }
    }

    public JButton[][] getGridButtons() {
        return gridButtons;
    }

    public void sendMessage(String message) {
        // Send a message through the chat
    }

    public void receiveMessage() {
        // Receive a message through the chat
    }

    public void updateScores(int player1Score, int player2Score) {
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        // Update the scores on the scoreboard
    }

    public void displayTurn() {
        // Display the current turn
    }
}
