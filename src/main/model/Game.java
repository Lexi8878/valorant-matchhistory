package model;

// Represents a single game/match a user has played. It has a status (win/lose)
// and the amount of team/enemy points at the end of the match
public class Game {

    private String status;
    private int teamPoints;
    private int enemyPoints;

    // REQUIRES: Sum of team and enemy points <= 25 (13 + 12) and the winning team must score 13
    //           status must be either "win" or "lose"
    // EFFECTS: Constructs a game with a win or lose status, and the final score of game
    public Game(String status, int teamPoints, int enemyPoints) {
        if ((teamPoints + enemyPoints <= 25) && (teamPoints == 13 || enemyPoints == 13)) {
            if ((status.equals("win") || status.equals("lose"))) {
                this.status = status;
                this.teamPoints = teamPoints;
                this.enemyPoints = enemyPoints;
            }
        }
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
}
