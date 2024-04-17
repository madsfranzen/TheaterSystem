package model;

import java.awt.print.Book;
import java.util.ArrayList;

public class Customer {

    private String name;
    private String phoneNumber;

    // Association Customer -> 0..* Booking
    private final ArrayList<Booking> bookings; // nullable

    public Customer(String name, String phoneNumber)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.bookings = new ArrayList<>();
    }

    public void addBooking(Booking booking)
    {
        this.bookings.add(booking);
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
