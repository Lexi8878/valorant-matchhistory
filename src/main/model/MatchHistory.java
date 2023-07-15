package model;

import java.util.ArrayList;
import java.util.List;

// Represents a user's match history, that stores all the games a user has played
public class MatchHistory {
    private int numGamesWon;
    private int numGames;
    private double wr;
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

    // EFFECTS: Displays the details of the matches in a match history
    public String getDisplay() {
        String s = null;
        String tp = null;
        String ep = null;
        String history = null;
        for (Game g: games) {
            s = g.getStatus();
            tp = Integer.toString(g.getTeamPoints());
            ep = Integer.toString(g.getEnemyPoints());
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
        for (Game g: games) {
            if (g.wasGameWon()) {
                numGamesWon++;
            }
        }
        return numGamesWon;
    }

    // REQUIRES: Match history must contain at least one game
    // MODIFIES: this
    // EFFECTS: Calculates user's average win rate based off of the games that have been
    //          added into match history
    public double calculateWinRate() {
        numGames = getNumGames();
        numGamesWon = getNumGamesWon();
        double numg = numGames;
        double numgw = numGamesWon;
        wr = (numgw / numg) * 100;
        return wr;
    }
}
