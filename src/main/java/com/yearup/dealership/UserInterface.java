package com.yearup.dealership;


import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private DealershipFileManager fileManager;

    Dealership dealership;

    private void init() {
        fileManager = new DealershipFileManager("inventory.csv");
        dealership = fileManager.getDealership();
    }

    public static Scanner scanner = new Scanner(System.in);

    public void display() {
        init();
        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    *****************************Main Menu********************************
                    [1] - Search By price
                    [2] - Search By Make/Model
                    [3] - Search By Year
                    [4] - Search By Color
                    [5] - Search By Mileage
                    [6] - Search By Type
                    [7] - Show All Vehicle
                    [8] - Add Vehicle
                    [9] - Remove Vehicle
                    [10] - SELL/LEASE A Vehicle
                    [99] - Exit""");
            int choice;
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 10 -> processSellOrLeaseVehicle();
                case 99 -> {
                    exit=true ;
                    System.out.println("Exiting Dealership Menu...");
                }

            }

        }

    }

    private void processSellOrLeaseVehicle() {


    }

    public void processGetByPriceRequest() {
        System.out.print("Enter minimum price: ");
        double minPrice = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double maxPrice = scanner.nextDouble();

        ArrayList aList = (ArrayList) dealership.getVehiclesByPrice(minPrice, maxPrice);
        System.out.println("Vehicles by Price:");
        displayVehicles(aList);
        System.out.println();

    }

    public void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.next();
        System.out.print("Enter model: ");
        String model = scanner.next();

        System.out.println("Vehicles by Make and Model:");
        ArrayList aList = (ArrayList) dealership.getVehiclesByMakeModel(make, model);

        displayVehicles(aList);


    }


    public void processGetByYearRequest() {
        System.out.print("Enter minimum year: ");
        int minYear = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int maxYear = scanner.nextInt();

        System.out.println("Vehicles by Year:");
        ArrayList aList = (ArrayList) dealership.getVehiclesByYear(minYear, maxYear);

        displayVehicles(aList);




    }

    public void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.next();

        System.out.println("Vehicles by Color:");
        ArrayList aList = (ArrayList) dealership.getVehiclesByColor(color);

        displayVehicles(aList);




    }

    public void processGetByMileageRequest() {
        System.out.print("Enter minimum mileage: ");
        double minMileage = scanner.nextDouble();
        System.out.print("Enter maximum mileage: ");
        double maxMileage = scanner.nextDouble();

        System.out.println("Vehicles by Mileage:");
        ArrayList aList = (ArrayList) dealership.getVehiclesByMileage(minMileage, maxMileage);

        displayVehicles(aList);


    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.next();

        System.out.println("Vehicles by Vehicle Type:");
        ArrayList aList = (ArrayList) dealership.getVehiclesByType(vehicleType);

        displayVehicles(aList);


    }


    public void processGetAllVehiclesRequest() {
        System.out.println("***********************All Vehicle******************************");
        ArrayList aList = (ArrayList) dealership.getAllVehicles();
        displayVehicles(aList);


    }


    public void processAddVehicleRequest() {
        System.out.println("Enter the vehicle details:");
        System.out.print("VIN: ");
        int vin = scanner.nextInt();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        System.out.print("Make: ");
        String make = scanner.next();
        System.out.print("Model: ");
        String model = scanner.next();
        System.out.print("Vehicle Type: ");
        String vehicleType = scanner.next();
        System.out.print("Color: ");
        String color = scanner.next();
        System.out.print("Odometer: ");
        int odometer = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.println("Vehicle added successfully.\n");

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(vehicle);
        fileManager.saveDealership(dealership);
    }

    public void processRemoveVehicleRequest() {
        Vehicle v = null;
        System.out.print("Enter vin of vehicle you would like to remove: ");
        int vin = scanner.nextInt();
        for(Vehicle i : dealership.getAllVehicles()){
            if(i.getVin() == vin) {
                System.out.println("Vehicle has been removed");
                v = i;
                break;
            }
        }
        dealership.removeVehicle(v);
        fileManager.saveDealership(dealership);

    }

    private void displayVehicles(ArrayList<Vehicle> inventory) {
        for (Vehicle vehicle : inventory) {
            System.out.printf("%-8d  %-8d  %-15s  %-15s  %-15s  %-10s  %-10d  %.2f%n",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice());
        }
    }
}
