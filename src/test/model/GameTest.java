package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static model.AgentType.*;

class GameTest {

    Game g1;
    Game g2;
    Game g3;
    Game g4;

    @BeforeEach
    void runBefore() {
        g1 = new Game("win", 13, 0, SOVA);
        g2 = new Game("lose", 1, 13, SAGE);
        g3 = new Game("win", 13, 12, JETT);
        g4 = new Game("lose", 3, 6, BRIMSTONE);
    }

    @Test
    void testGame() {
        assertEquals("win", g1.getStatus());
        assertEquals(13, g1.getTeamPoints());
        assertEquals(0, g1.getEnemyPoints());
    }

    @Test
    void testWasGameWon() {
        assertTrue(g1.wasGameWon());
        assertFalse(g2.wasGameWon());
    }
}