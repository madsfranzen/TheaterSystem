package model;

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

    @Override
    public String toString()
    {
        return this.name;
    }
}
