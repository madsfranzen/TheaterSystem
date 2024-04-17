package model;

import java.util.ArrayList;

public class Customer {
    String name;
    String phoneNumber;
    ArrayList<Booking> bookings;

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.bookings = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ",  phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
