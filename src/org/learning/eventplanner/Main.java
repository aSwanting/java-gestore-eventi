package org.learning.eventplanner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Event e = null;

//        System.out.println("Event Creation Wizard");
//        System.out.print("Event name: ");
//        String name = scan.nextLine();
//        System.out.print("Event date(dd-mm-yyyy): ");
//        LocalDate date = LocalDate.parse(scan.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//        System.out.print("Event capacity: ");
//        int capacity = Integer.parseInt(scan.nextLine());

        String name = "test event";
        LocalDate date = LocalDate.parse("14-05-2088", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        int capacity = 20;

        try {
            e = new Event(name, date, capacity);
            System.out.println(e);
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
                        e.book(ticketCount);
                    } catch (Exception ex) {
                        System.out.println("Booking error: " + ex.getMessage());
                    }
                    break;
                case "2":
                    System.out.println("How many tickets do you need to cancel?");
                    ticketCount = Integer.parseInt(scan.nextLine());
                    try {
                        e.cancel(ticketCount);
                    } catch (Exception ex) {
                        System.out.println("Booking error: " + ex.getMessage());
                    }
                    break;
                default:
                    break;
            }
            System.out.println("Tickets Booked: " + e.getBooked() + "\nTickets Remaining: " + e.getAvailable());
            System.out.println("\nContinue?");
            System.out.println("Press any key to continue or 'n' to exit:");
            menuOpen = !scan.nextLine().equals("n");
        }
    }
}
