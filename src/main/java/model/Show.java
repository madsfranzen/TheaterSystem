package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Show {

    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;

    private final ArrayList<Booking> bookings;

    public Show(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bookings = new ArrayList<>();
    }

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }

    public boolean isSeatAvailable(int row, int no, LocalDate date) {
        boolean isSeatAvailable = true;

        for (int i = 0; i < bookings.size() && isSeatAvailable; i++) {
            Booking booking = bookings.get(i);
            if (booking.getDate().equals(date)) {
                for (Seat seat : booking.getSeats()) {
                    if ((row == seat.getRow()) && (no == seat.getNumber())) {
                        isSeatAvailable = false;
                    }
                }
            }
        }

        return isSeatAvailable;
    }

    public boolean isShowActiveOnDate(LocalDate bookingDate) {
        boolean isShowActiveOnDate = false;
        if ((bookingDate.isAfter(startDate.minusDays(1))) && (bookingDate.isBefore(endDate.plusDays(1)))) {
            isShowActiveOnDate = true;
        }
        return isShowActiveOnDate;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }

    public int getCountOfBookingOnDate(LocalDate date) {
        int countOfBookingOnDate = 0;

        for (Booking booking : bookings) {
            if (booking.getDate().equals(date)) {
                countOfBookingOnDate += booking.countOfSeats();
            }
        }
        return countOfBookingOnDate;
    }

    /**
     * @return date for wiht the higest count of sold seats.
     * Note: If a show hasn't sold any seats then the function will return the starte date for the show.
     */
    public LocalDate getSuccesDate()
    {
        LocalDate succesDate = this.getBookings().get(0).getDate();

        for (Booking booking : this.getBookings())
        {
            if (this.getCountOfBookingOnDate(booking.getDate()) > this.getCountOfBookingOnDate(succesDate))
            {
                succesDate = booking.getDate();
            }
        }
        return succesDate;
    }

    @Override
    public String toString() {
        return String.format("%s ( From: %s To: %s )", name, startDate, endDate);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
