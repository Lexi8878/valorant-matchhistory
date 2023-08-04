package ui.tabs;

import ui.ButtonNames;
import ui.MatchHistoryUI;

import javax.swing.*;
import java.awt.*;

// Tab where user can switch between light and dark mode themes
public class LightDarkTab extends Tab {
    private static final String INIT_MESSAGE = "Change to light or dark mode";
    private JLabel message;

    // REQUIRES: MatchHistoryUI controller that holds this tab
    // EFFECTS: constructs a light/dark mode tab with buttons and message
    public LightDarkTab(MatchHistoryUI controller) {
        super(controller);

        setLayout(new GridLayout(3, 1));

        placeMessage();
        placeLightDarkButtons();
    }

    // MODIFIES: this
    // EFFECTS: creates message at top of console
    private void placeMessage() {
        message = new JLabel(INIT_MESSAGE, JLabel.CENTER);
        message.setSize(WIDTH, HEIGHT / 3);
        this.add(message);
    }

    // MODIFIES: this
    //EFFECTS: creates light and dark mode buttons that change message when clicked and changes colour of panel
    private void placeLightDarkButtons() {
        JButton b1 = new JButton(ButtonNames.LIGHT.getValue());
        JButton b2 = new JButton(ButtonNames.DARK.getValue());

        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.add(b2);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(e -> {
            getController().changeThemeLight();
            message.setText("Setting theme to light mode!");
        });

        b2.addActionListener(e -> {
            getController().changeThemeDark();
            message.setText("Setting theme to dark mode!");
        });

        this.add(buttonRow);
    }
}
