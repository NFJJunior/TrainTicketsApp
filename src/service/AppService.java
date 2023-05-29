package service;

import car.*;
import train.*;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class AppService {
    //  The constructor is private because we don't want the class to be initialized;
    private AppService() {
    }

    //  Attributes
    private final static ArrayList<Car> cars = new ArrayList<>();
    private final static ArrayList<Train> trains = new ArrayList<>();
    private final static ArrayList<Route> routes = new ArrayList<>();

    private final static Scanner in = new Scanner(System.in);

    //  Methods
    public static void addCar() {
        boolean open = true;
        while (open) {
            System.out.println("Choose what type of car do you want to create:");
            System.out.println("1. FirstClass");
            System.out.println("2. SecondClass");
            System.out.println("3. Bicycles");
            System.out.println("4. SleepingCar");

            int op;
            try {
                op = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException err) {
                System.out.println("Error: invalid format");
                continue;
            }

            switch (op) {
                case 1 -> {
                    System.out.println("A FirstClassCar can have between 60 and 80 seats. Select a number:");
                    int nrSeats;
                    try {
                        nrSeats = Integer.parseInt(in.nextLine());
                        Car car = new DaylightCar("FirstClass", nrSeats);
                        cars.add(car);
                        open = false;
                    } catch (NumberFormatException err) {
                        System.out.println("Error: invalid format");
                    } catch (IllegalArgumentException err) {
                        System.out.println(err.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("A SecondClassCar can have between 80 and 120 seats. Select a number:");
                    int nrSeats;
                    try {
                        nrSeats = Integer.parseInt(in.nextLine());
                        Car car = new DaylightCar("SecondClass", nrSeats);
                        cars.add(car);
                        open = false;
                    } catch (NumberFormatException err) {
                        System.out.println("Error: invalid format");
                    } catch (IllegalArgumentException err) {
                        System.out.println(err.getMessage());
                    }
                }
                case 3 -> {
                    System.out.println("A BicycleCar can have between 40 and 60 seats. Select a number:");
                    int nrSeats;
                    try {
                        nrSeats = Integer.parseInt(in.nextLine());
                        Car car = new DaylightCar("Bicycles", nrSeats);
                        cars.add(car);
                        open = false;
                    } catch (NumberFormatException err) {
                        System.out.println("Error: invalid format");
                    } catch (IllegalArgumentException err) {
                        System.out.println(err.getMessage());
                    }
                }
                case 4 -> {
                    System.out.println("A SleepingCar has 10 compartments. Select the number of 4Beds compartments (the rest will be 6Beds):");
                    int nr4BedsCompartments;
                    try {
                        nr4BedsCompartments = Integer.parseInt(in.nextLine());
                        Car car = new SleepingCar(nr4BedsCompartments);
                        cars.add(car);
                        open = false;
                    } catch (NumberFormatException err) {
                        System.out.println("Error: invalid format");
                    } catch (IllegalArgumentException err) {
                        System.out.println(err.getMessage());
                    }
                }
                default -> System.out.println("Error: invalid number");
            }
        }
    }

    public static void addTrain() {

    }

    public static void addRoute() {
        boolean open = true;
        while (open) {
            System.out.println("Choose the numbers of stations the train will go through:");

            int nrStation;
            try {
                nrStation = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException err) {
                System.out.println("Error: invalid format");
                continue;
            }

            if (nrStation < 2) {
                System.out.println("The number of stations has to be at least 2!");
                continue;
            }

            String startStation, finishStation, aux;

            System.out.println("Choose the starting station:");
            startStation = in.nextLine();

            System.out.println("Choose the finishing station:");
            finishStation = in.nextLine();

            System.out.println("Choose the starting time(HH:mm):");

            LocalTime startingTime;
            try {
                aux = in.nextLine();
                startingTime = LocalTime.parse(aux, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException err) {
                System.out.println("Error: invalid time format");
                continue;
            }

            System.out.println("Choose the finishing time(HH:mm):");

            LocalTime finishingTime;
            try {
                aux = in.nextLine();
                finishingTime = LocalTime.parse(aux, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException err) {
                System.out.println("Error: invalid time format");
                continue;
            }

            Route route = new Route(nrStation, startStation, finishStation, startingTime, finishingTime);

            for (int i = 0; i < nrStation - 1; i++) {
                System.out.println("RouteEdge: " + (i + 1) + " (out of " + (nrStation - 1) + ")");

                System.out.println("Choose the starting station:");
                startStation = in.nextLine();

                System.out.println("Choose the finishing station:");
                finishStation = in.nextLine();

                System.out.println("Choose the distance between the two stations: ");
                int distance;
                try {
                    distance = Integer.parseInt(in.nextLine());
                } catch (NumberFormatException err) {
                    System.out.println("Error: invalid format");
                    continue;
                }

                if (distance <= 0) {
                    System.out.println("The distance needs to be bigger than 0!");
                    continue;
                }


                System.out.println("Choose the starting time(HH:mm):");
                try {
                    aux = in.nextLine();
                    startingTime = LocalTime.parse(aux, DateTimeFormatter.ofPattern("HH:mm"));
                } catch (DateTimeParseException err) {
                    System.out.println("Error: invalid time format");
                    continue;
                }

                System.out.println("Choose the finishing time(HH:mm):");
                try {
                    aux = in.nextLine();
                    finishingTime = LocalTime.parse(aux, DateTimeFormatter.ofPattern("HH:mm"));
                } catch (DateTimeParseException err) {
                    System.out.println("Error: invalid time format");
                    continue;
                }
            }

            open = false;
        }
    }

    public static void showCar() {
        if (cars.size() == 0) {
            System.out.println("There are no cars left!");
            return;
        }

        for (int i = 0; i < cars.size(); i++) {
            System.out.print("Car " + i + ":");
            System.out.println(cars.get(i));
        }
    }

    public static void showTrain() {
        return;
    }

    public static void showRoute() {
        if (routes.size() == 0) {
            System.out.println("There are no routes left!");
            return;
        }

        for (int i = 0; i < routes.size(); i++) {
            System.out.println("Route " + i + ":");
            System.out.println(routes.get(i));
        }
    }

    public static void populate() {
        return;
    }

}
