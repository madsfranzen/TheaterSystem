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


    @Override
    public String toString()
    {
        return this.name;
    }

}
