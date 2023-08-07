package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static model.AgentType.SOVA;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event e;
    private Event e2;
    private Event e3;
    private Game g;
    private Date d;
    private Date d2;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("Added a match to match history.");   // (1)
        d = Calendar.getInstance().getTime();    // (2)
        e2 = new Event("Win rate was calculated: 0%");   // (1)
        d2 = Calendar.getInstance().getTime();   // (2)
        e3 = null;
        g = new Game("win", "13", "0", SOVA);
    }

    @Test
    public void testEvent() {
        assertFalse(e.equals(e2));
        assertTrue(e.equals(e));
        assertFalse(e.equals(e3));
        assertFalse(e.equals(g));
        assertEquals("Added a match to match history.", e.getDescription());
        assertEquals("Win rate was calculated: 0%", e2.getDescription());
        assertEquals(d2, e2.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Added a match to match history.", e.toString());
        assertEquals(d.toString() + "\n" + "Win rate was calculated: 0%", e2.toString());
    }
}
