package model;

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
        String str = " | " + row + ", " +number + ", " + price + ", " + seatType + " ";
        return String.format("%-25s" , str);
    }
}
