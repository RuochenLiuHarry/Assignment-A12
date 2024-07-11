package view;

import javax.swing.*;
import java.awt.*;

public class SettingsView extends JFrame {
    private JButton languageButton;
    private JButton soundButton;
    private JButton backButton;

    public SettingsView() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Game Settings");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(51, 204, 255));
        setLayout(new BorderLayout());

        ImageIcon logoIcon = new ImageIcon("logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        add(logoLabel, BorderLayout.NORTH);

        JLabel text = new JLabel("Click button to change the settings", JLabel.CENTER);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Arial", Font.BOLD, 30));
        add(text, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        buttonPanel.setBackground(new Color(51, 204, 255));

        languageButton = new JButton("Language");
        soundButton = new JButton("Sound");
        backButton = new JButton("Back");

        buttonPanel.add(languageButton);
        buttonPanel.add(soundButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    public JButton getLanguageButton() { return languageButton; }
    public JButton getSoundButton() { return soundButton; }
    public JButton getBackButton() { return backButton; }
}
