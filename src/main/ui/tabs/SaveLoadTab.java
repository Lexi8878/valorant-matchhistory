package ui.tabs;

import ui.ButtonNames;
import ui.MatchHistoryUI;

import javax.swing.*;
import java.awt.*;

public class SaveLoadTab extends Tab {
    private static final String INIT_GREETING = "Save or load match history?";
    private JLabel greeting;

    //EFFECTS: constructs a home tab for console with buttons and a greeting
    public SaveLoadTab(MatchHistoryUI controller) {
        super(controller);

        setLayout(new GridLayout(3, 1));

        placeGreeting();
        placeSaveLoadButtons();
    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    //EFFECTS: creates Add and View buttons that change greeting message when clicked
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
