package model;

import java.util.ArrayList;

public class Customer {

    private final String name;
    private final String phoneNumber;

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
        bookings.add(booking);
    }

    public void removeBooking(Booking booking)
    {
        bookings.remove(booking);
    }

    public ArrayList<Booking> getBookings()
    {
        return new ArrayList<>(bookings);
    }

    @Override
    public String toString()
    {
        return this.name;
    }


    // Getter and setter methods

    public String getName()
    {
        return name;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }





}
