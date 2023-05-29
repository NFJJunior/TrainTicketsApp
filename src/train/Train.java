package train;

import car.*;

import java.time.temporal.ChronoUnit;
import java.util.*;

public abstract class Train {
    //  Attributes;
    protected static int nrTrains = 0;
    protected final int TrainID;
    protected final String TrainCode;
    protected int nrCars = 0;
    protected ArrayList<Car> cars;
    protected TreeMap<String, Integer> freeSeats;

    protected Route route;

    //  Constructors
    public Train(String TrainCode) {
        TrainID = ++nrTrains;
        this.TrainCode = TrainCode;

        cars = new ArrayList<>();
        freeSeats = new TreeMap<>();
        route = null;

        freeSeats.put("FirstClass", 0);
        freeSeats.put("SecondClass", 0);
    }

    //  Setters & Getters
    public int getTrainID() {
        return TrainID;
    }

    public String getTrainCode() {
        return TrainCode;
    }


    public int getFreeSeats(String type) {
        return freeSeats.getOrDefault(type, -1);
    }

    public Route getRoute() {
        return route;
    }

    //  Methods
    @Override
    public String toString() {
        return TrainCode + TrainID +
                "\n" + ((route != null) ? route : "No route");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return TrainID == train.TrainID && nrCars == train.nrCars && Objects.equals(TrainCode, train.TrainCode) && Objects.equals(cars, train.cars) && Objects.equals(freeSeats, train.freeSeats) && Objects.equals(route, train.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(TrainID, TrainCode, nrCars, cars, freeSeats, route);
    }

    public abstract void addCar(Car car);

    public void removeCar(int index) {
        Car car = cars.get(index);
        String type = car.getType();

        if (car instanceof DaylightCar daylightCar) {

            int temp = freeSeats.get(type);
            temp -= daylightCar.nrFreeSeats();
            freeSeats.replace(type, temp);
        } else if (car instanceof SleepingCar sleepingCar) {
            int temp4 = freeSeats.get("4Beds");
            temp4 -= sleepingCar.nrFreeBeds(4);
            freeSeats.replace("4Beds", temp4);

            int temp6 = freeSeats.get("6Beds");
            temp6 -= sleepingCar.nrFreeBeds(6);
            freeSeats.replace("6Beds", temp6);
        }

        cars.remove(index);
        nrCars--;
    }

    public void removeSleepingCars() {
        if (route == null) {
            System.out.println("This train doesn't have a route yet!");
            return;
        }

        if (route.getStartTime().until(route.getFinishTime(), ChronoUnit.MINUTES) > 0) {
            Iterator<Car> itr = cars.iterator();
            while (itr.hasNext()) {
                Car car = itr.next();
                if (car.getType().equals("SleepingCar")) {
                    SleepingCar sleepingCar = (SleepingCar) car;

                    int temp4 = freeSeats.get("4Beds");
                    temp4 -= sleepingCar.nrFreeBeds(4);
                    freeSeats.replace("4Beds", temp4);

                    int temp6 = freeSeats.get("6Beds");
                    temp6 -= sleepingCar.nrFreeBeds(6);
                    freeSeats.replace("6Beds", temp6);

                    itr.remove();
                    nrCars--;
                }
            }
        }
    }

    public void addRoute(Route route) {
        if (route.getHasTrain()) {
            System.out.println("This route is already used by a train!");
            return;
        }

        route.setHasTrain(true);
        this.route = route;
    }

    public void freeSeats() {
        System.out.println("Train: " + this);
        for (Map.Entry<String, Integer> item : freeSeats.entrySet()) {
            if (item.getValue() > 0)
                System.out.println(item.getKey() + ": " + item.getValue() + " seats");
        }
    }

    public double ticketPrice(String type, String startStation, String finishStation) {
        int distance = 0;
        double price = -1;
        boolean ok = false;
        for (int i = 0; i < route.getNrStations() - 1; i++) {
            if (route.getRoute().get(i).start.equals(startStation))
                ok = true;
            if (ok)
                distance += route.getRoute().get(i).distance;
            if (route.getRoute().get(i).finish.equals(finishStation)) {
                ok = false;
                break;
            }
        }

        if (distance == 0 || ok) {
            System.out.println("The stations have been wrongly introduced!");
            return price;
        }

        switch (type) {
            case "FirstClass" -> {
                if (TrainCode.equals("IC"))
                    price = 0.85 * (double) distance;
                else price = 0.75 * (double) distance;
            }
            case "SecondClass" -> {
                if (TrainCode.equals("IC"))
                    price = 0.6 * (double) distance;
                else price = 0.5 * (double) distance;
            }
            case "Bicycles" -> price = 0.1 * (double) distance;
            case "4Beds" -> price = 1 * (double) distance;
            case "6Beds" -> price = 0.9 * (double) distance;
            default -> System.out.println("An unexpected error had appeared! Please try again!");
        }

        return price;
    }

    public Ticket reserveSeat(String type, boolean sleeping, String startStation, String finishStation) {
        double price = ticketPrice(type, startStation, finishStation);

        if (price == -1)
            return null;

        if (getFreeSeats(type) <= 0) {
            System.out.println("There are no more seats for " + type + " on this train!");
            return null;
        }

        if (!sleeping) {
            for (int i = 0; i < nrCars; i++)
                if (cars.get(i) instanceof DaylightCar daylightCar && daylightCar.getType().equals(type) && daylightCar.nrFreeSeats() > 0) {
                    int seatNumber = daylightCar.reserveSeat();
                    freeSeats.replace(type, freeSeats.get(type) - 1);
                    return new Ticket(TrainID, TrainCode, type, seatNumber, i + 1, price, startStation, finishStation);
                }
        } else {
            for (int i = 0; i < nrCars; i++)
                if (cars.get(i) instanceof SleepingCar sleepingCar) {
                    if (type.equals("4Beds") && sleepingCar.nrFreeBeds(4) > 0) {
                        int bedNumber = sleepingCar.reserveBed(4);
                        freeSeats.replace("4Beds", freeSeats.get(type) - 1);
                        return new Ticket(TrainID, TrainCode, type, bedNumber, i + 1, price, startStation, finishStation);
                    } else if (type.equals("6Beds") && sleepingCar.nrFreeBeds(6) > 0) {
                        int bedNumber = sleepingCar.reserveBed(6);
                        freeSeats.replace("6Beds", freeSeats.get(type) - 1);
                        return new Ticket(TrainID, TrainCode, type, bedNumber, i + 1, price, startStation, finishStation);
                    }
                }
        }

        System.out.println("An unexpected error had appeared! Please try again!");

        return null;
    }

    public int totalDistance() {
        return route.totalDistance();
    }

    public void showDetails() {
        route.showDetails();
    }

    public void showStations() {
        route.showStations();
    }
}
