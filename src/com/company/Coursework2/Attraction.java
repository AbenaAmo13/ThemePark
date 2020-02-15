package com.company.Coursework2;
/******************************************************************************

 File        : Attraction.java

 Date        : 12/02/2020

 Author      : Abena Serwaa Johene

 Description : Abstract Attraction Class. Base class for the Transportattraction, GentleAttraction and RollercoasterAttraction

 History     : v 0.01

 Copyright   : (c) Abena Serwaa Johene Amo

 ******************************************************************************/

public  abstract class Attraction {
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

    //toString method to Return variables in string format.
    public String toString(){
        return name +" "+ basePrice;
    }



    public abstract int getOffPeakPrice();

}
