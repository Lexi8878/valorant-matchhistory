package ui.tabs;

import ui.ButtonNames;
import ui.MatchHistoryUI;

import javax.swing.*;
import java.awt.*;

// Tab where user can save and load their current match history
public class SaveLoadTab extends Tab {
    private static final String INIT_GREETING = "Save or load match history?";
    private JLabel greeting;

    //REQUIRES: MatchHistoryUI controller that holds this tab
    //EFFECTS: constructs a save/load tab with buttons and greeting
    public SaveLoadTab(MatchHistoryUI controller) {
        super(controller);

        setLayout(new GridLayout(3, 1));

        placeGreeting();
        placeSaveLoadButtons();
    }

    // MODIFIES: this
    //EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    // MODIFIES: this
    //EFFECTS: creates save and load buttons that change message when clicked and saves/loads match history
    private void placeSaveLoadButtons() {
        JButton b1 = new JButton(ButtonNames.SAVE.getValue());
        JButton b2 = new JButton(ButtonNames.LOAD.getValue());

        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.add(b2);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(e -> {
            getController().saveMatchHistory();
            greeting.setText("Saving match history!");
        });

        b2.addActionListener(e -> {
            getController().loadMatchHistory();
            greeting.setText("Loading match history!");
        });

        this.add(buttonRow);
    }
}
