package controller;

import model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    public static Show createShow(String name, LocalDate startDate, LocalDate endDate)
    {
        Show show = new Show(name, startDate, endDate);
        Storage.storeShow(show);
        return show;
    }

    public static Customer createCustomer(String name, String phoneNumber) {
        Customer customer = new Customer(name, phoneNumber);
        Storage.storeCustomer(customer);
        return customer;
    }

    public static Seat createSeat(int row, int no, int price, SeatType seatType) {
        Seat seat = new Seat(row, no, price, seatType);
        Storage.storeSeat(seat);
        return seat;
    }

    public static ArrayList<Show> getShows() {
        return Storage.getShows();
    }

    public static ArrayList<Customer> getCustomers() {
        return Storage.getCustomer();
    }

    public static ArrayList<Seat> getSeats() {
        return Storage.getSeats();
    }

    /**
     *
     * @param show
     * @param customer
     * @param date
     * @param seats
     * @return booking
     * Note: If a booking can't be done then the method returns null.
     */
    public static Booking createBookingWithSeats(Show show, Customer customer, LocalDate date, ArrayList<Seat> seats){
        Booking booking = null;
        boolean validBooking = true;

        if (show.isShowActiveOnDate(date)){
            for (Seat seat : seats){
                if (!show.isSeatAvailable(seat.getRow(),seat.getNumber(),date)){
                    validBooking = false;
                }
            }
        }

        if (validBooking){
            booking = new Booking(date,customer,show);
            for (Seat seat : seats){
                booking.addSeat(seat);
            }

            show.addBooking(booking);
            customer.addBooking(booking);
        }
        return booking;
    }

}
