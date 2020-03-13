package com.company.Coursework2;

import java.io.*;

import java.util.ArrayList;


import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

/******************************************************************************

 File        : Simulation.java

 Date        : 24/02/2020

 Author      : Abena Serwaa Johene Amo

 Description : Class to simulate the use of a theme park.

 History     : v 0.01

 Copyright   : (c) Abena Serwaa Johene Amo
 ******************************************************************************/

public class Simulation {
    //Method to create the theme Park
    public static ThemePark createThemePark() throws IOException {
        //Variables for Customers
        ArrayList tempCustomerArray = new ArrayList();
        String accountNo;
        String customerName;
        int age;
        int accountBalance;
        String discountType;
        String noPersonalDiscountTypeCustomers[]= new String[5];
        String RollerCoaster[] = new String[5];

        //Variables for Attraction
        String attractionName;
        int basePrice;
        String typeOfAttraction;
        int distance;
        int capacity;
        int minAge;
        float topSpeed;
        ThemePark newPark = new ThemePark();
        newPark.setParkName("FunTown");
        try{
            BufferedReader customerInput = new BufferedReader(new FileReader("customers.txt"));
            BufferedReader attractionInput = new BufferedReader(new FileReader("attractions.txt"));
            if (!customerInput.ready()) {
                throw new IOException();
            }
            String customerLine;
            String attractionLine;
            while ((customerLine = customerInput.readLine()) != null &&((attractionLine= attractionInput.readLine())!= null)){
                //Reading customer Details
                String[] customerDetails;
               customerDetails = customerLine.split("#");
                int numberOfElements = customerDetails.length;
                if(numberOfElements<5){
                    for(int i=0; i < customerDetails.length; i++){
                      noPersonalDiscountTypeCustomers[i] = customerDetails[i];
                    }
                    accountNo = customerDetails[0];
                    customerName = customerDetails[1];
                    age = parseInt(customerDetails[2]);
                    accountBalance = parseInt(customerDetails[3]);
                    noPersonalDiscountTypeCustomers[4]= "None";
                    discountType = noPersonalDiscountTypeCustomers[4];
                    newPark.AddCustomers(accountNo, customerName, age, accountBalance, discountType);
                } else {
                    accountNo = customerDetails[0];
                    customerName = customerDetails[1];
                    age = parseInt(customerDetails[2]);
                    accountBalance = parseInt(customerDetails[3]);
                    discountType = customerDetails[4];
                    newPark.AddCustomers(accountNo, customerName, age, accountBalance, discountType);
                }
                    //Reading attraction details
                    String [] attractionDetails;
                    attractionDetails= attractionLine.split("@");
                    int numberofElementAttractions = attractionDetails.length;
                    typeOfAttraction=attractionDetails[2];
                    switch(typeOfAttraction){
                        case "TRA" :
                            attractionName= attractionDetails[0];
                            basePrice= Integer.parseInt(attractionDetails[1]);
                            typeOfAttraction= attractionDetails[2];
                            distance = Integer.parseInt(attractionDetails[3]);
                            newPark.AddTransportAttraction(attractionName,basePrice, typeOfAttraction,distance );
                            break;

                        case "GEN":
                            attractionName= attractionDetails[0];
                            basePrice= Integer.parseInt(attractionDetails[1]);
                            typeOfAttraction= attractionDetails[2];
                            capacity= Integer.parseInt(attractionDetails[3]);
                            newPark.AddGentleAttraction(attractionName,basePrice, typeOfAttraction,capacity);
                            break;
                        case "ROL":
                            for(int i=0; i < attractionDetails.length; i++){
                              RollerCoaster[i] = attractionDetails[i];
                            }
                            attractionName= attractionDetails[0];
                            basePrice= Integer.parseInt(attractionDetails[1]);
                            typeOfAttraction= attractionDetails[2];
                            minAge= Integer.parseInt(attractionDetails[3]);
                            topSpeed= Float.parseFloat(attractionDetails[4]);
                            newPark.AddRollerCoasterRides(attractionName, basePrice, typeOfAttraction, minAge, topSpeed);
                            break;





                    }




                }









            }
        catch(FileNotFoundException e){
            System.out.println("File is not Found.Please put the right file.");
        }

        return newPark;


    }







    //Testing harness.
    public static void main(String[] args) throws IOException {
        //Testing whether the txt files was correctly placed in the themePark object
        System.out.println(createThemePark());


    }


}

