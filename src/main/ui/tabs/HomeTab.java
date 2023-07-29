package ui.tabs;

import ui.ButtonNames;
import ui.MatchHistoryUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeTab extends Tab {
    private static final String INIT_GREETING = "Welcome";
    private JLabel greeting;

    //EFFECTS: constructs a home tab for console with buttons and a greeting
    public HomeTab(MatchHistoryUI controller) {
        super(controller);

        setLayout(new GridLayout(3, 1));

        placeGreeting();
        placeHomeButtons();
        placeStatusButton();
    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    //EFFECTS: creates Add and View buttons that change greeting message when clicked
    private void placeHomeButtons() {
        JButton b1 = new JButton(ButtonNames.ADD.getValue());
        JButton b2 = new JButton(ButtonNames.WINRATE.getValue());

        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.add(b2);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(e -> {
            getController().getMatchHistoryApp().addCommand();
            greeting.setText("Adding a match!");
        });

        b2.addActionListener(e -> {
            getController().getMatchHistoryApp().winrateCommand();
            greeting.setText("Calculating win rate!");
        });

        this.add(buttonRow);
    }

    //EFFECTS: constructs a status button that switches to the view match history tab on the console
    private void placeStatusButton() {
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
