package train;

import car.*;

import java.util.Objects;

public class InterRegio extends Train {
    //  Attributes
    private boolean hasSleepingCars = false;
    private boolean hasBicycles = false;

    //  Constructors
    public InterRegio() {
        super("IR");
        freeSeats.put("Bicycles", 0);
        freeSeats.put("4Beds", 0);
        freeSeats.put("6Beds", 0);
    }

    //  Setters & Getters
    public boolean getHasSleepingCars() {
        return hasSleepingCars;
    }

    public boolean getHasBicycles() {
        return hasBicycles;
    }

    //  Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InterRegio that = (InterRegio) o;
        return hasSleepingCars == that.hasSleepingCars && hasBicycles == that.hasBicycles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasSleepingCars, hasBicycles);
    }
    @Override
    public void addCar(Car car) {
        if (car instanceof DaylightCar daylightCar) {
            if (freeSeats.containsKey(daylightCar.getType())) {
                cars.add(new DaylightCar(daylightCar));
                nrCars++;

                if (daylightCar.getType().equals("Bicycles"))
                    hasBicycles = true;

                int temp = freeSeats.get(daylightCar.getType());
                temp += daylightCar.nrFreeSeats();
                freeSeats.replace(daylightCar.getType(), temp);

//                System.out.println("The car had been added with succes!");
            } else
                System.out.println("An unexpected error had appeared! Please try again!");
        } else if (car instanceof SleepingCar sleepingCar) {
            cars.add(new SleepingCar(sleepingCar));
            nrCars++;

            hasSleepingCars = true;

            int temp4 = freeSeats.get("4Beds");
            temp4 += sleepingCar.nrFreeBeds(4);
            freeSeats.replace("4Beds", temp4);

            int temp6 = freeSeats.get("6Beds");
            temp6 += sleepingCar.nrFreeBeds(6);
            freeSeats.replace("6Beds", temp6);

//            System.out.println("The sleeping car had been added with success!");
        } else
            System.out.println("An unexpected error had appeared! Please try again!");
    }
}
