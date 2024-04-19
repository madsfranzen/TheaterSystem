package gui;

import controller.Controller;
import javafx.application.Application;
import model.Seat;
import model.SeatType;

import java.time.LocalDate;

public class TheaterApp {

    public static void main(String[] args) {
        initStorage();
//        testPrint();
        Application.launch(TheaterGUI.class);
    }

    public static void initStorage() {

        Controller.createShow("Evita", LocalDate.of(2023, 8, 10), LocalDate.of(2023, 8, 20));
        Controller.createShow("Lykke Per", LocalDate.of(2023, 9, 01), LocalDate.of(2023, 9, 10));
        Controller.createShow("Chess", LocalDate.of(2023, 8, 21), LocalDate.of(2023, 8, 30));
        Controller.createCustomer("Anders Hansen", "28653746");
        Controller.createCustomer("Peter Jensen", "61652242");
        Controller.createCustomer("Niels Madsen", "56872261");

        // Init seats
        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 21; j++) {
                Controller.createSeat(i, j, priceCalculate(i, j), seatTypeCalculate(i, j));
            }
        }
    }

    /**
     * Return the price for a seat given a row and no
     */
    public static int priceCalculate(int row, int column) {
        int price = 0;
        int yellow = 500;
        int green = 450;
        int blue = 400;
        if (row <= 5) {
            price = yellow;
            if (column <= 2 || column >= 19) {
                price = green;
            }
        } else if (row <= 10) {
            price = green;

            if (column <= 2 || column >= 19) {
                price = blue;
            }
        } else {
            price = blue;
        }
        return price;
    }


    /**
     * Return the seat type given a row and no
     */
    public static SeatType seatTypeCalculate(int row, int column) {
        SeatType seatType = SeatType.STANDARD;

        // Check for wheelchair
        if (row == 10 && (column > 7 && column < 13)) {
            seatType = SeatType.WHEELCHAIR;
        }

        // Check for extra space
        if (row == 11 && (column > 7 && column < 13)) {
            seatType = SeatType.EXTRASPACE;
        }
        return seatType;
    }

    public static void testPrint() {
        System.out.println(Controller.getShows());
        System.out.println(Controller.getCustomers());

        for (int i = 0; i < Controller.getSeats().size(); i++) {
            System.out.print(Controller.getSeats().get(i));
            if (i % 20 == 0) {
                System.out.println("");
            }
        }
    }
}
