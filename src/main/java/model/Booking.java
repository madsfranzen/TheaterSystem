package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Booking {
    private LocalDate date;
    private final Show show;
    private final Customer customer;
    private final ArrayList<Seat> seats = new ArrayList<>();

    public Booking(LocalDate date, Show show, Customer customer, Seat seat) {
        this.date = date;
        this.show = show;
        this.customer = customer;
        this.seats.add(seat);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "date=" + date +
                ", show=" + show +
                ", customer=" + customer +
                ", seats=" + seats +
                '}';
    }
}
