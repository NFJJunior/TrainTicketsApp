package main;

import service.AppService;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        AppService.populate();

        System.out.println("Welcome to the train tickets platform!");

        boolean open = true;
        while (open) {
            System.out.println("\nChoose one command:");
            System.out.println("1. Create an object");
            System.out.println("2. Show an object");
            System.out.println("3. Add car to a train");
            System.out.println("4. Add route to a train");
            System.out.println("5. Verify free seats");
            System.out.println("6. Show details about a train");
            System.out.println("7. Calculate the price of a ticket");
            System.out.println("8. Buy a ticket");
            System.out.println("9. Remove sleeping cars");
            System.out.println("10. Delete an object");
            System.out.println("11. Exit platform");

            int op;
            try {
                op = Integer.parseInt(in.nextLine());
            }
            catch (NumberFormatException err) {
                System.out.println("Error: invalid format");
                continue;
            }

            switch (op) {
                case 1 -> {
                    System.out.println("Choose which object to create:");
                    System.out.println("1. Car");
                    System.out.println("2. Train");
                    System.out.println("3. Route");

                    int op2;
                    try {
                        op2 = Integer.parseInt(in.nextLine());
                    }
                    catch (NumberFormatException err) {
                        System.out.println("Error: invalid format");
                        continue;
                    }

                    switch (op2) {
                        case 1 -> AppService.addCar();
                        case 2 -> AppService.addTrain();
                        case 3 -> AppService.addRoute();
                        default -> System.out.println("Error: invalid number");
                    }
                }
                case 2 -> {
                    System.out.println("Choose which object to show:");
                    System.out.println("1. Car");
                    System.out.println("2. Train");
                    System.out.println("3. Route");

                    int op2;
                    try {
                        op2 = Integer.parseInt(in.nextLine());
                    }
                    catch (NumberFormatException err) {
                        System.out.println("Error: invalid format");
                        continue;
                    }

                    switch (op2) {
                        case 1 -> AppService.showCar();
                        case 2 -> AppService.showTrain();
                        case 3 -> AppService.showRoute();
                        default -> System.out.println("Error: invalid number");
                    }
                }
                case 3 -> AppService.addCarToTrain();
                case 4 -> AppService.addRouteToTrain();
                case 5 -> AppService.freeSeats();
                case 6 -> AppService.showDetails();
                case 7 -> AppService.ticketPrice();
                case 8 -> AppService.buyTicket();
                case 9 -> AppService.removeSleepingCars();
                case 10 -> {
                    System.out.println("Choose which object to remove:");
                    System.out.println("1. Car");
                    System.out.println("2. Train");
                    System.out.println("3. Route");

                    int op2;
                    try {
                        op2 = Integer.parseInt(in.nextLine());
                    }
                    catch (NumberFormatException err) {
                        System.out.println("Error: invalid format");
                        continue;
                    }

                    switch (op2) {
                        case 1 -> AppService.removeCar();
                        case 2 -> AppService.removeTrain();
                        case 3 -> AppService.removeRoute();
                        default -> System.out.println("Error: invalid number");
                    }
                }
                case 11 -> {
                    System.out.println("Have a good day!");
                    open = false;
                }
                default -> System.out.println("Error: invalid number");
            }
        }
    }
}