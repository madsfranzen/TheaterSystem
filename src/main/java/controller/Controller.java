package controller;


import model.Customer;
import model.Seat;
import model.SeatType;
import model.Show;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Controller {
    public static void addShow(String name, LocalDate startdate, LocalDate enddate){
        Show show = new Show(name,startdate,enddate);
        Storage.storeShow(show);
    }
    public static void addCustomer(String name, String phoneNumber){
        Customer customer = new Customer(name,phoneNumber);
        Storage.storeCustomer(customer);
    }
    public static void addSeat(int Row, int number, int price, SeatType seatType){
        Seat seat = new Seat(Row,number,price,seatType);
        Storage.storeSeat(seat);
    }
    public static ArrayList<Show> getShows(){
        return Storage.getShows();
    }
    public static ArrayList<Customer> getCustomer(){
        return Storage.getCustomers();
    }
    public static ArrayList<Seat> getSeats(){
        return Storage.getSeats();
    }
}
