package com.yearup.dealership;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    public void saveContract(Contract contract) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("contracts.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String contractInfo;
        contractInfo = String.format("%s|%s|%s|%s|%d|%d\n", contract.getDate(), contract.getCustomerName(), contract.getCustomerEmail(),
                contract.getVehicleSold(), contract.getTotalPrice(), contract.getMonthlyPayment());
        try {
            fileWriter.write(contract.getPersistanceString(contract));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}


