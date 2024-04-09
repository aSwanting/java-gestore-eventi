package org.learning.eventplanner;

import org.learning.eventplanner.comparators.EventComparator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventProgram {
    private final String title;
    private final ArrayList<Event> events;


    public EventProgram(String title) {
        this.title = title;
        this.events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> searchEventByDate(LocalDate date) {
        return events.stream().filter(event -> event.getDate().equals(date)).collect(Collectors.toList());
    }

    public int getEventCount() {
        return events.size();
    }

    public void clearEventProgram() {
        events.clear();
        System.out.println(title + " cleared!");
    }

    public ArrayList<Event> sortedEventList() {
        ArrayList<Event> sortedEvents = new ArrayList<>(events);
        sortedEvents.sort(new EventComparator());
        return sortedEvents;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Event event : events) {
            string.append(event.toString()).append("\n");
        }
        return title + "\n" + string;
    }

    public String toStringSorted() {
        StringBuilder string = new StringBuilder();
        for (Event event : sortedEventList()) {
            string.append(event.toString()).append("\n");
        }
        return title + " (sorted by date)\n" + string;
    }
}
