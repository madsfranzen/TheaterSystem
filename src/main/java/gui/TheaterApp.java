package gui;

import controller.Controller;
import javafx.application.Application;
import model.Seat;
import model.SeatType;

import java.time.LocalDate;

public class TheaterApp {

    public static void main(String[] args) {
        initStorage();
        testPrint();
        // Application.launch(TheaterGUI.class);
    }

    public static void initStorage() {

        Controller.addShow("Evita", LocalDate.of(2023, 8, 10), LocalDate.of(2023, 8, 20));
        Controller.addShow("Lykke Per", LocalDate.of(2023, 9, 01), LocalDate.of(2023, 9, 10));
        Controller.addShow("Chess", LocalDate.of(2023, 8, 21), LocalDate.of(2023, 8, 30));
        Controller.addCustomer("Anders Hansen", "28653746");
        Controller.addCustomer("Peter Jensen", "61652242");
        Controller.addCustomer("Niels Madsen", "56872261");

        // Init seats
        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 21; j++) {
                Controller.addSeat(i, j, priceCalculate(i, j), seatTypeCalculate(i, j));
            }
        }
    }

    public static int priceCalculate(int row, int column) {
        int price;
        if (row < 6) {
            if (column > 2 && column < 19) {
                price = 500;
            } else price = 450;
        } else if (row < 11) {
            if (column > 2 && column < 19) {
                price = 450;
            } else price = 400;
        } else price = 400;
        return price;
    }

    public static SeatType seatTypeCalculate(int row, int column) {
        SeatType seatType = SeatType.STANDARD;
        if (row == 10 && (column == 8 || column == 9 || column == 10 || column == 11 || column == 12)) {
            seatType = SeatType.WHEELCHAIR;
        }
        if (row == 11 && (column == 8 || column == 9 || column == 10 || column == 11 || column == 12)) {
            seatType = SeatType.EXTRASPACE;
        }
        return seatType;
    }

    public static void testPrint() {
        System.out.println(Controller.getShows());
        System.out.println(Controller.getCustomers());

        for (int i = 0; i < Controller.getSeats().size(); i++) {
            if (i % 20 == 0) {
                System.out.println("");
            }
            System.out.print(Controller.getSeats().get(i));
        }
    }
}

