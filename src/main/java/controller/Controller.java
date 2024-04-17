package controller;

import model.*;
import storage.Storage;

import java.time.LocalDate;

public abstract class Controller {
    public static Customer createCustomer(String name, String phoneNumber){
        Customer customer = new Customer(name, phoneNumber);
        Storage.storeCustomer(customer);
        return customer;
    }

    public static Seat createSeat(int row, int no, int price, SeatType seatType){
        Seat seat = new Seat(row, no, price, seatType);
        Storage.storeSeat(seat);
        return seat;
    }

    public static Show createShow(String name, LocalDate startDate, LocalDate endDate){
        Show show = new Show(name, startDate, endDate);
        Storage.storeShow(show);
        return show;
    }
}
