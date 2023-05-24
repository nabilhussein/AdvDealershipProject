package com.yearup.dealership;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private DealershipFileManager fileManager;
    private ContractFileManager contractFileManager;

    private Dealership dealership;


    // Initialize the file managers and dealership
    private void init() {
        fileManager = new DealershipFileManager("inventory.csv");
        contractFileManager = new ContractFileManager("contract.csv");
        dealership = fileManager.getDealership();
    }

    public static Scanner scanner = new Scanner(System.in);

    // Main method to display the user interface
    public void display() {
        init();
        boolean exit = false;
        while (!exit) {
            System.out.println("""
                          Main Menu
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
            // Process user's choice
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
                case 10 -> processCreateContractRequest();
                case 99 -> {
                    exit = true;
                    System.out.println("Exiting Dealership Menu...");
                }

            }

        }

    }

    // Process the request to search vehicles by price range
    public void processGetByPriceRequest() {
        System.out.print("Enter minimum price: ");
        double minPrice = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double maxPrice = scanner.nextDouble();

        ArrayList aList = (ArrayList) dealership.getVehiclesByPrice(minPrice, maxPrice);
        System.out.println("                      Vehicles by Price:                      ");
        displayVehicles(aList);
        System.out.println();

    }

    // Process the request to search vehicles by make and model
    public void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.next();
        System.out.print("Enter model: ");
        String model = scanner.next();

        System.out.println("                      Vehicles by Make and Model:                  ");
        ArrayList aList = (ArrayList) dealership.getVehiclesByMakeModel(make, model);

        displayVehicles(aList);


    }

    // Process the request to search vehicles by year range
    public void processGetByYearRequest() {
        System.out.print("Enter minimum year: ");
        int minYear = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int maxYear = scanner.nextInt();

        System.out.println("                         Vehicles by Year:"              );
        ArrayList aList = (ArrayList) dealership.getVehiclesByYear(minYear, maxYear);

        displayVehicles(aList);


    }

    // Process the request to search vehicles by color
    public void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.next();

        System.out.println("                         Vehicles by Color          ");
        ArrayList aList = (ArrayList) dealership.getVehiclesByColor(color);

        displayVehicles(aList);


    }

    public void processGetByMileageRequest() {
        // Prompt the user to enter the minimum mileage
        System.out.print("Enter minimum mileage: ");
        double minMileage = scanner.nextDouble();
        // Prompt the user to enter the maximum mileage
        System.out.print("Enter maximum mileage: ");
        double maxMileage = scanner.nextDouble();
        // Display the heading for the list of vehicles by mileage
        System.out.println("                              Vehicles by Mileage                   ");
        ArrayList aList = (ArrayList) dealership.getVehiclesByMileage(minMileage, maxMileage);
        // Display the vehicles in the list
        displayVehicles(aList);


    }

    public void processGetByVehicleTypeRequest() {
        // Prompt the user to enter the vehicle type
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.next();

        // Display the heading for the list of vehicles by vehicle type
        System.out.println("                                Vehicles by Vehicle Typ               ");
        ArrayList aList = (ArrayList) dealership.getVehiclesByType(vehicleType);
        // Display the vehicles in the list
        displayVehicles(aList);


    }


    public void processGetAllVehiclesRequest() {
        // Display the heading for the list of all vehicles
        System.out.println("                              All Vehicle                         ");
        ArrayList aList = (ArrayList) dealership.getAllVehicles();
        displayVehicles(aList);


    }


    public void processAddVehicleRequest() {
        // Prompt the user to enter the vehicle details
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
        // Inform the user that the vehicle was added successfully
        System.out.println("Vehicle added successfully.\n");

        // Create a new Vehicle object with the entered details
        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(vehicle);
        fileManager.saveDealership(dealership);
    }

    public void processRemoveVehicleRequest() {
        Vehicle v = null;
        System.out.print("Enter vin of vehicle you would like to remove: ");
        int vin = scanner.nextInt();
        for (Vehicle i : dealership.getAllVehicles()) {
            if (i.getVin() == vin) {
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

    public void processCreateContractRequest() {
        // Prompt the user to enter the VIN of the vehicle
        System.out.print("Enter VIN of the vehicle: ");
        int vin = scanner.nextInt();

        Vehicle vehicle = null;

        // Find the vehicle with the entered VIN in the dealership's inventory
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                vehicle = v;
                break;
            }
        }

        if (vehicle != null) {
            // Prompt the user to select the contract type
            System.out.println("Select the contract type:");
            System.out.println("[1] Sales Contract");
            System.out.println("[2] Lease Contract");
            System.out.print("Enter your choice: ");
            int contractTypeChoice = scanner.nextInt();

            if (contractTypeChoice == 1) {
                // Create a sales contract for the selected vehicle
                createSalesContract(vehicle);
            } else if (contractTypeChoice == 2) {
                // Create a lease contract for the selected vehicle
                createLeaseContract(vehicle);
            } else {
                System.out.println("Invalid contract type choice.");
            }
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    public void createSalesContract(Vehicle vehicle) {
        // Display a message indicating the creation of a sales contract
        System.out.println("Creating Sales Contract...");

        // Get the current date for the contract
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String contractDate = currentDate.format(dateFormatter);

        // Prompt the user to enter customer details
        System.out.print("Enter customer name: ");
        String customerName = scanner.next().trim();
        System.out.print("Enter customer email: ");
        String customerEmail = scanner.next().trim();

        // Calculate sales tax amount as 5% of the vehicle price
        double salesTaxAmount = vehicle.getPrice() * 0.05;

        // Set the recording fee to $100
        double recordingFee = 100.00;

        // Set the processing fee based on the vehicle price
        double processingFee = (vehicle.getPrice() < 10000) ? 295.00 : 495.00;

        // Prompt the user to enter the finance option (yes/no)
        System.out.print("Enter finance option (yes/no): ");
        boolean financeOption = scanner.next().equalsIgnoreCase("yes");

        // Format prices and costs to display only two decimal places
        String formattedSalesTaxAmount = String.format("%.2f", salesTaxAmount);
        String formattedRecordingFee = String.format("%.2f", recordingFee);
        String formattedProcessingFee = String.format("%.2f", processingFee);

        // Create a sales contract object with the entered details
        SalesContract salesContract = new SalesContract(contractDate, customerName, customerEmail, vehicle,
                Double.parseDouble(formattedSalesTaxAmount), Double.parseDouble(formattedRecordingFee),
                Double.parseDouble(formattedProcessingFee), financeOption);

        // Save the sales contract
        contractFileManager.saveContract(salesContract);

        // Remove the vehicle from the dealership's inventory
        dealership.removeVehicle(vehicle);

        // Save the updated dealership information to a file
        fileManager.saveDealership(dealership);

        System.out.println("Sales Contract created and saved.");
    }

    public void createLeaseContract(Vehicle vehicle) {
        // Display a message indicating the creation of a lease contract
        System.out.println("Creating Lease Contract...");

        // Get the current date for the contract
        // Get the current date for the contract
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String contractDate = currentDate.format(dateFormatter);

        // Prompt the user to enter customer details
        System.out.print("Enter customer name: ");
        String customerName = scanner.next().trim();
        System.out.print("Enter customer email: ");
        String customerEmail = scanner.next().trim();

        // Calculate expected ending value as 50% of the vehicle price
        double expectedEndingValue = vehicle.getPrice() * 0.5;

        // Calculate lease fee as 7% of the vehicle price
        double leaseFee = vehicle.getPrice() * 0.07;

        // Format prices and costs to display only two decimal places
        String formattedExpectedEndingValue = String.format("%.2f", expectedEndingValue);
        String formattedLeaseFee = String.format("%.2f", leaseFee);

        // Create a lease contract object with the entered details
        LeaseContract leaseContract = new LeaseContract(contractDate, customerName, customerEmail, vehicle,
                Double.parseDouble(formattedExpectedEndingValue), Double.parseDouble(formattedLeaseFee));

        // Save the lease contract
        contractFileManager.saveContract(leaseContract);

        // Remove the vehicle from the dealership's inventory
        dealership.removeVehicle(vehicle);

        // Save the updated dealership information to a file
        fileManager.saveDealership(dealership);

        System.out.println("Lease Contract created and saved.");
    }
}

