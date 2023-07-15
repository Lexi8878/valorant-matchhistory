package model;

import java.util.ArrayList;
import java.util.List;

// Represents a user's match history, that stores all the games a user has played
public class MatchHistory {
    private int numGamesWon;
    private List<Game> games;

    // EFFECTS: Constructs a match history with an empty list of games
    public MatchHistory() {
        this.games = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds a single game to the user's match history
    public void addGame(Game g) {
        this.games.add(g);
    }

    // EFFECTS: Displays the details of the most recent game in the match history
    public String getDisplay() {
        String history = null;
        for (Game g: games) {
            String s = g.getStatus();
            String tp = Integer.toString(g.getTeamPoints());
            String ep = Integer.toString(g.getEnemyPoints());
            history = s.toUpperCase() + " " + tp + "-" + ep;
        }
        return history;
    }

    // EFFECTS: Gets the number of games played in a match history
    public int getNumGames() {
        return this.games.size();
    }

    // EFFECTS: Gets the number of games won in a match history
    public int getNumGamesWon() {
        numGamesWon = 0;
        for (Game g: games) {
            if (g.wasGameWon()) {
                numGamesWon++;
            }
        }
        return numGamesWon;
    }

    // REQUIRES: Match history must contain at least one game
    // EFFECTS: Calculates user's average win rate based off of the games that have been
    //          added into match history
    public double calculateWinRate() {
        int numGames = getNumGames();
        numGamesWon = getNumGamesWon();
        double numG = numGames;
        double numGW = numGamesWon;
        if (numG == 0) {
            return 0;
        } else {
            double wr = (numGW / numG) * 100;
            return wr;
        }
    }

    // REQUIRES: Match history must contain at least one game
    // EFFECTS: Calculates how many times the user has played a certain agent
    public int calculateNumGamesPlayedAgent(AgentType agent) {
        int count = 0;

        for (Game g: games) {
            if (g.getAgent() == agent) {
                count++;
            }
        }
        return count;
    }
}
