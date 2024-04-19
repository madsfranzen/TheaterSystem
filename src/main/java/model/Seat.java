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
        String str = String.format("* Row: %-2d | No: %-2d | Price kr. %-3d | Type: %-10s * ", row, number, price, seatType);
        return String.format("%-25s", str);
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
}
