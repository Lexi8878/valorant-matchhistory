package ui;

import model.AgentType;
import model.Game;
import model.MatchHistory;

import java.util.Scanner;

import static model.AgentType.*;


// Match history application
public class MatchHistoryApp {
    private Game game;
    private MatchHistory matchHistory;
    private String userName;
    private Scanner input;

    // EFFECTS: Constructs a match history application with the user's name and runs the application
    public MatchHistoryApp(String userName) {
        this.userName = userName;
        runMatchHistory();
    }

    // MODIFIES: this
    // EFFECTS: Processes user input
    private void runMatchHistory() {
        boolean conditionTrue = true;
        String command = null;

        init();

        while (conditionTrue) {
            displayMenu();
            command = input.next();
            command = command.toUpperCase();

            if (command.equals("QUIT")) {
                conditionTrue = false;
                System.out.println("Quitting program..");
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Initializes user's games, match history and scanner
    private void init() {
        game = new Game("win", 13, 0, PHOENIX);
        matchHistory = new MatchHistory();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: Processes user's command
    private void processCommand(String command) {
        if (command.equals("ADD")) {
            doAddGame(matchHistory, game);
            System.out.println("Added a game to match history");
        } else if (command.equals("VIEW")) {
            String history = doDisplayMatches(matchHistory);
            System.out.println("Here is your match history:");
            System.out.println(userName + ": " + history);
        } else if (command.equals("CALCULATE")) {
            System.out.println("Calculating...");
            double wr = doCalculate(matchHistory);
            System.out.println("Your win rate is: " + wr + "%");
        } else if (command.equals("AGENT")) {
            int numGamesPlayed = doCalculateMostPlayedAgent(matchHistory, PHOENIX);
            System.out.println(numGamesPlayed);
        } else {
            System.out.println("Selection not valid");
        }
    }

    // EFFECTS: Displays menu to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1. ADD -> Add match");
        System.out.println("\t2. VIEW -> View match history");
        System.out.println("\t3. CALCULATE -> Calculate win rate");
        System.out.println("\t4. AGENT -> View your most played agent");
        System.out.println("\t5. QUIT -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: Adds a game to the user's match history
    private void doAddGame(MatchHistory mh, Game g) {
        mh.addGame(g);
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

    private int doCalculateMostPlayedAgent(MatchHistory mh, AgentType agent) {
        return mh.calculateNumGamesPlayedAgent(agent);
    }

}


