package com.yearup.dealership;

import java.time.LocalDate;

public class SalesContract extends Contract {
    private  final double tax = 0.05;
    private final double recodingFee = 100;
    private double processingFee;
    private boolean finance;
    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        this.setDate(date);
        this.setCustomerName(customerName);
        this.setCustomerEmail(customerEmail);
        this.setVehicle(vehicleSold);

        if (vehicleSold.getPrice() < 10000){
            this.processingFee = 295;
        }else {
            this.processingFee = 495;
        }

    }

    @Override
    public double getTotalPrice() {
        return 0;
    }

    @Override
    public double getMonthlyPayment() {
        return 0;
    }

    @Override
    public String getPersistenceString() {
        return null;
    }
}