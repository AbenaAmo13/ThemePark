package com.company.Coursework2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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
    private String parkName;

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    private ArrayList<Customer> customers = new ArrayList<Customer>();

    private ArrayList<Attraction> attractions = new ArrayList<Attraction>();

    //Method to add individual customers to the arraylist
    public ArrayList<Customer> AddCustomers(String accountNumber, String name, int age, int accountBalance, String personalDiscountType) {
        customers.add(new Customer(accountNumber, name, age, accountBalance, personalDiscountType));
        return customers;
    }

    //Method to add individual ride for all except roller coaster
    public ArrayList<Attraction> AddTransportAttraction(String name, int basePrice, String rideType, int distance) {
        attractions.add(new TransportAttraction(name, basePrice, rideType, distance));
        return attractions;
    }

    //Method to add individual ride for all except roller coaster
    public ArrayList<Attraction> AddGentleAttraction(String name, int basePrice, String rideType, int capacity) {
        attractions.add(new GentleAttraction(name, basePrice, rideType, capacity));
        return attractions;
    }

    //Method to add individual ride for all except roller coaster
    public ArrayList<Attraction> AddRollerCoasterRides(String name, int basePrice, String rideType, int minAge, float topSpeed) {
        attractions.add(new RollerCoaster(name, basePrice, rideType, minAge, topSpeed));
        return attractions;
    }

    //Methods to get Customer

    public Customer getCustomer(String accountNumber) {
        Customer finalCustomer = null;
        try { //Loop through customer arrayList

            for (Customer customer : customers) {
                //Check if account Number is equal to the account number you are looking for.
                if (customer.getAccountNumber().equals(accountNumber)) {
                    //If it is place that customer in the finalCustomer variable.
                    finalCustomer = customer;
                }
            }

            if (finalCustomer == null) {
                //If customer isn't found then throw the exception.
                throw new CustomerNotFoundException();
            }
            System.out.println("The customer: " + finalCustomer);
        } catch (Exception e) {
        }
        //Return the found customer.
        return finalCustomer;
    }

    //Method to remove customer.
    public Customer removeCustomer(String accountNumber) {

        Customer finalCustomer = null;
        try {
            //Loop through customer arrayList
            for (Customer customer : customers) {
                //Check if account Number is equal to the account number you are looking for.
                if (customer.getAccountNumber().equals(accountNumber)) {
                    //Find customer you want to remove.
                    finalCustomer = customer;
                }
            }
            if (finalCustomer == null) {
                //If customer isn't found then throw exception.
                throw new CustomerNotFoundException();
            } else {
                //Once customer is found then remove customer.
                customers.remove(finalCustomer);
                finalCustomer = null;
            }
        } catch (Exception e) {
        }

        return finalCustomer;
    }

    //Method to get Attraction
    public Attraction getAttraction(String rideName) {
        Attraction foundAttraction = null;
        try {// Method to get Attraction
            for (Attraction attraction : attractions) {
                //When name of attraction in the arraylist attraction matches ridename
                if (attraction.getName().contentEquals(rideName)) {
                    //Place it in the attraction object called foundAttraction
                    foundAttraction = attraction;

                }

            }
            if (foundAttraction == null) {
                //When attraction isn't found then throw AttractionNotFound Exception.
                throw new AttractionNotFound();

            }
            System.out.println("The ride: " + foundAttraction);

        } catch (Exception e) {
        }

        return foundAttraction;
    }

    //Method to remove Attraction
    public Attraction removeAttraction(String rideName) {
        Attraction foundRide = null;
        try {
            //Loop through attractions arraylist.
            for (Attraction attraction : attractions) {
                //If the name of the attraction in the attraction arraylist is the same as the rideName
                if (attraction.getName().equals(rideName)) {
                    //Place that attraction object in foundRide
                    foundRide = attraction;
                }
            }
            if (foundRide == null) {
                //if attraction isn't found then throw attraction not found exception.
                throw new AttractionNotFound();
            } else {
                //Once attraction is found then delete the attraction.
                attractions.remove(foundRide);
                foundRide = null;
            }
        } catch (Exception e) {

        }

        return foundRide;
    }


    public int calculateTotalTransportDistance() {
        int totalDistance = 0;
        int transportDistance = 0;
        //Get all rides in attraction whose RideType is "TRA"
        for (Attraction transportAttraction : attractions) {
            if (transportAttraction.getTypeOfAttraction().equals("TRA")) {
                //Get distance of the transport Attraction
                // Cast the attraction to a TransportAttraction object.

                //Add transportDistance to totalDistance
                totalDistance = ((TransportAttraction) transportAttraction).getDistance() + totalDistance;
            }
        }

        System.out.println("The total distance is" + " " + totalDistance);

        return totalDistance;
    }

    ;

    public double calculateAverageGentleCapacity() {
        double totalAmountGentleCapacity = 0;
        double numberOfGentleAttraction = 0;
        double averageGentleCapacity = 0;
        for (Attraction gentleAttraction : attractions) {
            if (gentleAttraction.getTypeOfAttraction().equals("GEN")) {
                gentleAttraction = (GentleAttraction) gentleAttraction;
                //Count number of Gentle attraction rides.
                numberOfGentleAttraction = numberOfGentleAttraction + 1;

                totalAmountGentleCapacity = totalAmountGentleCapacity + ((GentleAttraction) gentleAttraction).getCapacity();
            }
        }
        averageGentleCapacity = totalAmountGentleCapacity / numberOfGentleAttraction;
        System.out.println("The average Gentle Capacity is: " + " " + averageGentleCapacity);
        return averageGentleCapacity;

    }

    public double calculateMedianCoasterSpeed() {
        //Get an array of all the Top Speeds
        double medianCoasterSpeed = 0;
        int numberOfElements = 0;
        double evenElement1 = 0;
        double evenElement2 = 0;
        ArrayList<Double> TopSpeed = new ArrayList<Double>();
        System.out.println(attractions);
        for (Attraction rollerCoaster : attractions) {

            if (rollerCoaster.getTypeOfAttraction().equals("ROL")) {
                rollerCoaster = (RollerCoaster) rollerCoaster;
                TopSpeed.add(((RollerCoaster) rollerCoaster).getTopSpeed());
                numberOfElements = numberOfElements + 1;
            }
        }
        //Sort TopSpeed Array
        Collections.sort(TopSpeed);
        if (numberOfElements % 2 == 0) {
            evenElement1 = (double) TopSpeed.get(numberOfElements / 2);
            evenElement2 = (double) TopSpeed.get((numberOfElements / 2) + 1);
            medianCoasterSpeed = (evenElement1 + evenElement2) / 2;
        } else {
            medianCoasterSpeed = (double) TopSpeed.get(numberOfElements / 2);
        }
        //Check the number of elements.

        System.out.println("The median coaster speed is: " + medianCoasterSpeed + "mph");

        return medianCoasterSpeed;


    }

    //toString method
    @Override
    public String toString() {
        return parkName + attractions.toString() + customers;
    }


    public static void main(String[] args) throws IOException {

        //Testing getCustomer.
        ThemePark testThemePark = new ThemePark();
        testThemePark = Simulation.createThemePark();
        System.out.println(testThemePark);
        //Testing Total Trasnport Distance.
        testThemePark.calculateTotalTransportDistance();
        //Testing calculate Average Gentle Capacity.
        testThemePark.calculateAverageGentleCapacity();
        //Testing get Customer.

        testThemePark.getCustomer("576012");
        testThemePark.getCustomer("324287");
        testThemePark.getCustomer("196225");
        testThemePark.getCustomer("585526");
        //Testing get Ride method.
        testThemePark.getAttraction("Haunted House");
        testThemePark.getAttraction("Giga Coaster");
        testThemePark.getAttraction("The Corkscrew");
        testThemePark.getAttraction("Tower of Midnight");
        //Testing Calculating median coaster speed.
        testThemePark.calculateMedianCoasterSpeed();

        //Testing AttractionNot Found Exception
        testThemePark.getAttraction("Joki");

        ThemePark testThemePark2 = new ThemePark();

        //Testing setParkName Method.
        testThemePark2.setParkName("TestingMethodsThemePark");

        //Testing the AddRides  method
        testThemePark2.AddGentleAttraction("Lol", 12, "GEN", 12);
        testThemePark2.AddTransportAttraction("Bouncy", 50, "TRA", 12);
        //Testing calculating Total Transport Distance
        testThemePark2.calculateTotalTransportDistance();
        System.out.println(testThemePark2);

        //Testing removeRide
        testThemePark2.removeAttraction("Lol");
        System.out.println("After applying the remove ride method, the themePark object now contains:" + "\n" + testThemePark2);

        //Testing Ride not found exception for remove ride.
        testThemePark2.removeAttraction("JigJag");


        //Testing Add Customers.
        testThemePark2.AddCustomers("200", "Jennifer-Lauren", 23, 400, "Student");
        testThemePark2.AddCustomers("350", "Ricky Thompson", 12, 30, "None");

        //Testing getCustomers.
        testThemePark2.getCustomer("200");
        testThemePark2.getCustomer("1001");

        //Testing Customer Not Found Exception.
        testThemePark2.removeCustomer("4545");
        System.out.println(testThemePark2);

        //Testing remove customer.
        testThemePark2.removeCustomer("350");
        System.out.println("After applying the remove customer method, the themePark object now contains:" + "\n" + testThemePark2);


    }

}

