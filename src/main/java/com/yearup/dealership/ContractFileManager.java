package com.yearup.dealership;


import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    private String fileName;

    public ContractFileManager(String fileName) {
        this.fileName = fileName;
    }

    // Method for saving a contract to a file
    public void saveContract(Contract contract) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            // Write the contract's persistence string to the file
            writer.write(contract.getPersistenceString() + "\n");
            System.out.println("Contract saved successfully.");
        } catch (IOException e) {
            // Handle any IOException that occurs while saving the contract
            System.err.println("Error saving contract: " + e.getMessage());
        }
    }
}





