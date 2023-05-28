package train;

import car.*;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;
import java.util.Objects;

public abstract class Train {
    //  Attributes;
    protected static int nrTrains = 0;
    protected final int TrainID;
    protected final String TrainCode;
    protected int nrCars = 0;
    protected ArrayList<Car> cars;
    protected TreeMap<String, Integer> freeSeats;

    protected int Route;

    //  Constructors
    public Train(String TrainCode) {
        TrainID = ++nrTrains;
        this.TrainCode = TrainCode;

        cars = new ArrayList<>();
        freeSeats = new TreeMap<>();

        freeSeats.put("FirstClass", 0);
        freeSeats.put("SecondClass", 0);
    }

    //  Setters & Getters
    public String getTrainCode() {
        return TrainCode;
    }

    public int getFreeSeats(String type) {
        return freeSeats.getOrDefault(type, -1);
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
        return TrainID == train.TrainID && nrCars == train.nrCars && Route == train.Route && Objects.equals(TrainCode, train.TrainCode) && Objects.equals(cars, train.cars) && Objects.equals(freeSeats, train.freeSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(TrainID, TrainCode, nrCars, cars, freeSeats, Route);
    }

    public abstract void addCar(Car car);

    public void freeSeats() {
        System.out.println("Train: " + this);
        for (Map.Entry<String, Integer> item : freeSeats.entrySet()) {
            if (item.getValue() > 0)
                System.out.println(item.getKey() + ": " + item.getValue() + " seats");
        }
    }

    //    public abstract double ticketPrice();

    public Ticket reserveSeat(String type, boolean sleeping) {
        if (getFreeSeats(type) <= 0) {
            System.out.println("There are no more seats for " + type + " on this train!");
            return null;
        }

        if (!sleeping) {
            for (int i = 0; i < nrCars; i++)
                if (cars.get(i) instanceof DaylightCar daylightCar && daylightCar.getType().equals(type) && daylightCar.nrFreeSeats() > 0) {
                    int seatNumber = daylightCar.reserveSeat();
                    freeSeats.replace(type, freeSeats.get(type) - 1);
                    return new Ticket(type, seatNumber, i + 1, 0);
                }
        } else {
            for (int i = 0; i < nrCars; i++)
                if (cars.get(i) instanceof SleepingCar sleepingCar) {
                    if (type.equals("4Beds") && sleepingCar.nrFreeBeds(4) > 0) {
                        int bedNumber = sleepingCar.reserveBed(4);
                        freeSeats.replace("4Beds", freeSeats.get(type) - 1);
                        return new Ticket(type, bedNumber, i + 1, 0);
                    } else if (type.equals("6Beds") && sleepingCar.nrFreeBeds(6) > 0) {
                        int bedNumber = sleepingCar.reserveBed(6);
                        freeSeats.replace("6Beds", freeSeats.get(type) - 1);
                        return new Ticket(type, bedNumber, i + 1, 0);
                    }
                }
        }

        System.out.println("An unexpected error had appeared! Please try again!");

        return null;
    }
}
