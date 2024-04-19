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
        if ((bookingDate.isAfter(startDate)) && (bookingDate.isBefore(endDate))) {
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
    public LocalDate getSuccessDate() {
        LocalDate successDate = startDate;
        int countOfSoldSeatsOnDate = 0;
        int countOfSoldSeatsSuccesDate = 0;

        for (LocalDate date = startDate.minusDays(1); date.isBefore(endDate.plusDays(1)); date.plusDays(1)) {
            for (Booking booking : bookings) {
                if (booking.getDate().equals(date)) {
                    countOfSoldSeatsOnDate += booking.countOfSeats();
                }
            }
            if (countOfSoldSeatsOnDate > countOfSoldSeatsSuccesDate) {
                successDate = date;
            }
        }
        return successDate;
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
