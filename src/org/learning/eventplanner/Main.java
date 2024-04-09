package org.learning.eventplanner;

import org.learning.eventplanner.exceptions.TimeTravelException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws TimeTravelException {


//        eventWizard();
//        addConcert();

        int currentEpochDay = (int) LocalDate.now().toEpochDay();
        Random r = new Random();

        EventProgram events = new EventProgram("Event Program");
        for (int i = 0; i < 8; i++) {
            events.addEvent(new Event("event-" + i, LocalDate.ofEpochDay(r.nextInt(366) + currentEpochDay), r.nextInt(50) + 50));
        }
        events.addEvent(new Event("find me!-1", LocalDate.ofEpochDay(20000), 100));
        events.addEvent(new Event("find me!-2", LocalDate.ofEpochDay(20000), 100));

        List<Event> filteredEvents = events.searchEventByDate(LocalDate.ofEpochDay(20000));

        System.out.println(events);
        System.out.println(events.toStringSorted());
        System.out.println("Number of events: " + events.getEventCount());

        events.clearEventProgram();
        System.out.println("Number of events: " + events.getEventCount());

    }

    private static void addConcert() {
        try {
            Concert newConcert = new Concert("test concert", LocalDate.parse("2088-05-14"), 10, LocalTime.parse("13:00"), BigDecimal.valueOf(100));
            System.out.println(newConcert);
        } catch (TimeTravelException ignored) {
        }
    }

    private static void eventWizard() {
        Scanner scan = new Scanner(System.in);
        Event newEvent = null;

        System.out.println("Event Creation Wizard");
        System.out.print("Event name: ");
        String name = scan.nextLine();
        System.out.print("Event date(dd-mm-yyyy): ");
        LocalDate date = LocalDate.parse(scan.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        System.out.print("Event capacity: ");
        int capacity = Integer.parseInt(scan.nextLine());

//        String name = "test event";
//        LocalDate date = LocalDate.parse("14-05-2088", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//        int capacity = 20;

        try {
            newEvent = new Event(name, date, capacity);
            System.out.println(newEvent);
        } catch (Exception ex) {
            System.err.println("Event creation error: " + ex.getMessage());
        }
        boolean menuOpen = true;
        while (menuOpen) {
            System.out.print("Event Menu:\n1 Book Ticket(s)\n2 Cancel Ticket(s)\n0 Exit\n");
            String userInput = scan.nextLine();
            int ticketCount;
            switch (userInput) {
                case "1":
                    System.out.println("How many tickets would you like to book?");
                    ticketCount = Integer.parseInt(scan.nextLine());
                    try {
                        newEvent.book(ticketCount);
                    } catch (Exception ex) {
                        System.out.println("Booking error: " + ex.getMessage());
                    }
                    break;
                case "2":
                    System.out.println("How many tickets do you need to cancel?");
                    ticketCount = Integer.parseInt(scan.nextLine());
                    try {
                        newEvent.cancel(ticketCount);
                    } catch (Exception ex) {
                        System.out.println("Booking error: " + ex.getMessage());
                    }
                    break;
                default:
                    break;
            }
            System.out.println("Tickets Booked: " + newEvent.getBooked() + "\nTickets Remaining: " + newEvent.getAvailable());
            System.out.println("\nContinue?");
            System.out.println("Press any key to continue or 'n' to exit:");
            menuOpen = !scan.nextLine().equals("n");
        }
    }
}
