package model;

import java.util.ArrayList;

public class Customer {
    private String name;
    private String phoneNumber;
    private final ArrayList<Booking> bookings = new ArrayList<>();

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bookings=" + bookings +
                '}';
    }
}
