package org.learning.eventplanner;

import org.learning.eventplanner.exceptions.TimeTravelException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
    private final int totalCapacity;
    private String title;
    private LocalDate date;
    private int booked;

    public Event(String title, LocalDate date, int totalCapacity) throws TimeTravelException {

        this.title = title;
        if (date.isBefore(LocalDate.now())) {
            throw new TimeTravelException("Time Travel has not been invented yet...");
        } else {
            this.date = date;
        }
        this.totalCapacity = totalCapacity;

    }

    // Class Methods
    public void book() throws Exception {
        if (booked == totalCapacity) {
            throw new Exception("Event is at capacity");
        } else if (date.isBefore(LocalDate.now())) {
            throw new TimeTravelException("Cannot book past event");
        } else {
            booked++;
        }
    }

    public void cancel() throws Exception {
        if (booked == 0) {
            throw new Exception("Nothing left to cancel");
        } else if (date.isBefore(LocalDate.now())) {
            throw new TimeTravelException("Cannot cancel past event");
        } else {
            booked--;
        }
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", date=" + date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                ", totalCapacity=" + totalCapacity +
                ", booked=" + booked +
                '}';
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public int getBooked() {
        return booked;
    }


}
