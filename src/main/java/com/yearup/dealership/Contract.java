package com.yearup.dealership;


public abstract class Contract {

    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicle;

    public void setDate(String date) {
        this.date = date;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Contract(String date, String customer_name, String customer_email, Vehicle vehicle) {
        this.date = date;
        this.customerName = customer_name;
        this.customerEmail = customer_email;
        this.vehicle = vehicle;
    }

    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();

    public abstract String getPersistenceString();

    public String getDate() {
        return date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}

