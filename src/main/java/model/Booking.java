package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Booking {
    private final LocalDate localDate;
    private final Show show;
    private final Customer customer;
    private final ArrayList<Seat> seats;

    public Booking(LocalDate localDate, Show show, Customer customer, ArrayList<Seat> seats) {
        this.localDate = localDate;
        this.show = show;
        this.customer = customer;
        this.seats = seats;
    }
}
