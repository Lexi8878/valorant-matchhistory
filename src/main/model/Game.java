package model;

// Represents a single game/match a user has played. It has a status (win/lose),
// the amount of team/enemy points at the end of the match, and the name of the character played
public class Game {
    private AgentType agent;
    private String status;
    private int teamPoints;
    private int enemyPoints;

    // REQUIRES: Sum of team and enemy points <= 25 (13 + 12) and the winning team must score 13
    //           status must be either "win" or "lose"
    // EFFECTS: Constructs a game with a win or lose status, the final score of game, and the agent played
    public Game(String status, int teamPoints, int enemyPoints, AgentType agent) {
        this.status = status;
        this.teamPoints = teamPoints;
        this.enemyPoints = enemyPoints;
        this.agent = agent;
    }

    // EFFECTS: Gets the game's status (did user win or lose the game)
    public String getStatus() {
        return status;
    }

    // EFFECTS: Returns true if game was won, returns false otherwise
    public boolean wasGameWon() {
        return status.equals("win");
    }

    // EFFECTS: Gets user's team score at the end of a game
    public int getTeamPoints() {
        return teamPoints;
    }

    // EFFECTS: Gets the enemy team's score at the end of a game
    public int getEnemyPoints() {
        return enemyPoints;
    }

    // EFFECTS: Gets the agent that was played in a game
    public AgentType getAgent() {
        return agent;
    }
}
