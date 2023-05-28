package Car;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public final class DaylightCar extends Car {
    //  Attributes
    private final int nrSeats;
    private Set<Integer> freeSeats = new HashSet<>();

    //  Constructors
    public DaylightCar(String type, int nrSeats) {
        super(type);
        this.nrSeats = nrSeats;

        int nrRows;
        switch (type) {
            case "SecondClass" -> {
                nrRows = nrSeats / 8;
                for (int i = 1; i <= nrRows; i++)
                    for (int j = 1; j <= 8; j++)
                        freeSeats.add(i * 10 + j);
            }
            case "FirstClass" -> {
                nrRows = nrSeats / 6;
                for (int i = 1; i <= nrRows; i++)
                    for (int j = 1; j <= 6; j++)
                        freeSeats.add(i * 10 + j);
            }
            case "Bicycles" -> {
                for (int i = 1; i <= nrSeats; i++)
                    freeSeats.add(i);
            }
        }
    }

    public DaylightCar(Car o) {
        super(o);

        if (o instanceof DaylightCar daylightCar) {
            nrSeats = daylightCar.nrSeats;
            freeSeats = new HashSet<>(daylightCar.freeSeats);
        } else {
            nrSeats = 0;
            freeSeats = null;
        }
    }

    //  Getters & Setters
    public int getNrSeats() {
        return nrSeats;
    }

    //  Methods
    @Override
    public String toString() {
        return super.toString() + ", nrSeats = " + nrSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DaylightCar that = (DaylightCar) o;
        return nrSeats == that.nrSeats && Objects.equals(freeSeats, that.freeSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nrSeats, freeSeats);
    }

    public int nrFreeSeats() {
        return freeSeats.size();
    }

    public int reserveSeat() {
        int seatNumber = -1;
        Iterator<Integer> it = freeSeats.iterator();

        if (it.hasNext()) {
            seatNumber = it.next();
            freeSeats.remove(seatNumber);
        }

        return seatNumber;
    }
}
