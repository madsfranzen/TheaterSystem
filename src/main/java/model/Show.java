package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Show {

    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;

    private final ArrayList<Booking> bookings = new ArrayList<>();

    public Show(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    @Override
    public String toString() {
        return name + " (fra " + startDate + " til " + endDate + ")";
    }

    public boolean isSeatFree(int row, int number, LocalDate date) {
        boolean isFree = true;
        for (Booking booking : bookings) {
            if (booking.getDate().isEqual(date)) {
                for (Seat seat : booking.getSeats()) {
                    if (seat.getRow() == row && seat.getNumber() == number) {
                        isFree = false;
                    }
                }
            }
        }
        return isFree;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getName() {
        return name;
    }
}
