package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Customer {

    private String name;
    private String mobile;

    // Association Customer -> 0..* Booking
    private final ArrayList<Booking> bookings; // nullable

    public Customer(String name, String mobile)
    {
        this.name = name;
        this.mobile = mobile;
        this.bookings = new ArrayList<>();
    }

    public void addBooking(Booking booking)
    {
        this.bookings.add(booking);
    }

    public ArrayList<Booking> getBookings()
    {
        return new ArrayList<>(this.bookings);
    }

    /**
    * Return a list of all seats the customer have booked for a show on a given date.
    */
    public ArrayList<Seat> getBookedSeatsForShowOnDate(Show show, LocalDate date)
    {
        ArrayList<Seat> allSeats = new ArrayList<>();

        for (Booking booking : this.getBookings())
        {
            if (booking.getShow().equals(show) && booking.getDate().equals(date))
            {
                allSeats.addAll(booking.getSeats());
            }
        }
        return allSeats;
    }

    @Override
    public String toString()
    {
        return this.name + " (" + mobile + ")";
    }
}
