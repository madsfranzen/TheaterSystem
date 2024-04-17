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

    @Override
    public String toString() {
        return this.name + " (from " + this.startDate + " to " + this.endDate + ")";
    }
}
