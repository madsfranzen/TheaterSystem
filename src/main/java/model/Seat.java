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

    @Override
    public String toString() {
        String str = "Row: "+ row + " No.: " + number + " (DKK " + price + " " + seatType + ")\n";
        return String.format("%-20s", str);
    }
}
