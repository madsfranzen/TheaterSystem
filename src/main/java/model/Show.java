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
        bookings.add(booking);
    }

    public void removeBooking(Booking booking)
    {
        bookings.remove(booking);
    }

    @Override
    public String toString()
    {
        return this.name;
    }


    // Getter and setter methods

    public String getName()
    {
        return name;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public ArrayList<Booking> getBookings()
    {
        return bookings;
    }

}
