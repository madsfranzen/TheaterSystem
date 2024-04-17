package gui;

import controller.Controller;
import javafx.application.Application;
import model.Customer;
import model.Seat;
import model.SeatType;
import model.Show;
import storage.Storage;

import java.time.LocalDate;

public class TheaterApp {

    public static void main(String[] args) {
        initStorage();

        testPrint();

        //Application.launch(TheaterGUI.class);

    }
    private static void initStorage(){
        Controller.addShow("Evita", LocalDate.of(2023,8,10),LocalDate.of(2023,8,20));
        Controller.addShow("Lykke Per",LocalDate.of(2023,9,1),LocalDate.of(2023,9,10));
        Controller.addShow("Chess",LocalDate.of(2023,8,21),LocalDate.of(2023,8,30));

        Controller.addCustomer("Anders Hansen","11223344");
        Controller.addCustomer("Peter Jensen", "12345678");
        Controller.addCustomer("Niels Madsen","12341234");

        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 21; j++) {
                int price = priceCalc(i,j);
                SeatType seatType = seatTypeCalc(i,j);
                Controller.addSeat(i,j,price,seatType);
            }
        }

    }
    private static int priceCalc(int row, int col){
        int price = 0;
        if (row <6 && col > 2 & col < 19){
            price = 500;
        }
        else if(row < 6 && col < 3 && col > 18){
            price = 450;
        }
        else if (row < 5 && col > 2 && col < 19){
            price = 450;
        }
        else {
            price = 400;
        }
        return price;
    }
    private static SeatType seatTypeCalc(int row, int col){
        SeatType seatType = SeatType.STANDARD;
        if (row == 10 && col > 7 && col < 13){
            seatType =SeatType.WHEELCHAIR;
        }
        else if (row == 11 && col > 7 && col < 13){
            seatType =SeatType.EXTRASPACE;
        }
        return seatType;
    }
    private static void testPrint(){
        for (Show s: Controller.getShows()){
            System.out.println(s.toString());
        }
        System.out.println("");
        for (Customer c: Controller.getCustomer()){
            System.out.println(c.toString());
        }
        System.out.println("");
        for (int i = 0; i < Controller.getSeats().size(); i++) {
            if(i % 20 == 0){
                System.out.println("");
            }
            System.out.print(Controller.getSeats().get(i).toString() +" ");
        }
    }
}
