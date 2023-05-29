package train;

import java.util.Objects;

public class Ticket {
    //  Attributes
    private final int TrainID;

    private final String TrainCode;

    private final String type;
    private final int seatNumber;
    private final int carNumber;
    private final double price;

    private final String startStation;
    private final String finishStation;

    //  Constructors
    Ticket(int TrainID, String TrainCode, String type, int seatNumber, int carNumber, double price, String startStation, String finishStation) {
        this.TrainID = TrainID;
        this.TrainCode = TrainCode;
        this.type = type;
        this.seatNumber = seatNumber;
        this.carNumber = carNumber;
        this.price = price;
        this.startStation = startStation;
        this.finishStation = finishStation;
    }

    //  Setters & Getters

    //  Methods
    @Override
    public String toString() {
        return TrainID + TrainCode +
                "\n" + startStation + "->" + finishStation +
                "\n" + type +
                "\nSeat - " + seatNumber +
                ", Car - " + carNumber +
                "\nPrice: " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return TrainID == ticket.TrainID && seatNumber == ticket.seatNumber && carNumber == ticket.carNumber && Double.compare(ticket.price, price) == 0 && Objects.equals(TrainCode, ticket.TrainCode) && Objects.equals(type, ticket.type) && Objects.equals(startStation, ticket.startStation) && Objects.equals(finishStation, ticket.finishStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(TrainID, TrainCode, type, seatNumber, carNumber, price, startStation, finishStation);
    }
}
