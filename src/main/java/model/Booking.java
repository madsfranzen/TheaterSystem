package model;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Booking
{
    private final LocalDate date;

    // Association Booking -> 1 Customer
    private final Customer customer;

    // Composition Booking -> 1 Customer
    private final Show show;

    // Association Booking -> 0..* Seat
    private final ArrayList<Seat> seats;

    public Booking(LocalDate date, Customer customer, Show show)
    {
        this.date = date;
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>();
    }

    public void addSeat(Seat seat)
    {
        seats.add(seat);
    }

    public void removeSeat(Seat seat)
    {
        seats.remove(seat);
    }

    public ArrayList<Seat> getSeats()
    {
        return new ArrayList<>(seats);
    }

    // Getter and setter methods

    public LocalDate getDate()
    {
        return date;
    }

    public Customer getCustomer()
    {
        return customer;
    }

}
