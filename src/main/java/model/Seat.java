package model;

public class Seat {
    private int row;
    private int no;
    private int price;
    private SeatType seatType;

    public Seat(int row, int no, int price, SeatType seatType) {
        this.row = row;
        this.no = no;
        this.price = price;
        this.seatType = seatType;
    }

    @Override
    public String toString() {
        return String.format("* Row no: %d | Seat no: %d | Price: %d | Seat type: %s *", row, no, price, seatType);
    }
}
