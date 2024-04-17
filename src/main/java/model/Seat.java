package model;

public class Seat
{
    private int row;
    private int no;
    private int price;
    private SeatType seatType;

    public Seat(int row, int no, int price, SeatType seatType)
    {
        this.row = row;
        this.no = no;
        this.price = price;
        this.seatType = seatType;
    }


    public int getRow()
    {
        return row;
    }

    public int getNo()
    {
        return no;
    }

    public int getPrice()
    {
        return price;
    }

    public SeatType getSeatType()
    {
        return seatType;
    }

    @Override
    public String toString()
    {
        return String.format("%-30s", "| " + row + ", " + no + ", " + price + ", " + seatType + " ");
    }
}
