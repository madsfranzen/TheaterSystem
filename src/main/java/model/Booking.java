package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Booking {
    private final LocalDate date;

    // Association Booking -> 1 Customer
    private Customer customer;

    // Composition Booking -> 1 Customer
    private final Show show;

    // Association Booking -> 0..* Seat
    private final ArrayList<Seat> seats;

    public Booking(LocalDate date, Customer customer, Show show) {
        this.date = date;
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>();
    }

    public void addSeat(Seat seat) {
        this.seats.add(seat);
    }

    public ArrayList<Seat> getSeats(){
        return new ArrayList<>(seats);
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public int totalPrice(){
        int totalPrice = 0;

        for (Seat seat : seats){
            totalPrice += seat.getPrice();
        }

        return totalPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public Show getShow() {
        return show;
    }

    public int countOfSeats(){
        return seats.size();
    }
    @Override
    public String toString() {
        return "Booking{" +
                "date=" + date +
                ", customer=" + customer +
                ", show=" + show +
                ", seats=" + seats +
                '}';
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getShowName() {
        return show.getName();
    }
}
