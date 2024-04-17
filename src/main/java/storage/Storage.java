package storage;

import model.Booking;
import model.Customer;
import model.Seat;
import model.Show;

import java.util.ArrayList;

public abstract class Storage {
    private static ArrayList<Seat> seats = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Show> shows = new ArrayList<>();

    public static ArrayList<Seat> getSeats() {
        return seats;
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static ArrayList<Show> getShows() {
        return shows;
    }
    public static void storeShow(Show show){
        shows.add(show);
    }
    public static void storeCustomer(Customer customer){
        customers.add(customer);
    }
    public static void storeSeat(Seat seat){
        seats.add(seat);
    }
}
