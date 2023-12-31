package ui.tabs;

import ui.ButtonNames;
import ui.MatchHistoryUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the home tab of the match history application with buttons that sends user to different tabs
public class HomeTab extends Tab {
    private static final String INIT_GREETING = "Welcome";
    private JLabel greeting;

    // REQUIRES: MatchHistoryUI controller that holds this tab
    // EFFECTS: constructs a home tab for console with buttons and a greeting
    public HomeTab(MatchHistoryUI controller) {
        super(controller);

        setLayout(new GridLayout(4, 1));

        placeGreeting();
        placeAddButton();
        placeWinrateButton();
        placeViewButton();
    }

    // MODIFIES: this
    // EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    // MODIFIES: this
    // EFFECTS: constructs an add button that switches to the add tab on the console
    private void placeAddButton() {
        JPanel addBlock = new JPanel();
        JButton addButton = new JButton(ButtonNames.GO_TO_ADD.getValue());
        addBlock.add(formatButtonRow(addButton));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.GO_TO_ADD.getValue())) {
                    getController().getTabbedPane().setSelectedIndex(MatchHistoryUI.ADD_TAB_INDEX);
                }
            }
        });

        this.add(addBlock);
    }

    // MODIFIES: this
    // EFFECTS: constructs a winrate button that switches to the winrate/agent tab on the console
    private void placeWinrateButton() {
        JPanel winrateBlock = new JPanel();
        JButton winrateButton = new JButton(ButtonNames.GO_TO_WINRATE.getValue());
        winrateBlock.add(formatButtonRow(winrateButton));

        winrateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.GO_TO_WINRATE.getValue())) {
                    getController().getTabbedPane().setSelectedIndex(MatchHistoryUI.WINRATE_AGENT_TAB_INDEX);
                }
            }
        });

        this.add(winrateBlock);
    }

    // MODIFIES: this
    // EFFECTS: constructs a view button that switches to the view match history tab on the console
    private void placeViewButton() {
        JPanel statusBlock = new JPanel();
        JButton statusButton = new JButton(ButtonNames.GO_TO_MATCH_HISTORY.getValue());
        statusBlock.add(formatButtonRow(statusButton));

        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.GO_TO_MATCH_HISTORY.getValue())) {
                    getController().getTabbedPane().setSelectedIndex(MatchHistoryUI.VIEW_TAB_INDEX);
                }
            }
        });

        this.add(statusBlock);
    }
}
