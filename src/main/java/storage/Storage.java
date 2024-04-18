package storage;

import model.Booking;
import model.Customer;
import model.Seat;
import model.Show;

import java.util.ArrayList;

public abstract class Storage {

    public static final ArrayList<Show> shows = new ArrayList<>();
    public static final ArrayList<Customer> customers = new ArrayList<>();
    public static final ArrayList<Seat> seats = new ArrayList<>();
    public static final ArrayList<Booking> bookings = new ArrayList<>();


    public static ArrayList<Show> getShows()
    {
        return new ArrayList<>(shows);
    }

    public static ArrayList<Customer> getCustomer()
    {
        return new ArrayList<>(customers);
    }

    public static ArrayList<Seat> getSeats()
    {
        return new ArrayList<>(seats);
    }
    public static ArrayList<Booking> getBookings(){return new ArrayList<>(bookings);}


    public static void storeShow(Show show)
    {
        shows.add(show);
    }

    public static void storeCustomer(Customer customer)
    {
        customers.add(customer);
    }

    public static void storeSeat(Seat seat)
    {
        seats.add(seat);
    }
    public static void storeBooking(Booking booking){bookings.add(booking);}

    public static void deleteShow(Show show)
    {
        shows.remove(show);
    }

    public static void deleteCustomer(Customer customer)
    {
        customers.remove(customer);
    }

    public static void deleteSeat(Seat seat)
    {
        seats.remove(seat);
    }
    public static void deleteBooking(Booking booking){bookings.remove(booking);}


}
