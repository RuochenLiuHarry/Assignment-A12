import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard extends JFrame {
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
    private JButton[][] computerGridButtons;
    private int shipsToPlace = 5;
    private List<Ship> playerShips;
    private List<Ship> computerShips;
    private int currentShipIndex = 0;
    private Player player1;
    private Player player2;
    private boolean placingShips = true;
    private boolean gameStarted = false;
    private ImageIcon hitIcon;
    private ImageIcon missIcon;

    public GameBoard(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        playerShips = new ArrayList<>();
        computerShips = new ArrayList<>();
        hitIcon = new ImageIcon("hit.png");
        missIcon = new ImageIcon("miss.png");
        initializeShips();
        initializeBoard();
    }

    private void initializeShips() {
        playerShips.add(new Ship("Aircraft Carrier", 5, new ImageIcon("bow_west.png"), new ImageIcon("midhull_horiz.png"), new ImageIcon("bow_east.png")));
        playerShips.add(new Ship("Battleship", 4, new ImageIcon("bow_north.png"), new ImageIcon("midhull_vert.png"), new ImageIcon("bow_south.png")));
        playerShips.add(new Ship("Submarine", 3, new ImageIcon("bow_north.png"), new ImageIcon("midhull_vert.png"), new ImageIcon("bow_south.png")));
        playerShips.add(new Ship("Cruiser", 3, new ImageIcon("bow_north.png"), new ImageIcon("midhull_vert.png"), new ImageIcon("bow_south.png")));
        playerShips.add(new Ship("Destroyer", 2, new ImageIcon("bow_west.png"), new ImageIcon("midhull_horiz.png"), new ImageIcon("bow_east.png")));

        computerShips.add(new Ship("Aircraft Carrier", 5, new ImageIcon("bow_west.png"), new ImageIcon("midhull_horiz.png"), new ImageIcon("bow_east.png")));
        computerShips.add(new Ship("Battleship", 4, new ImageIcon("bow_north.png"), new ImageIcon("midhull_vert.png"), new ImageIcon("bow_south.png")));
        computerShips.add(new Ship("Submarine", 3, new ImageIcon("bow_west.png"), new ImageIcon("midhull_horiz.png"), new ImageIcon("bow_east.png")));
        computerShips.add(new Ship("Cruiser", 3, new ImageIcon("bow_north.png"), new ImageIcon("midhull_vert.png"), new ImageIcon("bow_south.png")));
        computerShips.add(new Ship("Destroyer", 2, new ImageIcon("bow_west.png"), new ImageIcon("midhull_horiz.png"), new ImageIcon("bow_east.png")));
    }

    public void initializeBoard() {
        setTitle("Game Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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
        swapButton.addActionListener(e -> {
            if (gameStarted) {
                swapBoards();
            } else if (shipsToPlace == 0) {
                swapBoards();
                gameStarted = true;
            } else {
                JOptionPane.showMessageDialog(this, "Place all ships before starting the game.");
            }
        });
        JButton quitButton = new JButton("Quit");
        bottomPanel.add(surrenderButton);
        bottomPanel.add(settingButton);
        bottomPanel.add(swapButton);
        bottomPanel.add(quitButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Center panel for the game board grid
        JPanel gridPanel = new JPanel(new GridLayout(11, 11, 2, 2)); // Adjusted the layout to 11x11
        gridPanel.setBackground(new Color(51, 204, 255)); // Light blue

        // Empty label in the top-left corner
        gridPanel.add(new JLabel(""));

        // Column labels
        String[] colLabel = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for (int col = 0; col < 10; col++) {
            JLabel label = new JLabel(colLabel[col], SwingConstants.CENTER);
            label.setForeground(Color.WHITE); // Text color white
            gridPanel.add(label);
        }

        gridButtons = new JButton[10][10];
        computerGridButtons = new JButton[10][10];
        // Row label
        for (int row = 0; row < 10; row++) {
            JLabel label = new JLabel(String.valueOf(row + 1), SwingConstants.CENTER);
            label.setForeground(Color.BLACK); // Text color black
            gridPanel.add(label);

            // Grid
            for (int col = 0; col < 10; col++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(40, 40)); // Size of button
                button.setBackground(new Color(51, 204, 255)); // Light blue
                button.setBorder(new LineBorder(Color.WHITE)); // White border
                int finalRow = row;
                int finalCol = col;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (placingShips) {
                            placePlayerShip(finalRow, finalCol);
                        } else if (gameStarted && currentTurn == player1) {
                            makeMove(finalRow, finalCol);
                        }
                    }
                });
                gridButtons[row][col] = button;
                computerGridButtons[row][col] = new JButton();
                computerGridButtons[row][col].setPreferredSize(new Dimension(40, 40)); // Size of button
                computerGridButtons[row][col].setBackground(new Color(51, 204, 255)); // Light blue
                computerGridButtons[row][col].setBorder(new LineBorder(Color.WHITE)); // White border
                gridPanel.add(button);
            }
        }

        mainPanel.add(gridPanel, BorderLayout.CENTER);
        add(mainPanel);
        pack();
    }

    private void placePlayerShip(int row, int col) {
        if (currentShipIndex >= playerShips.size()) {
            return;
        }
        Ship currentShip = playerShips.get(currentShipIndex);
        boolean horizontal = isShipHorizontal(currentShipIndex, false);
        // Example placement logic: just mark the button and reduce the ship count
        if (canPlaceShip(row, col, currentShip.getSize(), horizontal)) {
            currentShip.placeShip(this, row, col, horizontal, false);
            shipsToPlace--;
            currentShipIndex++;
            if (shipsToPlace == 0) {
                placeComputerShips();
                placingShips = false;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ships cannot overlap each other. Try again.");
        }
    }

    private void placeComputerShips() {
        // Simple AI logic for placing ships randomly
        Random random = new Random();
        for (int i = 0; i < computerShips.size(); i++) {
            Ship ship = computerShips.get(i);
            boolean placed = false;
            boolean horizontal = isShipHorizontal(i, true);
            while (!placed) {
                int startX = random.nextInt(10);
                int startY = random.nextInt(10);
                if (canPlaceShip(startX, startY, ship.getSize(), horizontal)) {
                    ship.placeShip(this, startX, startY, horizontal, true);
                    placed = true;
                }
            }
        }
    }

    private boolean canPlaceShip(int startX, int startY, int size, boolean horizontal) {
        if (horizontal) {
            if (startY + size > 10) return false;
            for (int i = 0; i < size; i++) {
                if (gridButtons[startX][startY + i].getIcon() != null) {
                    return false;
                }
            }
        } else {
            if (startX + size > 10) return false;
            for (int i = 0; i < size; i++) {
                if (gridButtons[startX + i][startY].getIcon() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isShipHorizontal(int shipIndex, boolean isComputer) {
        // Define the orientation of the ships based on index and player/computer
        if (isComputer) {
            // Computer's ships orientations
            switch (shipIndex) {
                case 0: return true; // Aircraft Carrier
                case 1: return false; // Battleship
                case 2: return true; // Submarine
                case 3: return false; // Cruiser
                case 4: return true; // Destroyer
                default: return true;
            }
        } else {
            // Player's ships orientations
            switch (shipIndex) {
                case 0: return true; // Aircraft Carrier
                case 1: return false; // Battleship
                case 2: return false; // Submarine
                case 3: return false; // Cruiser
                case 4: return true; // Destroyer
                default: return true;
            }
        }
    }

    public void updateBoard(Player player, int x, int y) {
        // Logic to update the game board with the player's move
        if (player == player1) {
            if (gridButtons[x][y].getIcon() == hitIcon || gridButtons[x][y].getIcon() == missIcon) {
                JOptionPane.showMessageDialog(this, "This cell was already hit. Try again.");
                return;
            }
            if (computerGridButtons[x][y].getIcon() != null) {
                gridButtons[x][y].setIcon(hitIcon); // Hit
                computerGridButtons[x][y].setIcon(hitIcon); // Mark the computer's grid as well
                currentTurn = player1; // Player 1 can continue hitting
            } else {
                gridButtons[x][y].setIcon(missIcon); // Miss
                computerGridButtons[x][y].setIcon(missIcon); // Mark the computer's grid as well
                JOptionPane.showMessageDialog(this, "Miss! Click 'Swap' to end your turn.");
                currentTurn = player2; // Switch to computer's turn
            }
        } else {
            if (gridButtons[x][y].getIcon() == hitIcon || gridButtons[x][y].getIcon() == missIcon) {
                player2.makeComputerMove(this); // Computer should not hit an already hit cell
            } else if (gridButtons[x][y].getIcon() != null) {
                gridButtons[x][y].setIcon(hitIcon); // Hit
                computerGridButtons[x][y].setIcon(hitIcon); // Mark the computer's grid as well
                currentTurn = player2; // Computer can continue hitting
            } else {
                gridButtons[x][y].setIcon(missIcon); // Miss
                computerGridButtons[x][y].setIcon(missIcon); // Mark the computer's grid as well
                currentTurn = player1; // Switch to player's turn
                swapBoards(); // Automatically swap to player's board
            }
        }
    }

    public void makeMove(int row, int col) {
        // Logic to make a move
        updateBoard(player1, row, col);
    }

    private void swapBoards() {
        if (currentTurn == player1) {
            // Hide Player 1's ships and show computer's board
            for (int row = 0; row < 10; row++) {
                for (int col = 0; col < 10; col++) {
                    if (gridButtons[row][col].getIcon() != hitIcon && gridButtons[row][col].getIcon() != missIcon) {
                        gridButtons[row][col].setIcon(null); // Hide Player 1's ships
                    }
                }
            }
            currentTurn = player2;
            player2.makeComputerMove(this); // Computer takes its turn
        } else {
            // Show Player 1's ships and hide computer's board
            for (int row = 0; row < 10; row++) {
                for (int col = 0; col < 10; col++) {
                    if (computerGridButtons[row][col].getIcon() == null) {
                        gridButtons[row][col].setIcon(null); // Show Player 1's ships
                    }
                }
            }
            currentTurn = player1;
        }
    }

    public JButton[][] getGridButtons() {
        return gridButtons;
    }

    public JButton[][] getComputerGridButtons() {
        return computerGridButtons;
    }

    public ImageIcon getHitIcon() {
        return hitIcon;
    }

    public ImageIcon getMissIcon() {
        return missIcon;
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

    public void playTurn() {
        // Logic to handle turns, this is where you can implement the game loop
    }
}


