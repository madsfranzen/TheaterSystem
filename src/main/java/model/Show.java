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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void addBooking(Booking booking)
    {
        this.bookings.add(booking);
    }


    @Override
    public String toString() {
        return "Show{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
    public boolean isSeatAvaliable(int row, int nr, LocalDate date){
        boolean avaliable = true;
        for (Booking b : bookings){
            if (b.getDate() == date){
                for (Seat s: b.getSeats()){
                    if(s.getNumber() == nr && s.getRow() == row){
                        avaliable = false;
                    }
                }
            }
        }
        return avaliable;
    }
}
