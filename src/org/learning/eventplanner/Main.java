package org.learning.eventplanner;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        try {

            Event e = new Event("test event", LocalDate.of(2024, 8, 23), 400);
            System.out.println(e);

            e.book();
            System.out.println(e);

            e.cancel();
            System.out.println(e);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

    }
}
