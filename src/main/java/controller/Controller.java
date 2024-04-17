package controller;

import model.Customer;
import model.Seat;
import model.SeatType;
import model.Show;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Controller {

    public static void addShow(String name, LocalDate startDate, LocalDate endDate) {
        Show show = new Show(name, startDate, endDate);
        Storage.storeShow(show);
    }

    public static void addCustomer(String name, String phoneNumber) {
        Customer customer = new Customer(name, phoneNumber);
        Storage.storeCustomer(customer);
    }

    public static void addSeat(int row, int number, int price, SeatType seatType) {
        Seat seat = new Seat(row, number, price, seatType);
        Storage.storeSeat(seat);
    }

    public static ArrayList<Show> getShows() {
        return Storage.getShows();
    }

    public static ArrayList<Customer> getCustomers() {
        return Storage.getCustomers();
    }

    public static ArrayList<Seat> getSeats() {
        return Storage.getSeats();
    }
}
