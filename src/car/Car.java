package car;

import java.util.Objects;

public abstract class Car {
    //  Attributes
    protected final String type;

    //  Constructors
    public Car(String type) {
        this.type = type;
    }

    public Car(Car o) {
        type = o.type;
    }

    //  Getters & Setters
    public String getType() {
        return type;
    }

    //  Methods
    @Override
    public String toString() {
        return "type = " + type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(type, car.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
