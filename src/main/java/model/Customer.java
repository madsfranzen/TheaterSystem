package model;

import java.util.ArrayList;
import java.time.LocalDate;

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

    public ArrayList<Booking> getBookings()
    {
        return new ArrayList<>(this.bookings);
    }

    public void addBooking(Booking booking)
    {
        this.bookings.add(booking);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Seat> getBookedSeatsForShowOnDate(Show show, LocalDate date){
        ArrayList<Seat> seats = new ArrayList<>();

        for (Booking booking : bookings){
            if (booking.getDate().equals(date) && show.equals(booking.getShow())){
                seats.addAll(booking.getSeats());
            }
        }

        return seats;
    }

    @Override
    public String toString()
    {
        return String.format("%s (%s)", name, phoneNumber);
    }
}
