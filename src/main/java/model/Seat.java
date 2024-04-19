package model;

import java.util.ArrayList;

public class Seat {
    private final int row;
    private final int number;
    private final int price;
    private final SeatType seatType;

    public Seat(int row, int number, int price, SeatType seatType) {
        this.row = row;
        this.number = number;
        this.price = price;
        this.seatType = seatType;
    }

    @Override
    public String toString() {
        return String.format("Row: %3s  Nr: %-3s  %-11s  $%4s", row, number, seatType, price);
    }

    public int getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    public int getPrice() {
        return price;
    }

    public SeatType getSeatType() {
        return seatType;
    }
}
