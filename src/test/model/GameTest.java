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
    Game g5;
    Game g6;
    Game g7;

    @BeforeEach
    void runBefore() {
        g1 = new Game("win", 13, 0, SOVA);
        g2 = new Game("lose", 1, 13, SAGE);
        g3 = new Game("win", 13, 12, JETT);
        g4 = new Game("lose", 3, 6, BRIMSTONE);
        g5 = new Game("tie", 13, 13, PHOENIX);
        g6 = new Game("lose", 3, 19, PHOENIX);
        g7 = new Game("sdasd", 13, 10, PHOENIX);
    }

    @Test
    void testGame() {
        assertEquals("win", g1.getStatus());
        assertEquals(13, g1.getTeamPoints());
        assertEquals(0, g1.getEnemyPoints());
        assertEquals(SOVA, g1.getAgent());

        assertEquals("lose", g2.getStatus());
        assertEquals(1, g2.getTeamPoints());
        assertEquals(13, g2.getEnemyPoints());
        assertEquals(SAGE, g2.getAgent());

        assertEquals("win", g3.getStatus());
        assertEquals(13,g3.getTeamPoints());
        assertEquals(12, g3.getEnemyPoints());
        assertEquals(JETT, g3.getAgent());
    }

    @Test
    void testWasGameWon() {
        assertTrue(g1.wasGameWon());
        assertFalse(g2.wasGameWon());
        assertTrue(g3.wasGameWon());
    }
}