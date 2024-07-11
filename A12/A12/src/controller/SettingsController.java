package controller;

import view.SettingsView;

public class SettingsController {
    private SettingsView view;

    public SettingsController(SettingsView view) {
        this.view = view;
        initializeListeners();
    }

    private void initializeListeners() {
        view.getLanguageButton().addActionListener(e -> changeLanguage());
        view.getSoundButton().addActionListener(e -> changeSound());
        view.getBackButton().addActionListener(e -> goBack());
    }

    private void changeLanguage() {
        // Implement language change logic
    }

    private void changeSound() {
        // Implement sound settings change logic
    }

    private void goBack() {
        view.dispose();
    }
}