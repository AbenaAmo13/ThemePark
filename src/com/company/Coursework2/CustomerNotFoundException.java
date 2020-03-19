package com.company.Coursework2;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(){
        System.out.println("Customer Not Found. Please Check account number and try again.");
    }
}
