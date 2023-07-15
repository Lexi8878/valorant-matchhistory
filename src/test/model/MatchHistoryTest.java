package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static model.AgentType.*;

public class MatchHistoryTest {
    MatchHistory mh1;
    MatchHistory mh2;
    MatchHistory mh3;
    MatchHistory mh4;
    Game g;
    Game g2;
    Game g3;

    @BeforeEach
    void runBefore() {
        mh1 = new MatchHistory();
        mh2 = new MatchHistory();
        mh3 = new MatchHistory();
        mh4 = new MatchHistory();
        g = new Game("win", 13, 0, SOVA);
        g2 = new Game("lose", 5, 13, SOVA);
        g3 = new Game("win", 13, 12, JETT);
    }

    @Test
    void testMatchHistory() {
        assertEquals(0, mh1.getNumGames());
        assertEquals(0, mh2.getNumGames());
        assertEquals(0, mh3.getNumGames());
    }

    @Test
    void testAddGame() {
        mh1.addGame(g);
        assertEquals(1, mh1.getNumGames());
        assertEquals(1, mh1.getNumGamesWon());

        mh2.addGame(g);
        mh2.addGame(g2);
        assertEquals(2, mh2.getNumGames());
        assertEquals(1, mh2.getNumGamesWon());
        mh2.addGame(g3);
        assertEquals(3, mh2.getNumGames());

        assertEquals(0, mh3.getNumGames());
    }

    @Test
    void testGetDisplay() {
        mh1.addGame(g);
        assertEquals("WIN 13-0", mh1.getDisplay());

        mh2.addGame(g);
        mh2.addGame(g2);
        assertEquals("LOSE 5-13", mh2.getDisplay());
        mh2.addGame(g3);
        assertEquals("WIN 13-12", mh2.getDisplay());
    }

    @Test
    void testGetNumGamesWon() {
        mh1.addGame(g);
        assertEquals(1, mh1.getNumGamesWon());

        mh2.addGame(g);
        mh2.addGame(g2);
        mh2.addGame(g3);
        assertEquals(2, mh2.getNumGamesWon());

        assertEquals(0, mh3.getNumGamesWon());
    }

    @Test
    void testCalculateWinRate() {
        mh1.addGame(g);
        assertEquals(100, mh1.calculateWinRate());

        mh2.addGame(g);
        mh2.addGame(g2);
        assertEquals(50, mh2.calculateWinRate());

        mh3.addGame(g);
        mh3.addGame(g2);
        mh3.addGame(g3);
        assertEquals(66.66666666666666, mh3.calculateWinRate());

        mh4.addGame(g2);
        assertEquals(0, mh4.calculateWinRate());
    }

    @Test
    void testCalculateNumGamesPlayedAgent() {
        mh1.addGame(g);
        assertEquals(1, mh1.calculateNumGamesPlayedAgent(SOVA));
        assertEquals(0, mh1.calculateNumGamesPlayedAgent(PHOENIX));

        mh2.addGame(g);
        mh2.addGame(g2);
        assertEquals(2, mh2.calculateNumGamesPlayedAgent(SOVA));
        mh2.addGame(g3);
        assertEquals(2, mh2.calculateNumGamesPlayedAgent(SOVA));
        assertEquals(0, mh2.calculateNumGamesPlayedAgent(BRIMSTONE));

        assertEquals(0, mh3.calculateNumGamesPlayedAgent(JETT));
    }
}
