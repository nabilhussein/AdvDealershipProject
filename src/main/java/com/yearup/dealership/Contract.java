package com.yearup.dealership;


import java.time.LocalDate;

public abstract class Contract {

     LocalDate Date;
    String customerName;
    String customerEmail;
    Vehicle vehicleSold;
    double price;
    double monthlyPayment;

    public Contract(LocalDate date, String customerName, String customerEmail, Vehicle vehicleSold) {
        Date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    public Contract() {

    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    abstract String getPersistanceString(Contract contract);

    abstract double getTotalPrice();

    abstract double getMonthlyPayment();

}

