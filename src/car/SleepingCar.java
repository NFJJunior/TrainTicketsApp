package car;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class SleepingCar extends Car {
    //  Attributes
    private final int nr4BedsCompartments;
    private final int nr6BedsCompartments;
    private Set<Integer> free4Beds = new HashSet<>();
    private Set<Integer> free6Beds = new HashSet<>();

    //  Constructors
    public SleepingCar(int nr4BedsCompartments) {
        super("SleepingCar");
        this.nr4BedsCompartments = nr4BedsCompartments;
        this.nr6BedsCompartments = 10 - nr4BedsCompartments;

        for (int i = 1; i <= 10; i++)
            if (i <= nr4BedsCompartments)
                for (int j = 1; j <= 4; j++)
                    free4Beds.add(i * 10 + j);
            else
                for (int j = 1; j <= 6; j++)
                    free6Beds.add(i * 10 + j);
    }

    public SleepingCar(Car o) {
        super(o);

        if (o instanceof SleepingCar sleepingCar) {
            nr4BedsCompartments = sleepingCar.nr4BedsCompartments;
            nr6BedsCompartments = sleepingCar.nr6BedsCompartments;
            free4Beds = new HashSet<>(sleepingCar.free4Beds);
            free6Beds = new HashSet<>(sleepingCar.free6Beds);
        } else {
            nr4BedsCompartments = 0;
            nr6BedsCompartments = 0;
            free4Beds = null;
            free6Beds = null;
        }
    }

    //  Getters & Setters
    public int getNr4Beds() {
        return nr4BedsCompartments * 4;
    }

    public int getNr6Beds() {
        return nr6BedsCompartments * 6;
    }

    //  Methods
    @Override
    public String toString() {
        return super.toString() +
                ", 4Beds = " + getNr4Beds() +
                ", 6Beds = " + getNr6Beds();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SleepingCar that = (SleepingCar) o;
        return nr4BedsCompartments == that.nr4BedsCompartments && nr6BedsCompartments == that.nr6BedsCompartments && Objects.equals(free4Beds, that.free4Beds) && Objects.equals(free6Beds, that.free6Beds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nr4BedsCompartments, nr6BedsCompartments, free4Beds, free6Beds);
    }

    public int nrFreeBeds(int bedType) {
        if (bedType == 4)
            return free4Beds.size();
        else if (bedType == 6)
            return free6Beds.size();

        return -1;
    }

    public int reserveBed(int bedType) {
        int bedNumber = -1;
        Iterator<Integer> it;

        if (bedType == 4) {
            it = free4Beds.iterator();
            if (it.hasNext()) {
                bedNumber = it.next();
                free4Beds.remove(bedNumber);
            }

        } else if (bedType == 6) {
            it = free6Beds.iterator();
            if (it.hasNext()) {
                bedNumber = it.next();
                free6Beds.remove(bedNumber);
            }
        }

        return bedNumber;
    }
}
