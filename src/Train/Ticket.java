package Train;

import java.util.Objects;

public class Ticket {
    //  Attributes
    private String type;
    private int seatNumber;
    private int carNumber;
    private double price;

    private String start = "Suceava";
    private String finish = "Bucuresti Nord";

    //  Constructors
    Ticket(String type, int seatNumber, int carNumber, double price) {
        this.type = type;
        this.seatNumber = seatNumber;
        this.carNumber = carNumber;
        this.price = price;
    }

    //  Setters & Getters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //  Methods
    @Override
    public String toString() {
        return start + " -> " + finish +
                "\n" + type +
                "\nSeat - " + seatNumber +
                ", Car - " + carNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return seatNumber == ticket.seatNumber && carNumber == ticket.carNumber && Double.compare(ticket.price, price) == 0 && Objects.equals(type, ticket.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, seatNumber, carNumber, price);
    }
}
