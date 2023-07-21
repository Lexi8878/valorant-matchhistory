package persistence;

import model.AgentType;
import model.Game;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkGame(String status, int teamPoints, int enemyPoints, AgentType agent, Game game) {
        assertEquals(status, game.getStatus());
        assertEquals(teamPoints, game.getTeamPoints());
        assertEquals(enemyPoints, game.getEnemyPoints());
        assertEquals(agent, game.getAgent());
    }
}
