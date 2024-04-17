package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Booking {
    private final LocalDate date;
    private final Show show;
    private Customer customer;
    private final ArrayList<Seat> seats;

    public Booking(LocalDate date, Show show, Customer customer) {
        this.date = date;
        this.show = show;
        this.customer = customer;
        this.seats = new ArrayList<>();
    }


}
