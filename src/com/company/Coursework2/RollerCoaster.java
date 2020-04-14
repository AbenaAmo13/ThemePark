package com.company.Coursework2;

/******************************************************************************

 File        : TransportAttraction.java

 Date        : 12/02/2020

 Author      : Abena Serwaa Johene.

 Description : Sub/Child class of Attraction. This class is to store information and perform methods for
 rollercoasters which includes corkscrew.

 History     : v 0.01

 Copyright   : (c) Abena Serwaa Johene Amo.
 ******************************************************************************/

public class RollerCoaster extends Attraction {
    private String name;

    //Accessor method for name
    public String getName() {
        return super.name;
    }

    //Mutator method to alter the name of an Attraction
    public void setName(String name) {
        this.name = name;
    }

    private int basePrice;

    //Accessor method for base price
    public int getBasePrice() {
        return super.basePrice;
    }

    //Mutator method to alter the base price.
    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    private int minAge;

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    private float topSpeed;

    public double getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(float topSpeed) {
        this.topSpeed = topSpeed;
    }

    private String typeOfAttraction;

    public String getTypeOfAttraction() {
        return super.typeOfAttraction;
    }

    public void setTypeOfAttraction(String typeOfAttraction) {
        this.typeOfAttraction = typeOfAttraction;
    }

    public int getOffPeakPrice() {
        basePrice = getBasePrice();
        return basePrice;
    }

    //toString method
    @Override
    public String toString() {
        String attractionDetails = super.toString();
        return attractionDetails + " " + minAge + " " + topSpeed;
    }

    //RollerCoaster constructor to create rollercoaster objects
    public RollerCoaster(String name, int basePrice, String typeOfAttraction, int minAge, float topSpeed) {
        super(name, basePrice, typeOfAttraction);
        this.minAge = minAge;
        this.topSpeed = topSpeed;
    }

    //Test harness
    public static void main(String[] args) {
        //Test constructor and inheritance.
        RollerCoaster rollercoaster1 = new RollerCoaster("R1", 200, "ROL", 12, 30);
        //Testing access and mutators.
        int originalMinAge = rollercoaster1.getMinAge();
        rollercoaster1.setMinAge(22);
        int testPeakPrice = rollercoaster1.getOffPeakPrice();
        System.out.println(rollercoaster1 + "\n" + "Original Min Age before setting: " + originalMinAge + "\n" + testPeakPrice);

    }


}
