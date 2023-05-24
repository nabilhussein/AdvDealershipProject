package com.yearup.dealership;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class DealershipFileManager {
    private final String fileName;

    public DealershipFileManager(String fileName) {
        this.fileName = fileName;
    }
    // Method for retrieving the dealership information from a file
    public Dealership getDealership() {
        // Create a new Dealership object with default values
        Dealership dealership = new Dealership("Car dealership", "Hillsida", "347-8374-1234");;

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufReader = new BufferedReader(fileReader)) {

            String input;
            // Read each line from the file
            while ((input = bufReader.readLine()) != null) {
                if (!input.isEmpty()) {
                    // Split the line by "|" delimiter to extract vehicle information
                    String[] parts = input.split("\\|");
                    int vin = Integer.parseInt(parts[0]);
                    int year = Integer.parseInt(parts[1]);
                    String make = parts[2];
                    String model = parts[3];
                    String vehicleType = parts[4];
                    String color = parts[5];
                    int odometer = Integer.parseInt(parts[6]);
                    double price = Double.parseDouble(parts[7]);

                    // Create a new Vehicle object with extracted information
                    Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                    // Add the vehicle to the dealership's inventory
                    dealership.addVehicle(vehicle);
                }
            }
        } catch (IOException e) {
            // Handle any IOException that occurs while reading the inventory file
            System.err.println("Error reading inventory file: ");
        }
        // Return the populated dealership object
            return dealership;
        }


    public void saveDealership(Dealership dealership) {

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (Vehicle v : dealership.getAllVehicles()) {
                String vehicle = String.format("%d|%d|%s|%s|%s|%s|%d|%.2f\n",
                        v.getVin(),
                        v.getYear(),
                        v.getMake(),
                        v.getModel(),
                        v.getVehicleType(),
                        v.getColor(),
                        v.getOdometer(),
                        v.getPrice());
                fileWriter.write(vehicle);
            }
            fileWriter.close();

            System.out.println("Dealership inventory saved to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving dealership inventory");
        }
    }
    }

