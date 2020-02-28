package com.company.Coursework2;
/******************************************************************************

 File        : TransportAttraction.java

 Date        : 12/02/2020

 Author      : Abena Serwaa Johene

 Description : Sub/Child class of Attraction. This class is to store information and perform methods
 for transport attractions which include e.g. chair lift, mono  rail)

 History     : v 0.01

 Copyright   : (c) Abena Serwaa Johene Amo

 NB: Do not forget to do test harness.

 ******************************************************************************/

public class TransportAttraction extends Attraction {
    String name;
    //Accessor method for name
    public String getName() {
        return name;
    }
    //Mutator method to alter the name of an Attraction
    public void setName(String name) {
        this.name = name;
    }
    int basePrice;
    //Accessor method for base price
    public int getBasePrice() {
        return basePrice;
    }
    //Mutator method to alter the base price.
    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }
    //Constructor to create transport attraction objects
    public TransportAttraction(String name, int basePrice){
        this.name=name;
        this.basePrice=basePrice;
    }
    int distance;
    @Override
    public String toString() {
        String attractionDetails =super.toString();
        return attractionDetails + " " + distance;
    }


    @Override
    public int getOffPeakPrice() {
        int newBasePrice = basePrice/2;
        return newBasePrice;
    }
}
