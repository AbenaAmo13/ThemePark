import org.w3c.dom.Attr;

/******************************************************************************

 File        : Attraction.java

 Date        : 12/02/2020

 Author      : Abena Serwaa Johene

 Description : Abstract Attraction Class. Base class for the Transportattraction, GentleAttraction and RollercoasterAttraction

 History     : v 0.01

 Copyright   : (c) Abena Serwaa Johene Amo
 ******************************************************************************/

public abstract class Attraction {
    //Declare variables and hafve accessors and mutators.
    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected int basePrice;

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getBasePrice() {
        return basePrice;
    }

    //To store the type of Attraction in attractions.txt
    protected String typeOfAttraction;

    public String getTypeOfAttraction() {
        return typeOfAttraction;
    }

    public void setTypeOfAttraction(String typeOfAttraction) {
    }

    public int getOffPeakPrice() {
        return basePrice;
    }

    public Attraction() {
    }

    public Attraction(String name, int basePrice, String typeOfAttraction) {
        this.name = name;
        this.basePrice = basePrice;
        this.typeOfAttraction = typeOfAttraction;
    }

    @Override
    public String toString() {
        return name + " " + basePrice + " " + typeOfAttraction;
    }
}

