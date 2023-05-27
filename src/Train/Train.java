package Train;

import java.util.ArrayList;
import java.util.Objects;

import Car.*;

public abstract class Train {
    //  Attributes;
    protected static int nrTrains = 0;
    protected final int TrainID;
    protected final String TrainCode;
    protected int nrCars = 0;
    protected ArrayList<Car> cars = null;

    protected int freeFirstSeats = 0;
    protected int freeSecondSeats = 0;

    protected int Route;

    //  Constructors
    public Train(String TrainCode) {
        TrainID = ++nrTrains;
        this.TrainCode = TrainCode;
    }

    //  Setters & Getters
    public String getTrainCode() {
        return TrainCode;
    }

    public int getFreeFirstSeats() {
        return freeFirstSeats;
    }

    public int getFreeSecondSeats() {
        return freeSecondSeats;
    }

    //  Methods
    @Override
    public String toString() {
        return TrainCode + TrainID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return TrainID == train.TrainID && nrCars == train.nrCars && freeFirstSeats == train.freeFirstSeats && freeSecondSeats == train.freeSecondSeats && Route == train.Route && Objects.equals(TrainCode, train.TrainCode) && Objects.equals(cars, train.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(TrainID, TrainCode, nrCars, cars, freeFirstSeats, freeSecondSeats, Route);
    }

//    public abstract double ticketPrice();

    public abstract void addCar(Car car);

    public void freeSeats() {
        System.out.println("Train: " + this);
        if (freeFirstSeats != 0)
            System.out.println("FirstClass: " + freeFirstSeats + "seats");
        if (freeSecondSeats != 0)
            System.out.println("SecondClass: " + freeSecondSeats + "seats");
    }
}
