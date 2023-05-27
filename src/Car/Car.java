package Car;

import java.util.Objects;

public abstract class Car {
    //  Attributes

    protected static int nrCars = 0;
    protected final int CarID;
    protected final String type;

    //  Constructors
    public Car(String type) {
        CarID = ++nrCars;
        this.type = type;
    }

    public Car(Car o) {
        CarID = o.CarID;
        type = o.type;
    }

    //  Getters & Setters
    public String getType() {
        return type;
    }

    //  Methods
    @Override
    public String toString() {
        return "ID = " + CarID + ", type = " + type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return CarID == car.CarID && Objects.equals(type, car.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CarID, type);
    }
}
