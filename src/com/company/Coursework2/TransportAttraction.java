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
 NOTE THAT YOU REMOVED CONSTRUCTOR TO TEST SOMETHING.

 ******************************************************************************/

public class TransportAttraction extends Attraction {
    private String name;



    //Accessor method for name
    public String getName(){return super.name;};

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
    //To store the type of Attraction in attractions.txt
    private String typeOfAttraction;
    public String getTypeOfAttraction(){
        return super.typeOfAttraction;}
        public void setTypeOfAttraction(String typeOfAttraction){}
    private int distance;
    public int getDistance() {
        return distance;
    }
    public void setDistance(int distance){
        this.distance = distance;
    }
    //Constructor to create TransportAttraction objects.
    public TransportAttraction(String name, int basePrice, String typeOfAttraction, int distance){
        super(name,basePrice,typeOfAttraction);

        this.distance= distance;}
    @Override
    public String toString() {
        String attractionDetails = super.toString();
        return attractionDetails + " " + distance;
    }

    public int getOffPeakPrice() {
       basePrice = getBasePrice();
       basePrice= basePrice/2;
       return basePrice;
    }
    public static void main(String[] args) {
        //Test if constructor would create object.
        TransportAttraction rideyRide = new TransportAttraction("Longride", 100, "TRA", 20) {};
        //Testing accessors.
        String rideName= rideyRide.getName();
        String typeOfAttraction = rideyRide.getTypeOfAttraction();
        //Testing getOffPeakPrice method
        int testOffPeakPrice= rideyRide.getOffPeakPrice();
        System.out.println(typeOfAttraction+"\n"+rideyRide+"\n"+rideName + "\n"+"The off peak price for this ride is: " + " " + testOffPeakPrice);



    }
}
