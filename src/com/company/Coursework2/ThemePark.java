package com.company.Coursework2;

import org.w3c.dom.ls.LSOutput;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

/******************************************************************************

 File        : ThemePark.java

 Date        : 12/02/2020

 Author      : Abena Serwaa Johene Amo

 Description : Class that stores information about the customers and
 the theme park.

 History     : v 0.01

 Copyright   : (c) Abena Serwaa Johene Amo



 ******************************************************************************/

public class ThemePark {
    //Variables.
    String parkName;
    public String getParkName() {
        return parkName;
    }
    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    ArrayList<Customer> customers = new ArrayList<Customer>();

    ArrayList<Attraction> attractions = new ArrayList<Attraction>();
    //Method to add individual customers to the arraylist
    public ArrayList AddCustomers(String accountNumber, String name,int age, int accountBalance, String personalDiscountType){
        customers.add(new Customer(accountNumber, name, age, accountBalance, personalDiscountType ));
        return customers;
    }
    //Method to add individual ride for all except roller coaster
    public ArrayList AddTransportAttraction(String name, int basePrice, String rideType, int distance ) {
        attractions.add(new TransportAttraction(name, basePrice, rideType, distance));
        return attractions;
    }

    //Method to add individual ride for all except roller coaster
    public ArrayList AddGentleAttraction(String name, int basePrice, String rideType, int capacity ) {
        attractions.add(new GentleAttraction(name, basePrice, rideType, capacity));
        return attractions;
    }
    //Method to add individual ride for all except roller coaster
    public ArrayList AddRollerCoasterRides(String name, int basePrice, String rideType,int minAge, float topSpeed){
        attractions.add(new RollerCoaster(name, basePrice, rideType,minAge,topSpeed));
        return attractions;
    }

    //Default constructor
    ThemePark(){}

    //Methods

    public void getCustomer(String accountNumber) throws CustomerNotFoundException{

    }
    public void removeCustomer(String accountNumber) throws CustomerNotFoundException{}
    public void getRide(String rideName ) throws RideNotFoundException{}
    public void removeRide(String rideName) throws RideNotFoundException{}

    //toString method
    @Override
    public String toString(){
        return parkName + attractions.toString() + customers;
    }


public static void main(String[] args){
        //Testing the AddRides  method

    ThemePark testTransportAttraction =  new ThemePark();
    testTransportAttraction.setParkName("Funtown");

    testTransportAttraction.AddTransportAttraction("Lol", 12, "GEN", 12);

    System.out.println(testTransportAttraction);
    //Adding
}

}

