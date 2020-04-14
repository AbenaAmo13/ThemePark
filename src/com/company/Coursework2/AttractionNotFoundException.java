package com.company.Coursework2;

public
class AttractionNotFoundException extends Exception {
    public AttractionNotFoundException() {
        System.out.println("Ride not found. Please check the name of the ride and try again");
    }
}
