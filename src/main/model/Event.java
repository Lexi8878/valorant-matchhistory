package model;

import java.util.Calendar;
import java.util.Date;


// Represents a match history event
// The following code is taken from the Event class in the Alarm System Project:
// https://github.students.cs.ubc.ca/CPSC210/AlarmSystem/blob/main/src/main/ca/ubc/cpsc210/alarm/model/Event.java
public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;

    // EFFECTS: creates an event with the given description and current date/time stamp
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    // EFFECTS: gets the date/time of the event
    public Date getDate() {
        return dateLogged;
    }

    // EFFECTS: gets the description of the event
    public String getDescription() {
        return description;
    }

    // EFFECTS: evaluates if two event objects are equal based off of date logged and description
    @Override
	public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged) && this.description.equals(otherEvent.description));
    }

    // EFFECTS: returns the hashcode of event based on date logged and description
    @Override
	public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    // EFFECTS: returns the date/time and description of the event
    @Override
	public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}
