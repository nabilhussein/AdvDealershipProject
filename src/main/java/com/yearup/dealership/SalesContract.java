package com.yearup.dealership;

import java.time.LocalDate;

public class SalesContract extends Contract {
    private int processingFee;
    private double salesTax;
    public SalesContract(LocalDate date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
    }

    @Override
    String getPersistanceString(Contract contract) {
        StringBuilder sb = new StringBuilder();
        sb.append("SALE|").append(contract.getDate()).append("|")
                .append(contract.getCustomerName()).append("|")
                .append(contract.getCustomerEmail()).append("|")
                .append(contract.getVehicleSold()).append("|")
                .append(contract.getTotalPrice()).append("|")
                .append(contract.getMonthlyPayment()).append("|")
                .append("100|")
                .append(getProcessingFee()).append("|")
                .append(getSalesTax()).append("|")
                .append(getVehicleSold()).append("|")
                .append(getMonthlyPayment()).append("|");
        return sb.toString();
    }


    @Override
    double getTotalPrice() {
        double vehiclePrice = 0;
        double totalPrice = 0;
        vehiclePrice = vehicleSold.getPrice();
        salesTax = vehiclePrice * 0.05; // for getSalesTax method
        // this is the processing fee
        if(vehiclePrice < 10000) {
            totalPrice = vehiclePrice + 295;
            processingFee = 295; // for getProcessingFee method
        } else {
            totalPrice = vehiclePrice + 495;
            processingFee = 495; // for getProcessingFee method
        }
        totalPrice = totalPrice + 100; // recording fee
        totalPrice = totalPrice + (vehiclePrice * 0.05); // sales tax


        return totalPrice;
    }

    int getProcessingFee() {
        return processingFee;
    }
    double getSalesTax() {
        return salesTax;
    }
    @Override
    double getMonthlyPayment() {
        double vehiclePrice = vehicleSold.getPrice();
        double p = vehiclePrice;
        double r;
        double n;
        double t;
        boolean finance;
        double payment = 0;
        if (UserInterface.finance == true) {
            if (vehiclePrice >= 10000) {
                r = 0.0425;
                t = 4;
                n = 12;
            } else {
                r = 0.0525;
                t = 2;
                n = 12;
            }

            double rOverN = r / n;
            double numerator = p * rOverN;
            double onePlusROverN = 1 + rOverN;
            double power = -t * n;
            double denominator = 1 - Math.pow(onePlusROverN, power);
            payment = numerator / denominator;

        } else {
            payment = 0.00;
        }
        return payment;
    }
}