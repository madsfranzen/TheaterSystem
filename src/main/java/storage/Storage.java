package storage;

import model.*;

import java.util.ArrayList;

public abstract class Storage {
    private final static ArrayList<Customer> customers = new ArrayList<>();
    private final static ArrayList<Seat> seats = new ArrayList<>();
    private final static ArrayList<Show> shows = new ArrayList<>();

    public static void storeCustomer(Customer customer){
        customers.add(customer);
    }

    public static void deleteCustomer(Customer customer){
        customers.remove(customer);
    }

    public static ArrayList<Customer> getCustomers(){
        return new ArrayList<>(customers);
    }

    public static void storeSeat(Seat seat){
        seats.add(seat);
    }

    public static void deleteSeat(Seat seat){
        seats.remove(seat);
    }

    public static ArrayList<Seat> getSeats(){
        return new ArrayList<>(seats);
    }

    public static void storeShow(Show show){
        shows.add(show);
    }

    public static void deleteShow(Show show){
        shows.remove(show);
    }

    public static ArrayList<Show> getShows(){
        return new ArrayList<>(shows);
    }
}
