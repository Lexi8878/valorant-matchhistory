package ui.tabs;

import ui.BarChart;
import ui.ButtonNames;
import ui.MatchHistoryUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// tab where user can calculate their winrate or see their most played agent
public class WinrateAgentTab extends Tab {
    private static final String INIT_GREETING = "Calculate win rate or most played agent?";
    private JLabel greeting;
    private JButton b1;
    private JButton b2;
    private JPanel buttonRow;

    //REQUIRES: MatchHistoryUI controller that holds this tab
    //EFFECTS: creates winrate/agent tab with buttons message that shows either winrate or agent most played
    public WinrateAgentTab(MatchHistoryUI controller) {
        super(controller);

        setLayout(new GridLayout(3, 1));
        placeGreeting();
        placeCalculateButton();
    }

    // MODIFIES: this
    //EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
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
    //EFFECTS: creates winrate and agent buttons that change message when clicked
    private void placeCalculateButton() {
        init();

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

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.AGENT.getValue())) {
                    //String agent = getController().agentCommand();
                    //greeting.setText(agent);
                    JFrame.setDefaultLookAndFeelDecorated(true);
                    JFrame frame = new JFrame("Bar Chart");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(600, 400);

                    String title = "Number of wins on each agent";
                    //double[] values = new double[]{1,2,3,4,5};
                    double[] values = getController().getWinsOnAgents();
                    String[] labels = new String[]{"SOVA","PHOENIX","BRIMSTONE","SAGE","JETT"};
                    Color[] colors = new Color[]{
                            Color.red,
                            Color.orange,
                            Color.yellow,
                            Color.green,
                            Color.blue
                    };
                    BarChart bc = new BarChart(values, labels, colors, title);

                    frame.add(bc);
                    frame.setVisible(true);
                }
            }
        });

        this.add(buttonRow);
    }
}

