package service;

import car.*;
import train.*;

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
        boolean open = true;
        while (open) {
            System.out.println("Choose what type of train do you want to create:");
            System.out.println("1. InterRegio");
            System.out.println("2. InterCity");

            int op;
            try {
                op = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException err) {
                System.out.println("Error: invalid format");
                continue;
            }

            switch (op) {
                case 1 -> {
                    trains.add(new InterRegio());
                    open = false;
                }
                case 2 -> {
                    System.out.println("Choose the WIFI password:");
                    String WIFIPassword = in.nextLine();

                    System.out.println("Choose if the train has a restauran: (yes/no");
                    String response = in.nextLine();

                    switch (response) {
                        case "yes" -> {
                            trains.add(new InterCity(WIFIPassword, true));
                            open = false;
                        }
                        case "no" -> {
                            trains.add(new InterCity(WIFIPassword, false));
                            open = false;
                        }
                        case "default" -> System.out.println("yes or no");
                    }
                }
                default -> System.out.println("Error: invalid number");
            }
        }
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

                route.addRouteEdge(startStation, finishStation, distance, startingTime, finishingTime);
            }

            routes.add(route);

            open = false;
        }
    }

    public static void showCar() {
        if (cars.size() == 0) {
            System.out.println("There are no cars left!");
            return;
        }

        for (int i = 0; i < cars.size(); i++) {
            System.out.print("Car " + i + ": ");
            System.out.println(cars.get(i));
        }
    }

    public static void showTrain() {
        if (trains.size() == 0) {
            System.out.println("There are no trains left!");
            return;
        }

        for (Train train : trains) System.out.println(train);
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

    public static void addCarToTrain() {
        if (trains.size() == 0) {
            System.out.println("There are no trains left!");
            return;
        }

        if (cars.size() == 0) {
            System.out.println("There are no cars left!");
            return;
        }

        System.out.println("Choose an index between 0 and " + (trains.size() - 1) + ": ");

        int index;
        try {
            index = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException err) {
            System.out.println("Error: invalid format");
            return;
        }

        if (index < 0 || index >= trains.size()) {
            System.out.println("Wrong index: ");
            return;
        }

        Train train = trains.get(index);
        if (train instanceof InterRegio) {
            System.out.println("You can choose any type of car!");
            AppService.showCar();

            int index2;
            try {
                index2 = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException err) {
                System.out.println("Error: invalid format");
                return;
            }

            if (index2 < 0 || index2 >= trains.size()) {
                System.out.println("Wrong index: ");
                return;
            }

            train.addCar(cars.get(index2));
        } else {
            System.out.println("You can choose just FirstClass and SecondClass cars!");
            AppService.showCar();

            int index2;
            try {
                index2 = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException err) {
                System.out.println("Error: invalid format");
                return;
            }

            if (index2 < 0 || index2 >= cars.size()) {
                System.out.println("Wrong index: ");
                return;
            }

            train.addCar(cars.get(index2));
        }
    }

    public static void addRouteToTrain() {
        if (trains.size() == 0) {
            System.out.println("There are no trains left!");
            return;
        }

        boolean ok = false;
        for (Train train : trains)
            if (train.getRoute() == null) {
                ok = true;
                break;
            }

        if (!ok) {
            System.out.println("All trains have already a root!");
            return;
        }

        if (routes.size() == 0) {
            System.out.println("There are no routes left!");
            return;
        }

        ok = false;
        for (Route route : routes) {
            if (!route.getHasTrain()) {
                ok = true;
                break;
            }
        }

        if (!ok) {
            System.out.println("All the routes are attached to a train!");
            return;
        }


        for (int i = 0; i < trains.size(); i++) {
            if (trains.get(i).getRoute() == null) {
                System.out.print(i + ": ");
                System.out.println(trains.get(i));
            }
        }
        System.out.println("Choose an index from above: ");

        int index;
        try {
            index = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException err) {
            System.out.println("Error: invalid format");
            return;
        }

        if (index < 0 || index >= trains.size()) {
            System.out.println("Wrong index: ");
            return;
        }

        Train train = trains.get(index);

        for (int i = 0; i < routes.size(); i++) {
            if (! routes.get(i).getHasTrain()) {
                System.out.print(i + ": ");
                System.out.println(routes.get(i));
            }
        }
        System.out.println("Choose an index from above: ");

        int index2;
        try {
            index2 = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException err) {
            System.out.println("Error: invalid format");
            return;
        }

        if (index2 < 0 || index2 >= trains.size()) {
            System.out.println("Wrong index: ");
            return;
        }

        train.addRoute(routes.get(index2));
    }

    public static void freeSeats() {
        if (trains.size() == 0) {
            System.out.println("There are no trains left!");
            return;
        }

        System.out.println("Choose an index between 0 and " + (trains.size() - 1) + ": ");

        int index;
        try {
            index = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException err) {
            System.out.println("Error: invalid format");
            return;
        }

        if (index < 0 || index >= trains.size()) {
            System.out.println("Wrong index: ");
            return;
        }

        trains.get(index).freeSeats();
    }

    public static void showDetails() {
        if (trains.size() == 0) {
            System.out.println("There are no trains left!");
            return;
        }

        System.out.println("Choose an index between 0 and " + (trains.size() - 1) + ": ");

        int index;
        try {
            index = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException err) {
            System.out.println("Error: invalid format");
            return;
        }

        if (index < 0 || index >= trains.size()) {
            System.out.println("Wrong index: ");
            return;
        }

        trains.get(index).showDetails();
    }

    public static void populate() {
        cars.add(new DaylightCar("FirstClass", 60));
        cars.add(new DaylightCar("FirstClass", 75));
        cars.add(new DaylightCar("SecondClass", 80));
        cars.add(new DaylightCar("SecondClass", 120));
        cars.add(new DaylightCar("Bicycles", 50));
        cars.add(new SleepingCar(4));
        cars.add(new SleepingCar(10));

        routes.add(new Route(8, "Suceava", "Bucuresti", LocalTime.parse("05:45", DateTimeFormatter.ofPattern("HH:mm")), LocalTime.parse("11:27", DateTimeFormatter.ofPattern("HH:mm"))));
        routes.get(0).addRouteEdge("Suceava", "Pascani", 61, LocalTime.parse("05:45", DateTimeFormatter.ofPattern("HH:mm")), LocalTime.parse("06:24", DateTimeFormatter.ofPattern("HH:mm")));
        routes.get(0).addRouteEdge("Pascani", "Roman", 40, LocalTime.parse("06:35", DateTimeFormatter.ofPattern("HH:mm")), LocalTime.parse("07:02", DateTimeFormatter.ofPattern("HH:mm")));
        routes.get(0).addRouteEdge("Roman", "Bacau", 44, LocalTime.parse("07:03", DateTimeFormatter.ofPattern("HH:mm")), LocalTime.parse("07:30", DateTimeFormatter.ofPattern("HH:mm")));
        routes.get(0).addRouteEdge("Bacau", "Focsani", 103, LocalTime.parse("07:31", DateTimeFormatter.ofPattern("HH:mm")), LocalTime.parse("08:43", DateTimeFormatter.ofPattern("HH:mm")));
        routes.get(0).addRouteEdge("Focsani", "Buzau", 71, LocalTime.parse("08:44", DateTimeFormatter.ofPattern("HH:mm")), LocalTime.parse("09:44", DateTimeFormatter.ofPattern("HH:mm")));
        routes.get(0).addRouteEdge("Buzau", "Ploiesti", 67, LocalTime.parse("09:45", DateTimeFormatter.ofPattern("HH:mm")), LocalTime.parse("10:48", DateTimeFormatter.ofPattern("HH:mm")));
        routes.get(0).addRouteEdge("Ploiesti", "Bucuresti", 59, LocalTime.parse("10:50", DateTimeFormatter.ofPattern("HH:mm")), LocalTime.parse("11:27", DateTimeFormatter.ofPattern("HH:mm")));

        trains.add(new InterCity("cfr2023", true));
        trains.get(0).addCar(cars.get(0));
        trains.get(0).addCar(cars.get(2));
        trains.get(0).addCar(cars.get(3));
        trains.get(0).addRoute(routes.get(0));

        routes.add(new Route(3, "Campulung Moldovenesc", "Suceava", LocalTime.parse("03:50", DateTimeFormatter.ofPattern("HH:mm")), LocalTime.parse("05:20", DateTimeFormatter.ofPattern("HH:mm"))));
        routes.get(1).addRouteEdge("Campulung Moldovenesc", "Gura Humorului", 32, LocalTime.parse("03:50", DateTimeFormatter.ofPattern("HH:mm")), LocalTime.parse("04:30", DateTimeFormatter.ofPattern("HH:mm")));
        routes.get(1).addRouteEdge("Gura Humorului", "Suceava", 47, LocalTime.parse("04:31", DateTimeFormatter.ofPattern("HH:mm")), LocalTime.parse("05:20", DateTimeFormatter.ofPattern("HH:mm")));
    }

}
