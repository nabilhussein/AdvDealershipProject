package com.yearup.dealership;


public class LeaseContract extends Contract {

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);


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

