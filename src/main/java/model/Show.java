package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Show {
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final ArrayList<Booking> bookings = new ArrayList<>();

    public Show(String name, LocalDate startDate, LocalDate endDate) {
        this.endDate = endDate;
        this.startDate = startDate;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Show{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
