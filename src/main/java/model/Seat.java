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

    public int getRow()
    {
        return row;
    }

    public int getNumber()
    {
        return number;
    }

    public int getPrice()
    {
        return price;
    }

    @Override
    public String toString() {
       return "Row: "+ row + " No.: " + number + " (DKK " + price + " " + seatType + ")";
    }
}
