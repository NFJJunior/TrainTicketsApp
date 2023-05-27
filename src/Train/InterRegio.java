package Train;

import Car.*;

import java.util.Objects;

public class InterRegio extends Train {
    //  Attributes
    private boolean hasSleepingCars = false;
    private boolean hasBicycles = false;

    private int free4Beds = 0;
    private int free6Beds = 0;
    private int freeBicycles = 0;

    //  Constructors
    public InterRegio() {
        super("IR");
    }

    //  Setters & Getters
    public boolean getHasSleepingCars() {
        return hasSleepingCars;
    }

    public boolean getHasBicycles() {
        return hasBicycles;
    }

    public int getFree4Beds() {
        return free4Beds;
    }

    public int getFree6Beds() {
        return free6Beds;
    }

    public int getFreeBicycles() {
        return freeBicycles;
    }

    //  Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InterRegio that = (InterRegio) o;
        return hasSleepingCars == that.hasSleepingCars && hasBicycles == that.hasBicycles && free4Beds == that.free4Beds && free6Beds == that.free6Beds && freeBicycles == that.freeBicycles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasSleepingCars, hasBicycles, free4Beds, free6Beds, freeBicycles);
    }

    @Override
    public void addCar(Car car) {
        if (car instanceof DaylightCar daylightCar) {
            cars.add(new DaylightCar(daylightCar));
            switch (daylightCar.getType()) {
                case "FirstClass" -> {
                    freeFirstSeats += daylightCar.nrFreeSeats();
                }
                case "SecondClass" -> {
                    freeSecondSeats += daylightCar.nrFreeSeats();
                }
                case "Bicycles" -> {
                    hasBicycles = true;
                    freeBicycles += daylightCar.nrFreeSeats();
                }
            }
            System.out.println("The car had been added with success!");
        } else if (car instanceof SleepingCar sleepingCar) {
            cars.add(new SleepingCar(sleepingCar));
            hasSleepingCars = true;
            free4Beds += getFree4Beds();
            free6Beds += getFree6Beds();
            System.out.println("The sleeping car had been added with success!");
        } else
            System.out.println("An unexpected error had appeared! Please try again!");
    }

    @Override
    public void freeSeats() {
        super.freeSeats();
        if (freeBicycles != 0)
            System.out.println("Bicycles: " + freeBicycles + "places");
        if (free4Beds != 0)
            System.out.println("4Beds: " + free4Beds + "beds");
        if (free6Beds != 0)
            System.out.println("4Beds: " + free6Beds + "beds");
    }
}
