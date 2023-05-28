package train;

import car.*;

import java.util.Objects;

public class InterCity extends Train {
    //  Attributes
    private String WIFIPassword;
    private final boolean hasRestaurant;

    //  Constructors
    public InterCity(String WIFIPassword, boolean hasRestaurant) {
        super("IC");
        this.WIFIPassword = WIFIPassword;
        this.hasRestaurant = hasRestaurant;
    }

    //  Getters & Setters;
    public String getWIFIPassword() {
        return WIFIPassword;
    }

    public void setWIFIPassword(String WIFIPassword) {
        this.WIFIPassword = WIFIPassword;
    }

    public boolean getHasRestaurant() {
        return hasRestaurant;
    }

    //  Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InterCity interCity = (InterCity) o;
        return hasRestaurant == interCity.hasRestaurant && Objects.equals(WIFIPassword, interCity.WIFIPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), WIFIPassword, hasRestaurant);
    }

    @Override
    public void addCar(Car car) {
        if (car instanceof DaylightCar daylightCar) {
            if (freeSeats.containsKey(daylightCar.getType())) {
                cars.add(new DaylightCar(daylightCar));
                nrCars++;

                int temp = freeSeats.get(daylightCar.getType());
                temp += daylightCar.nrFreeSeats();
                freeSeats.replace(daylightCar.getType(), temp);

                System.out.println("The car had been added with succes!");
            } else
                System.out.println("An unexpected error had appeared! Please try again!");
        } else
            System.out.println("An unexpected error had appeared! Please try again!");
    }
}
