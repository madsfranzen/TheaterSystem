package gui;

import controller.Controller;
import model.Customer;
import model.Seat;
import model.SeatType;
import storage.Storage;

import java.time.LocalDate;

public class TheaterApp {

    public static void main(String[] args) {
        initStorage();
        testPrint();
    }
    private static void initStorage(){
        // Create shows
        Controller.createShow("Evita", LocalDate.parse("2023-08-10"), LocalDate.parse("2023-08-20"));
        Controller.createShow("Lykke Per", LocalDate.parse("2023-09-01"), LocalDate.parse("2023-09-10"));
        Controller.createShow("Chess", LocalDate.parse("2023-08-21"), LocalDate.parse("2023-08-30"));

        // Create customers
        Controller.createCustomer("Anders Hansen", "11223344");
        Controller.createCustomer("Peter Hansen", "12345678");
        Controller.createCustomer("Niels Madsen", "12341234");

        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 21 ; j++) {
                Controller.createSeat(i,j,calculatePrice(i,j),getSeatType(i,j));
            }
        }
    }

    private static int calculatePrice(int row, int column){
        int yellowPrice = 500;
        int greenPrice = 450;
        int bluePrice = 400;
        int seatPrice = 0;

        // Check for yellow seats
        if ((row > 0 && row < 6) && (column > 2 && column < 19) ){
            seatPrice = yellowPrice;
        }

        // Check for green seats
        if ((row > 0 && row < 6) && (column > 0 && column < 3) ){
            seatPrice = greenPrice;
        }

        if ((row > 0 && row < 6) && (column > 18 && column < 21) ){
            seatPrice = greenPrice;
        }

        if ((row > 5 && row < 11) && (column > 2 && column < 19) ){
            seatPrice = greenPrice;
        }

        // Check for blue seats
        if ((row > 5 && row < 11) && (column > 0 && column < 3) ){
            seatPrice = bluePrice;
        }

        if ((row > 5 && row < 11) && (column > 18 && column < 21) ){
            seatPrice = bluePrice;
        }

        if ((row > 10 && row < 16) && (column > 0 && column < 21) ){
            seatPrice = bluePrice;
        }

        return seatPrice;
    }

    private static SeatType getSeatType(int row, int column){
        SeatType seatType = SeatType.STANDARD;

        // Check for wheelchair
        if (row == 10 && (column > 7 && column < 13)){
            seatType = SeatType.WHEEALCHAIR;
        }

        // Check for extra space
        if (row == 11 && (column > 7 && column < 13)){
            seatType = SeatType.EXTRASPACE;
        }

        return seatType;
    }

    private static void testPrint(){
        for (Customer customer : Storage.getCustomers()){
            System.out.println(customer.toString());
        }

        int count = 0;
        for (Seat seat : Storage.getSeats()){
            count++;
            System.out.printf("%s ", seat.toString());

            if (count % 20 == 0){
                System.out.println();
            }
        }
    }
}
