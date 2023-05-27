import Car.*;
public class Main {
    public static void main(String[] args) {

        Car c1 = new DaylightCar("Cefaci", 80);
        Car c2 = new DaylightCar(c1);
        Car c3 = new SleepingCar(4);

        System.out.println(c1);
        System.out.println(c2);
    }
}