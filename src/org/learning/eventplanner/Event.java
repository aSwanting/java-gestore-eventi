package org.learning.eventplanner;

import org.learning.eventplanner.exceptions.TimeTravelException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
    private final int totalCapacity;
    private String title;
    private LocalDate date;
    private int booked, available;

    public Event(String title, LocalDate date, int totalCapacity) throws TimeTravelException {

        this.title = title;
        if (date.isBefore(LocalDate.now())) {
            throw new TimeTravelException("Time Travel has not been invented yet...");
        } else {
            this.date = date;
        }
        this.totalCapacity = totalCapacity;
        this.available = totalCapacity - booked;

    }

    // Class Methods
    public void book(int ticketCount) throws Exception {

        if (date.isBefore(LocalDate.now())) {
            throw new TimeTravelException("Cannot book past event");

        } else if (available == 0) {
            throw new Exception("Event is fully booked");

        } else if (ticketCount > available) {
            throw new Exception("Unable to book, event only has " + (totalCapacity - booked) + " tickets remaining");

        } else {
            booked += ticketCount;
            available -= ticketCount;
        }

    }

    public void cancel(int ticketCount) throws Exception {

        if (date.isBefore(LocalDate.now())) {
            throw new TimeTravelException("Cannot cancel past event");

        } else if (booked == 0) {
            throw new Exception("There are no tickets to cancel");

        } else if (booked - ticketCount < 0) {
            throw new Exception("Unable to cancel, event only has " + (booked) + " tickets booked");

        } else {
            booked -= ticketCount;
            available += ticketCount;
        }
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " - " + title;
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

    public int getAvailable() {
        return available;
    }
}
