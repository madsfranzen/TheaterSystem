package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Show
{

    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;

    private final ArrayList<Booking> bookings;

    public Show(String name, LocalDate startDate, LocalDate endDate)
    {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bookings = new ArrayList<>();
    }

    public void addBooking(Booking booking)
    {
        this.bookings.add(booking);
    }

    public ArrayList<Booking> getBookings()
    {
        return new ArrayList<>(this.bookings);
    }

    /**
    * Checks if a specific seat is available on a given date.
    */
   public boolean isSeatAvailable(int row, int no, LocalDate date)
   {
       boolean available = true;
        for (Booking booking : bookings)
        {
            if (booking.getDate().equals(date))
            {
                for (Seat seat : booking.getSeats())
                {
                    if (seat.getRow() == row && seat.getNumber() == no)
                    {
                        available = false;
                    }
                }
            }
        }
        return available;
   }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    /**
    * Returns the count of booked seats on a given day
    */
    public int getBookedSeatOnDate(LocalDate date)
    {
        int count = 0;

        for (Booking booking : this.getBookings())
        {
            if (booking.getDate().equals(date))
            {
                count += booking.getSeats().size();
            }
        }
        return count;
    }

    /**
     * Return the date with the most booked seats
     */
    public LocalDate getSuccesDate()
    {
        LocalDate succesDate = this.getBookings().get(0).getDate();

        for (Booking booking : this.getBookings())
        {
            if (this.getBookedSeatOnDate(booking.getDate()) > this.getBookedSeatOnDate(succesDate))
            {
                succesDate = booking.getDate();
            }
        }
        return succesDate;
    }

    @Override
    public String toString() {
        return this.name + " (from " + this.startDate + " to " + this.endDate + ")";
    }
}
