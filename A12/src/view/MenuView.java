package view;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JFrame {
    private JButton pveButton;
    private JButton pvpButton;
    private JButton settingsButton;
    private JButton quitButton;

    public MenuView() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Battleship");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(51, 204, 255));
        setLayout(new BorderLayout());

        ImageIcon logoIcon = new ImageIcon("logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        add(logoLabel, BorderLayout.NORTH);

        JLabel text = new JLabel("Click button to start the game", JLabel.CENTER);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Arial", Font.BOLD, 30));
        add(text, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 0, 10));
        buttonPanel.setBackground(new Color(51, 204, 255));

        pveButton = new JButton("PVE");
        pvpButton = new JButton("PVP");
        settingsButton = new JButton("Settings");
        quitButton = new JButton("Quit");

        buttonPanel.add(pveButton);
        buttonPanel.add(pvpButton);
        buttonPanel.add(settingsButton);
        buttonPanel.add(quitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    public JButton getPveButton() { return pveButton; }
    public JButton getPvpButton() { return pvpButton; }
    public JButton getSettingsButton() { return settingsButton; }
    public JButton getQuitButton() { return quitButton; }
}