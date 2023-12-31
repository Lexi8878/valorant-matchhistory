package ui.tabs;

import ui.BarChart;
import ui.ButtonNames;
import ui.MatchHistoryUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a tab where user can calculate their win rate or see their most played agent
public class WinrateAgentTab extends Tab {
    private static final String INIT_MESSAGE = "Calculate win rate or see most played agents?";
    private JLabel message;
    private JButton b1;
    private JButton b2;
    private JPanel buttonRow;
    private JFrame frame;
    private BarChart bar;
    private String title;

    // REQUIRES: MatchHistoryUI controller that holds this tab
    // EFFECTS: creates winrate/agent tab with buttons message that shows either winrate or agent most played
    public WinrateAgentTab(MatchHistoryUI controller) {
        super(controller);

        setLayout(new GridLayout(3, 1));
        placeMessage();
        placeCalculateButton();
    }

    // MODIFIES: this
    // EFFECTS: creates message at top of console
    private void placeMessage() {
        message = new JLabel(INIT_MESSAGE, JLabel.CENTER);
        message.setSize(WIDTH, HEIGHT / 3);
        this.add(message);
    }

    // MODIFIES: this
    // EFFECTS: initializes buttons and panel
    private void init() {
        b1 = new JButton(ButtonNames.WINRATE.getValue());
        b2 = new JButton(ButtonNames.AGENT.getValue());

        buttonRow = formatButtonRow(b1);
        buttonRow.add(b2);
        buttonRow.setSize(WIDTH, HEIGHT / 6);
    }

    // MODIFIES: this
    // EFFECTS: initializes bar chart when button clicked
    private void initBar() {
        frame = new JFrame("Bar Chart");
        frame.setSize(600, 400);

        title = "Number of games on each agent";
        String[] labels = new String[]{"SOVA", "PHOENIX", "BRIMSTONE", "SAGE", "JETT"};
        double[] values = getController().getGamesOnAgents(getController().getMatchHistory());
        Color[] colours = new Color[]{Color.cyan, Color.yellow, Color.magenta, Color.pink, Color.blue};
        bar = new BarChart(title, labels, values, colours);
    }

    // MODIFIES: this
    //EFFECTS: creates winrate and agent buttons that change message when clicked
    private void placeCalculateButton() {
        init();

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.WINRATE.getValue())) {
                    String wr = getController().winrateCommand();
                    message.setText(wr);
                }
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.AGENT.getValue())) {
                    initBar();

                    frame.add(bar);
                    frame.setVisible(true);
                }
            }
        });

        this.add(buttonRow);
    }
}

