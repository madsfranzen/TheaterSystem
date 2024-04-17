package controller;

import model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {


    public static Booking createBookingWithSeats(Show show, Customer customer, LocalDate date, ArrayList<Seat> seats)
    {
        Booking booking = null;
        boolean seatsAvailable = true;
        for (Seat seat : seats)
        {
           if (!show.isSeatAvailable(seat.getRow(), seat.getNumber(), date))
           {
               seatsAvailable = false;
           }
        }
        if(seatsAvailable)
        {
            booking = new Booking(date, customer, show);
            show.addBooking(booking);

            for (Seat seat : seats)
            {
                booking.addSeat(seat);
            }
        }
        return booking;
    }

    public static Show createShow(String name, LocalDate startDate, LocalDate endDate)
    {
        Show show = new Show(name, startDate, endDate);
        Storage.storeShow(show);
        return show;
    }

    public static Customer createCustomer(String name, String mobile)
    {
        Customer customer = new Customer(name, mobile);
        Storage.storeCustomer(customer);
        return customer;
    }

    public static Seat createSeat(int row, int no, int price, SeatType seatType)
    {
        Seat seat = new Seat(row, no, price, seatType);
        Storage.storeSeat(seat);
        return seat;
    }

    public static ArrayList<Show> getShows()
    {
        return Storage.getShows();
    }

    public static ArrayList<Customer> getCustomers()
    {
        return Storage.getCustomer();
    }

    public static ArrayList<Seat> getSeats()
    {
        return Storage.getSeats();
    }



}
