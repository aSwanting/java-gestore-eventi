package org.learning.eventplanner;

import java.time.LocalDate;

public class Event {
    private final int totalCapacity;
    private String title;
    private LocalDate date;
    private int booked;

    public Event(String title, LocalDate date, int totalCapacity) {
        this.title = title;
        this.date = date;
        this.totalCapacity = totalCapacity;
    }

    // Class Methods
    public void book() {

    }

    public void cancel() {

    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", date=" + date +
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
