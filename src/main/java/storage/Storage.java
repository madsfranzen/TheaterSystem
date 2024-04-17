package storage;

import model.Customer;
import model.Seat;
import model.SeatType;
import model.Show;

import java.util.ArrayList;

public abstract class Storage {
    private static ArrayList<Show> shows = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Seat> seats = new ArrayList<>();

    public static void storeShow(Show show){
        shows.add(show);
    }

    public static void storeCustomer(Customer customer){
        customers.add(customer);
    }

    public static void storeSeat(Seat seat){
        seats.add(seat);
    }

    public static ArrayList<Show> getShows() {
        return shows;
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static ArrayList<Seat> getSeats() {
        return seats;
    }
}


