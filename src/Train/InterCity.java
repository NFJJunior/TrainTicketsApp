package Train;

import java.util.Objects;

public class InterCity extends Train {
    //  Attributes
    private String WIFIPassword;
    private boolean hasRestaurant;

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


}
