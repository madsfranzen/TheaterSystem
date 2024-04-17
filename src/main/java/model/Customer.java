package model;

import java.util.ArrayList;

public class Customer {
    private final String name;
    private final String phoneNumber;
    private final ArrayList<Booking> bookings;

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.bookings = new ArrayList<>();
    }
}
