package main;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to the train tickets platform!");

        boolean open = true;
        while (open) {
            System.out.println("\nChoose one command: ");
            System.out.println("1. Create an object");
            System.out.println("2. Show an object");
            System.out.println("3. Exit platform");

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
                    System.out.println("We created an object!");
                }
                case 2 -> {
                    System.out.println("We showed an object!");
                }
                case 3 -> {
                    System.out.println("Have a good day!");
                    open = false;
                }
                default -> {
                    System.out.println("Error: invalid number");
                }
            }
        }
//        Car c1 = new DaylightCar("SecondClass", 80);
//        Car c2 = new DaylightCar("FirstClass", 66);
//        Car c3 = new SleepingCar(4);
//        Car c4 = new DaylightCar("Bicycles", 50);
//
//        Train t1 = new InterRegio();
//        t1.addCar(c1);
//        t1.addCar(c1);
//        t1.addCar(c2);
//        t1.addCar(c3);
//        t1.addCar(c4);
//
//        t1.freeSeats();
//
//        Train t2 = new InterCity("cfr2023", true);
//        t2.addCar(c1);
//        t2.addCar(c2);
//        t2.addCar(c3);
//        t2.addCar(c4);
//
//        t2.freeSeats();
//
//        Ticket t;
//        for (int i = 1; i <= 67; i++) {
//            t = t1.reserveSeat("FirstClass", false);
//            System.out.println(t);
//        }
//
//        t1.freeSeats();
    }
}