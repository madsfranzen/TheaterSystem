package controller;

import model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Controller {

    // Tilføj klassen Controller i pakken controller. Klassen skal indeholde metoder til at oprette
    //objekter af klasserne Forestilling, Kunde og Plads

    public static Show createShow(String name, LocalDate startDate, LocalDate endDate)
    {
        Show show = new Show(name, startDate, endDate);
        Storage.storeShow(show);
        return show;
    }

    public static Customer createCustomer(String name, String phoneNumber)
    {
        Customer customer = new Customer(name, phoneNumber);
        Storage.storeCustomer(customer);
        return customer;
    }

    public static Seat createSeat(int row, int no, int price, SeatType seatType)
    {
        Seat seat = new Seat(row, no, price, seatType);
        Storage.storeSeat(seat);
        return seat;
    }
    public static Booking createBooking(Show show,Customer customer,LocalDate date, ArrayList<Seat> seats){
        Booking booking = new Booking(date,customer,show);
        if(date.plusDays(1).isAfter(show.getStartDate()) && date.minusDays(1).isBefore(show.getEndDate())){
            for (Seat s: seats){
                int row = s.getRow();
                int number = s.getNumber();
                if(!show.isSeatAvaliable(row,number,date)){
                    booking = null;
                }
                else{

                }
            }
        }
        else{
            booking = null;
        }
        if (booking != null){
            Storage.storeBooking(booking);
        }
        return booking;
    }

    public static ArrayList<Show> getShows()
    {
        return Storage.getShows();
    }

    public static ArrayList<Customer> getCustomers()
    {
        return Storage.getCustomer();
    }

    public static ArrayList<Seat> getSeats()
    {
        return Storage.getSeats();
    }



}
