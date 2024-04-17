package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Show {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private final ArrayList<Booking> bookings = new ArrayList<>();

    public Show(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    public void removeBooking(Booking booking){
        bookings.remove(booking);
    }

    @Override
    public String toString() {
        return "Show{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", bookings=" + bookings +
                '}';
    }
}
