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

    static EventProgram events;

    public static void main(String[] args) {

        // New EventProgram
        events = new EventProgram("Event Program");

        // Add an event, then choose to book or cancel tickets (Milestone 1, 2)
        eventWizard();

        // Test the Concert class, an extension of the Event class (Milestone 3)
        testAddConcert();

        // Generate random events and add to events ArrayList for testing (Bonus Milestone 4)
        events = generateRandomEvents(8);
        System.out.println(events);

        // Return a list of events on a specific date
        LocalDate dateToSearch = LocalDate.ofEpochDay(20000);
        List<Event> filteredEvents = events.searchEventByDate(dateToSearch);
        System.out.println("Events on " + dateToSearch.format(DateTimeFormatter.ofPattern("dd-MM-yy")) + ": ");
        System.out.println(filteredEvents + "\n");

        // Return the number of events in the list
        System.out.println("Number of events: " + events.getEventCount() + "\n");

        // Return a String of Events sorted by date
        System.out.println(events.toStringSorted());

        // Clear Event Program
        events.clearEventProgram();
        System.out.println("Number of events: " + events.getEventCount());

    }

    private static EventProgram generateRandomEvents(int eventCount) {
        int currentEpochDay = (int) LocalDate.now().toEpochDay();
        Random r = new Random();
        try {
            for (int i = 0; i < eventCount; i++) {
                events.addEvent(new Event("event-" + i, LocalDate.ofEpochDay(r.nextInt(366) + currentEpochDay), r.nextInt(50) + 50));
            }
            events.addEvent(new Event("find me!-1", LocalDate.ofEpochDay(20000), 100));
            events.addEvent(new Event("find me!-2", LocalDate.ofEpochDay(20000), 100));
        } catch (TimeTravelException ignored) {
        }
        return events;
    }

    private static void testAddConcert() {
        try {
            Concert newConcert = new Concert("test concert", LocalDate.parse("2088-05-14"), 10, LocalTime.parse("13:00"), BigDecimal.valueOf(100));
            System.out.println(newConcert.toDateTimePriceString());
            events.addEvent(newConcert);
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
            events.addEvent(newEvent);
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
                    menuOpen = false;
                    break;
            }
            if (menuOpen) {
                System.out.println("Tickets Booked: " + newEvent.getBooked() + "\nTickets Remaining: " + newEvent.getAvailable());
                System.out.println("\nContinue?");
                System.out.println("Press any key to continue or 'n' to exit:");
                menuOpen = !scan.nextLine().equals("n");
            }
        }
    }
}
