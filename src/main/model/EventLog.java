package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// Represents a log of match history system events.
// The following code is taken from the Event class in the Alarm System Project:
// https://github.students.cs.ubc.ca/CPSC210/AlarmSystem/blob/main/src/main/ca/ubc/cpsc210/alarm/model/EventLog.java
public class EventLog implements Iterable<Event> {
    private static EventLog theLog;
    private Collection<Event> events;

    // EFFECTS: creates an event log with an empty event log list
    private EventLog() {
        events = new ArrayList<Event>();
    }

    // EFFECTS: returns instance of EventLog, if it doesn't already exist, it creates it
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }

    // MODIFIES: this
    // EFFECTS: adds an event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }

    // MODIFIES: this
    // EFFECTS: clears the event log and logs the event
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    // EFFECTS: returns the event iterator
    @Override
	public Iterator<Event> iterator() {
        return events.iterator();
    }
}
