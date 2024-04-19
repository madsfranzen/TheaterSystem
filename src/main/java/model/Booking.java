package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Booking
{
    private final LocalDate date;

    // Association Booking -> 1 Customer
    private Customer customer;

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

    public LocalDate getDate() {
        return date;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Show getShow() {
        return show;
    }

    public void addSeat(Seat seat)
    {
        this.seats.add(seat);
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
}
