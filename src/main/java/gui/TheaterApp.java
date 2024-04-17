package gui;

import controller.Controller;
import javafx.application.Application;
import model.Customer;
import model.Seat;
import model.SeatType;
import model.Show;

import java.awt.print.Printable;
import java.time.LocalDate;

public class TheaterApp {

    public static void main(String[] args) {

        initStorage();
        testPrint();
        Application.launch(TheaterGUI.class);
    }

    public static void initStorage()
    {

        // Forestillinger:
        // Navn: Evita Spiller fra 2023-08-10 til 2023-08-20
        // Navn: Lykke Per Spiller fra 2023-09-01 til 2023-09-10
        // Navn: Chess Spiller fra 2023-08-21 til 2023-08-30

        Controller.createShow("Evita", LocalDate.of(2023,8,10), LocalDate.of(2023,8,20));
        Controller.createShow("Lykke Per", LocalDate.of(2023,9,1), LocalDate.of(2023,9,10));
        Controller.createShow("Chess", LocalDate.of(2023,8,21), LocalDate.of(2023,8,30));

        // Kunder:
        // Navn: Anders Hansen Mobil: 11223344
        // Navn: Peter Jensen Mobil: 12345678
        // Navn: Niels Madsen Mobil: 12341234

        Controller.createCustomer("Anders Hansen", "11223344");
        Controller.createCustomer("Peter Jensen", "12345678");
        Controller.createCustomer("Niels Madsen", "12341234");


        // Pladser: 15 rækker med 20 pladser i hver række.
        // De gule koster 500 kroner, de grønne koster 450 kroner og de blå pladser koster 400 kroner.

        int rows = 15;
        int seats = 20;

        for (int i = 1; i <= rows; i++)
        {
            for (int j = 1; j <= seats; j++)
            {
                Controller.createSeat(i,j, calculatePrice(i,j), getSeatType(i,j));
            }
        }
    }

    private static int calculatePrice(int row, int seat)
    {
        int price = 0;

        int yellow = 500;
        int green = 450;
        int blue = 400;

        if (row <= 5)
        {
            price = yellow;

            if (seat <= 2 || seat >= 19)
            {
                price = green;
            }
        } else if( row <= 10)
        {
            price = green;

            if (seat <= 2 || seat >= 19)
            {
                price = blue;
            }
        } else
        {
            price = blue;
        }

        return price;
    }

    private static SeatType getSeatType(int row, int seat)
    {
        SeatType seatType = SeatType.STABNDARD;

        if (row == 10 && (seat >= 8 && seat <= 12))
        {
            seatType = SeatType.WHEELCHAIR;
        } else if (row == 11 && (seat >= 8 && seat <= 12))
        {
            seatType = SeatType.EXTRASPACE;
        }

        return seatType;
    }

    private static void testPrint()
    {
        for (Show show : Controller.getShows())
        {
            System.out.println(show);
        }

        for (Customer customer : Controller.getCustomers())
        {
            System.out.println(customer);
        }

        for (Seat seat : Controller.getSeats())
        {
            System.out.print(seat);

            if (seat.getNo() == 20)
            {
                System.out.println();
            }

        }

    }


}
