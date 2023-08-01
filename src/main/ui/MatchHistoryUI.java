package ui;

import model.AgentType;
import model.Game;
import model.MatchHistory;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.HomeTab;
import ui.tabs.SaveLoadTab;
import ui.tabs.ViewTab;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static model.AgentType.*;

public class MatchHistoryUI extends JFrame {
    public static final int HOME_TAB_INDEX = 0;
    public static final int VIEW_TAB_INDEX = 1;
    public static final int SAVE_LOAD_TAB_INDEX = 2;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private JTabbedPane sidebar;
    private MatchHistory matchHistory;
    private Scanner input;
    private String userName;
    private AgentType agent;
    private static final String JSON_STORE = "./data/matchhistory.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public static void main(String[] args) {
        try {
            new MatchHistoryUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }

    //MODIFIES: this
    //EFFECTS: creates MatchHistoryUI, loads SmartHome appliances, displays sidebar and tabs
    private MatchHistoryUI() throws FileNotFoundException {
        super("MatchHistory Console");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        matchHistory = new MatchHistory();
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        System.out.println("Welcome");
        System.out.println("Enter username: ");
        userName = input.next();

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);
        loadTabs();
        add(sidebar);

        setVisible(true);


        //try {
            //matchHistoryApp = new MatchHistoryApp();
        //} catch (FileNotFoundException e) {
            //System.out.println("Unable to run application: file not found");
        //}
    }

    //EFFECTS: returns Match History object controlled by this UI
    public MatchHistory getMatchHistory() {
        return matchHistory;
    }

    //EFFECTS: returns username
    public String getUserName() {
        return userName;
    }

    // MODIFIES: this
    // EFFECTS: takes user's input for game details and adds that game into match history
    public void addCommand() {
        System.out.println("Enter win or lose: ");
        String gameStatus = input.next().toLowerCase();
        System.out.println("Enter your team's points: ");
        int points = input.nextInt();
        System.out.println("Enter the enemy team's points: ");
        int enemyPoints = input.nextInt();
        System.out.print("Enter name of agent:");
        String played = input.next().toUpperCase();
        AgentType gameType = getType(played);
        doAddGame(matchHistory, new Game(gameStatus, points, enemyPoints, gameType));
        System.out.println("Added a game to match history");
    }

    // REQUIRES: match history must not be empty
    // EFFECTS: takes user's input and displays how many games the specified agent was played
    private void agentCommand() {
        System.out.print("Enter name of agent:");
        String type = input.next().toUpperCase();
        int numGamesPlayed = doCalculateNumGamesPlayedAgent(matchHistory, type);
        System.out.println(type + ": " + numGamesPlayed);
    }

    // EFFECTS: calculates and displays user's win rate percent
    public void winrateCommand() {
        System.out.println("Calculating...");
        double wr = doCalculate(matchHistory);
        System.out.println("Your win rate is: " + wr + "%");
    }

    // REQUIRES: match history must not be empty
    // EFFECTS: displays user's most recent game in match history
    public void viewCommand() {
        String history = doDisplayMatches(matchHistory);
        System.out.println("Here is your match history:");
        System.out.println(userName + ": " + history);
    }

    // MODIFIES: this
    // EFFECTS: Displays the user's match history
    private String doDisplayMatches(MatchHistory mh) {
        return mh.getDisplay();
    }

    // MODIFIES: this
    // EFFECTS: Gets the user's win rate
    private double doCalculate(MatchHistory mh) {
        return mh.calculateWinRate();
    }

    // MODIFIES: this
    // EFFECTS: Adds a game to the user's match history
    private void doAddGame(MatchHistory mh, Game g) {
        mh.addGame(g);
    }

    private int doCalculateNumGamesPlayedAgent(MatchHistory mh, String type) {
        getType(type);
        return mh.calculateNumGamesPlayedAgent(agent);
    }

    // EFFECTS: saves match history to file
    public void saveMatchHistory() {
        try {
            jsonWriter.open();
            jsonWriter.write(matchHistory);
            jsonWriter.close();
            System.out.println("Saved " + userName + "'s match history" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads match history from file
    public void loadMatchHistory() {
        try {
            matchHistory = jsonReader.read();
            System.out.println("Loaded " + userName + "'s match history" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    private AgentType getType(String type) {
        switch (type) {
            case "SOVA":
                this.agent = SOVA;
                break;
            case "SAGE":
                this.agent = SAGE;
                break;
            case "PHOENIX":
                this.agent = PHOENIX;
                break;
            case "BRIMSTONE":
                this.agent = BRIMSTONE;
                break;
            default:
                this.agent = JETT;
                break;
        }
        return agent;
    }

    //MODIFIES: this
    //EFFECTS: adds home tab, view tab, save/load tab to this UI
    private void loadTabs() {
        JPanel homeTab = new HomeTab(this);
        JPanel viewTab = new ViewTab(this);
        JPanel quitTab = new SaveLoadTab(this);

        sidebar.add(homeTab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, "Home");
        sidebar.add(viewTab, VIEW_TAB_INDEX);
        sidebar.setTitleAt(VIEW_TAB_INDEX, "View");
        sidebar.add(quitTab, SAVE_LOAD_TAB_INDEX);
        sidebar.setTitleAt(SAVE_LOAD_TAB_INDEX, "Save/Load");
    }

    //EFFECTS: returns sidebar of this UI
    public JTabbedPane getTabbedPane() {
        return sidebar;
    }
}
