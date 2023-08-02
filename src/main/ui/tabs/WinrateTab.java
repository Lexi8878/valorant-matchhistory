package ui.tabs;

import ui.ButtonNames;
import ui.MatchHistoryUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinrateTab extends Tab {
    private JLabel greeting;

    //REQUIRES: MatchHistoryUI controller that holds this tab
    //EFFECTS: creates report tab with buttons and application status functionality
    public WinrateTab(MatchHistoryUI controller) {
        super(controller);

        placeGreeting();
        placeCalculateButton();
    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel("Your win rate is: ", JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    //MODIFIES: this
    //EFFECTS: adds a generate report button that prints app status when clicked
    private void placeCalculateButton() {
        JButton b1 = new JButton(ButtonNames.WINRATE.getValue());
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(b1,BorderLayout.SOUTH);
        panel.setVisible(true);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.WINRATE.getValue())) {
                    String wr = getController().winrateCommand();
                    greeting.setText(wr);
                }
            }
        });

        this.add(panel);
    }
}

