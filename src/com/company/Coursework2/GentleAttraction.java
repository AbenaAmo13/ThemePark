package com.company.Coursework2;

/******************************************************************************

 File        : TransportAttraction.java

 Date        : 12/02/2020

 Author      : Abena Serwaa Johene.

 Description : Sub/Child class of Attraction. This class is to store information and perform methods for
 gentle attractions which includes:  Ferris wheels and mini golf.

 History     : v 0.01

 Copyright   : (c) Abena Serwaa Johene Amo.
 NOTE THAT YOU DELETED CONSTRUCTORS TO TRY SOMETHING.

 ******************************************************************************/

public class GentleAttraction extends Attraction {
    String name;
    //Accessor method for name
    public String getName() {
        return super.name;
    }
    //Mutator method to alter the name of an Attraction
    public void setName(String name) {
        this.name = name;
    }
    int basePrice;
    //Accessor method for base price
    public int getBasePrice() {
        return super.basePrice;
    }
    //Mutator method to alter the base price.
    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }
    String typeOfAttraction;
     public String getTypeOfAttraction(){
         return super.typeOfAttraction;
     };
     public void setTypeOfAttraction(String typeOfAttraction){}
    int capacity;
    public int getCapacity(){return capacity;}
    public void setCapacity(int capacity){this.capacity=capacity;}

    @Override
    public String toString() {
        String attractionDetails =super.toString();
        return attractionDetails + " " + capacity;
    }

    public int getOffPeakPrice() {
        basePrice=getBasePrice();
        basePrice=(int) (basePrice-(0.8*basePrice));
        return basePrice;}
        //Constructor to create Gentle Attraction object

    public GentleAttraction(String name, int basePrice, String typeOfAttraction, int capacity){
        super(name, basePrice, typeOfAttraction);
        this.capacity= capacity;
    }
    //Test constructor.
    public static void main(String[] args) {
        //Testing set and get methods with constructors.
        GentleAttraction gentleRide1 = new GentleAttraction("Longhole", 85, "GEN", 3);
        String getAttractionType = gentleRide1.getTypeOfAttraction();
        int testPeakPrice = gentleRide1.getOffPeakPrice();
        System.out.println(gentleRide1+"\n"+getAttractionType + "\n" +"The off peak price is" +" "+ testPeakPrice);

    }
}


