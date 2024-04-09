package org.learning.eventplanner;

import org.learning.eventplanner.exceptions.TimeTravelException;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Concert extends Event {
    private final LocalDateTime dateTime;
    private LocalTime time;
    private BigDecimal price;

    public Concert(String title, LocalDate date, int totalCapacity, LocalTime time, BigDecimal price) throws TimeTravelException {
        super(title, date, totalCapacity);
        this.time = time;
        this.price = price;
        this.dateTime = LocalDateTime.of(getDate(), getTime());
    }

    // Class Methods
    @Override
    public String toString() {
        return getFormattedDateTime() + " - " + getTitle() + " - " + getFormattedPrice();
    }

    // Getters and Setters
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFormattedDateTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("HH:mm, dd/MM/yy"));
    }

    public String getFormattedPrice() {
        return NumberFormat.getCurrencyInstance(new Locale("it", "IT")).format(price);
    }


}
