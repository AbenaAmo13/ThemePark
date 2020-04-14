package com.company.Coursework2;

import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;


import static java.lang.Integer.min;
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
        String noPersonalDiscountTypeCustomers[] = new String[5];
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
        //Set the name of the Theme Park.
        newPark.setParkName("FunTown");
        try {
            BufferedReader customerInput = new BufferedReader(new FileReader("customers.txt"));
            BufferedReader attractionInput = new BufferedReader(new FileReader("attractions.txt"));
            if (!customerInput.ready()) {
                throw new IOException();
            }
            String customerLine;
            String attractionLine;


            while ((customerLine = customerInput.readLine()) != null) {
                //Reading customer Details
                String[] customerDetails;
                customerDetails = customerLine.split("#");

                int numberOfElements = customerDetails.length;
                if (numberOfElements < 5) {
                    for (int i = 0; i < customerDetails.length; i++) {
                        noPersonalDiscountTypeCustomers[i] = customerDetails[i];

                    }
                    accountNo = customerDetails[0];

                    customerName = customerDetails[1];
                    age = parseInt(customerDetails[2]);
                    accountBalance = parseInt(customerDetails[3]);
                    noPersonalDiscountTypeCustomers[4] = "None";
                    discountType = noPersonalDiscountTypeCustomers[4];

                } else {
                    accountNo = customerDetails[0];

                    customerName = customerDetails[1];
                    age = parseInt(customerDetails[2]);
                    accountBalance = parseInt(customerDetails[3]);
                    discountType = customerDetails[4];

                }
                //Add customers to the theme Park using the Add Customers method.
                //Note that the customer details are the ones that have been read from the file.
                newPark.AddCustomers(accountNo, customerName, age, accountBalance, discountType);
            }

            while ((attractionLine = attractionInput.readLine()) != null) {
                //Reading attraction details
                String[] attractionDetails;
                attractionDetails = attractionLine.split("@");
                int numberofElementAttractions = attractionDetails.length;
                //Based on the type of attraction it is add ride to the theme park accordingly.
                typeOfAttraction = attractionDetails[2];
                switch (typeOfAttraction) {
                    case "TRA":
                        attractionName = attractionDetails[0].trim();
                        basePrice = Integer.parseInt(attractionDetails[1]);
                        typeOfAttraction = attractionDetails[2];
                        distance = Integer.parseInt(attractionDetails[3]);
                        //Add transport attractions to the theme park object
                        newPark.AddTransportAttraction(attractionName, basePrice, typeOfAttraction, distance);
                        break;
                    case "GEN":
                        attractionName = attractionDetails[0].trim();
                        basePrice = Integer.parseInt(attractionDetails[1]);
                        typeOfAttraction = attractionDetails[2];
                        capacity = Integer.parseInt(attractionDetails[3]);
                        //Add gentle attractions to the theme park object
                        newPark.AddGentleAttraction(attractionName, basePrice, typeOfAttraction, capacity);
                        break;
                    case "ROL":
                        //Using for loop to increase the size of the attractionDetails.
                        for (int i = 0; i < attractionDetails.length; i++) {
                            RollerCoaster[i] = attractionDetails[i];
                        }
                        attractionName = attractionDetails[0].trim();
                        basePrice = Integer.parseInt(attractionDetails[1]);
                        typeOfAttraction = attractionDetails[2];
                        minAge = Integer.parseInt(attractionDetails[3]);
                        topSpeed = Float.parseFloat(attractionDetails[4]);
                        //Add rollercoaster rides to the theme park object.
                        newPark.AddRollerCoasterRides(attractionName, basePrice, typeOfAttraction, minAge, topSpeed);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            //Print message if file is not found.
            System.out.println("File is not Found.Please put the right file.");
        }
        return newPark;
    }


    //Simulate method to perform transactions in the trasnaction files.
    public static void Simulate(ThemePark newThemePark) throws IOException {
        newThemePark = createThemePark();
        //Read transaction file.
        String transactions;
        String typeOfRide;
        String instruction;
        String typeOfPrice;
        String accountNumber;
        String rideName;
        int amountToAdd;
        String customerName;
        int beforeTransactionBalance;
        int age;
        int minAge;
        int accountBalance;
        String personalDiscount = "";
        int finalProfit = 0;
        String fileName = "transactions.txt";
        int basePrice = 0;
        File file = new File(fileName);
        //ArrayList to store information for all Use Attraction instructions.
        ArrayList transactionsArray = new ArrayList();
        try {
            //Read all the useAttractionTransactions
            File transactionFile = new File("transactions.txt");
            Scanner transactionScanner = new Scanner(transactionFile);
            while (transactionScanner.hasNextLine()) {
                transactions = transactionScanner.nextLine();
                Scanner specificTransactionScanner = new Scanner(transactions).useDelimiter(",");
                instruction = specificTransactionScanner.next();
                Attraction transactionAttraction = null;
                Customer transactionCustomer = null;
                //Execute the transactions based on the specific word.
                switch (instruction) {
                    //If use attraction transaction
                    case "USE_ATTRACTION":
                        System.out.println("The transaction is: " + transactions);
                        typeOfPrice = specificTransactionScanner.next();
                        accountNumber = specificTransactionScanner.next();
                        rideName = specificTransactionScanner.next();
                        //Get all necessary information related to the transaction.
                        transactionAttraction = newThemePark.getAttraction(rideName);
                        basePrice = transactionAttraction.getBasePrice();
                        transactionCustomer = newThemePark.getCustomer(accountNumber);
                        beforeTransactionBalance = transactionCustomer.getAccountBalance();
                        personalDiscount = transactionCustomer.getPersonalDiscount();
                        typeOfRide = transactionAttraction.getTypeOfAttraction();
                        //Determine whether standard or peak price.
                        //If Standard Price
                        if (typeOfPrice.equals("STANDARD_PRICE")) {
                            //Determine which discount is available for the customer (if the customer has one).

                            switch (personalDiscount) {
                                case "None":
                                    basePrice = basePrice;
                                    if (typeOfRide.equals("ROL")) {
                                        RollerCoaster rol = (RollerCoaster) transactionAttraction;
                                        minAge = rol.getMinAge();
                                        //Apply use attraction method for rollercoaster after getting necessary information to do so.
                                        transactionCustomer.useAttraction(basePrice, minAge);
                                        accountBalance = transactionCustomer.getAccountBalance();
                                        //If the transaction balance doesn't change that means error was thrown hence
                                        if (beforeTransactionBalance == accountBalance) {
                                            //Do not add transaction to the total final profit.
                                            finalProfit = finalProfit;
                                            System.out.println("Total Profit: " + finalProfit);
                                            System.out.println("\n");
                                        } //If account balance after transaction is different from before transaction was executed
                                        //That means that transaction was successful hence
                                        else if (beforeTransactionBalance != accountBalance) {
                                            //Add basePrice to the final profit.
                                            finalProfit = finalProfit + basePrice;
                                            System.out.println("Total Profit: " + finalProfit);
                                            System.out.println("\n");
                                        }
                                    } else {
                                        //Once it is not a roller coaster, use the other use attraction method.
                                        transactionCustomer.useAttraction(basePrice);
                                        accountBalance = transactionCustomer.getAccountBalance();
                                        if (beforeTransactionBalance == accountBalance) {
                                            finalProfit = finalProfit;
                                            System.out.println("Total Profit: " + finalProfit);
                                            System.out.println("\n");
                                        } else if (beforeTransactionBalance != accountBalance) {
                                            finalProfit = finalProfit + basePrice;
                                            System.out.println("Total Profit: " + finalProfit);
                                            System.out.println("\n");
                                        }
                                    }

                                    break;
                                case "STUDENT":
                                    //Apply the student discount
                                    basePrice = (int) (0.9 * basePrice);
                                    if (typeOfRide.equals("ROL")) {
                                        RollerCoaster rol = (RollerCoaster) transactionAttraction;
                                        minAge = rol.getMinAge();
                                        // Apply Use attraction method for rollercoaster
                                        transactionCustomer.useAttraction(basePrice, minAge);
                                        accountBalance = transactionCustomer.getAccountBalance();
                                        if (beforeTransactionBalance == accountBalance) {
                                            finalProfit = finalProfit;
                                            System.out.println("Total Profit: " + finalProfit);
                                            System.out.println("\n");
                                        } else if (beforeTransactionBalance != accountBalance) {
                                            finalProfit = finalProfit + basePrice;
                                            System.out.println("Total Profit: " + finalProfit);
                                            System.out.println("\n");
                                        }
                                    } else {
                                        //Apply use attraction method for the other rides.
                                        transactionCustomer.useAttraction(basePrice);
                                        accountBalance = transactionCustomer.getAccountBalance();
                                        if (beforeTransactionBalance == accountBalance) {
                                            finalProfit = finalProfit;
                                            System.out.println("Total Profit: " + finalProfit);
                                            System.out.println("\n");
                                        } else if (beforeTransactionBalance != accountBalance) {
                                            finalProfit = finalProfit + basePrice;
                                            System.out.println("Total Profit: " + finalProfit);
                                            System.out.println("\n");
                                        }
                                    }

                                    break;
                                case "FAMILY":
                                    //Apply family discount on base Price.
                                    basePrice = (int) (0.85 * basePrice);
                                    if (typeOfRide.equals("ROL")) {
                                        RollerCoaster rol = (RollerCoaster) transactionAttraction;
                                        minAge = rol.getMinAge();
                                        //Apply use attraction for rollercoaster.
                                        transactionCustomer.useAttraction(basePrice, minAge);
                                    } else {
                                        //Apply use attraction for the other rides.
                                        transactionCustomer.useAttraction(basePrice);

                                    }
                                    accountBalance = transactionCustomer.getAccountBalance();
                                    if (beforeTransactionBalance == accountBalance) {
                                        finalProfit = finalProfit;
                                        System.out.println("Total Profit: " + finalProfit);
                                        System.out.println("\n");
                                    } else if (beforeTransactionBalance != accountBalance) {
                                        finalProfit = finalProfit + basePrice;
                                        System.out.println("Total profit: " + finalProfit);
                                        System.out.println("\n");
                                    }

                                    break;

                            }

                            //Based on the typeofRide apply the personal discount
                        } else {
                            //Get typeOfRide and place it in variable name "typeOfRide"
                            //Based on that apply the off peak price on the base price.
                            //For rollercoasters, execute use attractions.
                            if (typeOfRide.equals("ROL")) {
                                RollerCoaster rol = (RollerCoaster) transactionAttraction;
                                //Apply off peak price first.
                                basePrice = rol.getOffPeakPrice();
                                //Apply discounts if there are any.
                                switch (personalDiscount) {
                                    case "None":
                                        basePrice = basePrice;
                                        minAge = rol.getMinAge();
                                        //Apply appropriate use attraction.
                                        transactionCustomer.useAttraction(basePrice, minAge);
                                        accountBalance = transactionCustomer.getAccountBalance();
                                        //Check if there was a change in the customers' balance.
                                        if (beforeTransactionBalance == accountBalance) {
                                            //No change then exception was thrown.
                                            // base price won't be added to final profit.
                                            finalProfit = finalProfit;
                                            System.out.println(finalProfit);
                                            System.out.println("\n");
                                        } //Change in account balance means transaction was successful.
                                        else if (beforeTransactionBalance != accountBalance) {
                                            //Add baseprice to finalprfit.
                                            finalProfit = finalProfit + basePrice;
                                            System.out.println("Total profit: " + finalProfit);
                                            System.out.println("\n");
                                        }
                                        break;
                                    case "STUDENT":
                                        //Apply student discount.
                                        basePrice = (int) (0.9 * basePrice);
                                        minAge = rol.getMinAge();
                                        transactionCustomer.useAttraction(basePrice, minAge);
                                        accountBalance = transactionCustomer.getAccountBalance();
                                        if (beforeTransactionBalance == accountBalance) {
                                            finalProfit = finalProfit;
                                            System.out.println(finalProfit);
                                            System.out.println("\n");
                                        } else if (beforeTransactionBalance != accountBalance) {
                                            finalProfit = finalProfit + basePrice;
                                            System.out.println("Total profit: " + finalProfit);
                                            System.out.println("\n");
                                        }
                                        break;

                                    case "FAMILY":
                                        basePrice = (int) (0.85 * basePrice);
                                        minAge = rol.getMinAge();
                                        transactionCustomer.useAttraction(basePrice, minAge);
                                        accountBalance = transactionCustomer.getAccountBalance();
                                        if (beforeTransactionBalance == accountBalance) {
                                            finalProfit = finalProfit;
                                            System.out.println(finalProfit);
                                            System.out.println("\n");
                                        } else if (beforeTransactionBalance != accountBalance) {
                                            finalProfit = finalProfit + basePrice;
                                            System.out.println("Total profit: " + finalProfit);
                                            System.out.println("\n");
                                        }
                                        break;
                                }

                            } else if (typeOfRide.equals("GEN")) {
                                GentleAttraction gen = (GentleAttraction) transactionAttraction;
                                basePrice = gen.getOffPeakPrice();
                                switch (personalDiscount) {
                                    case "None":
                                        transactionCustomer.useAttraction(basePrice);
                                        accountBalance = transactionCustomer.getAccountBalance();
                                        if (beforeTransactionBalance == accountBalance) {
                                            finalProfit = finalProfit;
                                            System.out.println("Total profit: " + finalProfit);
                                            System.out.println("\n");
                                        } else if (beforeTransactionBalance != accountBalance) {
                                            finalProfit = finalProfit + basePrice;
                                            System.out.println("Total profit: " + finalProfit);
                                            System.out.println("\n");
                                        }
                                        break;
                                    case "STUDENT":
                                        basePrice = (int) (0.9 * basePrice);
                                        transactionCustomer.useAttraction(basePrice);
                                        accountBalance = transactionCustomer.getAccountBalance();
                                        if (beforeTransactionBalance == accountBalance) {
                                            finalProfit = finalProfit;
                                            System.out.println("Total profit: " + finalProfit);
                                            System.out.println("\n");
                                        } else if (beforeTransactionBalance != accountBalance) {
                                            finalProfit = finalProfit + basePrice;
                                            System.out.println("Total profit: " + finalProfit);
                                            System.out.println("\n");
                                        }
                                        break;

                                    case "FAMILY":
                                        basePrice = (int) (0.85 * basePrice);
                                        transactionCustomer.useAttraction(basePrice);
                                        accountBalance = transactionCustomer.getAccountBalance();
                                        if (beforeTransactionBalance == accountBalance) {
                                            finalProfit = finalProfit;
                                            System.out.println("Total profit: " + finalProfit);
                                            System.out.println("\n");
                                        } else if (beforeTransactionBalance != accountBalance) {
                                            finalProfit = finalProfit + basePrice;
                                            System.out.println("Total profit: " + finalProfit);
                                            System.out.println("\n");
                                        }
                                        break;
                                }

                            } else if (typeOfRide.equals("TRA")) {
                                TransportAttraction tra = (TransportAttraction) transactionAttraction;
                                basePrice = tra.getOffPeakPrice();
                                System.out.println(basePrice);
                                switch (personalDiscount) {
                                    case "None":
                                        transactionCustomer.useAttraction(basePrice);
                                        accountBalance = transactionCustomer.getAccountBalance();
                                        if (beforeTransactionBalance == accountBalance) {
                                            finalProfit = finalProfit;
                                            System.out.println("Total profit " + finalProfit);
                                            System.out.println("\n");
                                        } else if (beforeTransactionBalance != accountBalance) {
                                            finalProfit = finalProfit + basePrice;
                                            System.out.println("Total profit " + finalProfit);
                                            System.out.println("\n");
                                        }
                                        break;
                                    case "STUDENT":
                                        basePrice = (int) (0.9 * basePrice);
                                        transactionCustomer.useAttraction(basePrice);
                                        accountBalance = transactionCustomer.getAccountBalance();
                                        if (beforeTransactionBalance == accountBalance) {
                                            finalProfit = finalProfit;
                                            System.out.println("Total profit: " + finalProfit);
                                            System.out.println("\n");
                                        } else if (beforeTransactionBalance != accountBalance) {
                                            finalProfit = finalProfit + basePrice;
                                            System.out.println("Total profit: " + finalProfit);
                                            System.out.println("\n");
                                        }
                                        break;

                                    case "FAMILY":
                                        basePrice = (int) (0.85 * basePrice);
                                        transactionCustomer.useAttraction(basePrice);
                                        accountBalance = transactionCustomer.getAccountBalance();
                                        if (beforeTransactionBalance == accountBalance) {
                                            finalProfit = finalProfit;
                                            System.out.println("Total profit: " + finalProfit);
                                            System.out.println("\n");
                                        } else if (beforeTransactionBalance != accountBalance) {
                                            finalProfit = finalProfit + basePrice;
                                            System.out.println("Total profit: " + finalProfit);
                                            System.out.println("\n");
                                        }

                                        break;
                                }

                            }

                        }
                        break;
                        //Execute add funds transaction.
                    case "ADD_FUNDS":
                        System.out.println("The transaction is: " + transactions);
                        accountNumber = specificTransactionScanner.next();
                        amountToAdd = specificTransactionScanner.nextInt();
                        transactionCustomer = newThemePark.getCustomer(accountNumber);
                        transactionCustomer.addFunds(amountToAdd);
                        System.out.println("\n");

                        break;
                    //Exeucte new customer transaction
                    case "NEW_CUSTOMER":
                        System.out.println("The transaction is: " + transactions);
                        accountNumber = specificTransactionScanner.next();
                        customerName = specificTransactionScanner.next();
                        age = specificTransactionScanner.nextInt();
                        accountBalance = specificTransactionScanner.nextInt();
                        if (specificTransactionScanner.hasNext()) {
                            personalDiscount = specificTransactionScanner.next();
                            newThemePark.AddCustomers(accountNumber, customerName, age, accountBalance, personalDiscount);
                            //Apply getCustomer method
                            transactionCustomer = newThemePark.getCustomer(accountNumber);
                            System.out.println("\n");

                        } else {
                            personalDiscount = "None";
                            newThemePark.AddCustomers(accountNumber, customerName, age, accountBalance, personalDiscount);
                            transactionCustomer = newThemePark.getCustomer(accountNumber);
                            System.out.println("\n");
                        }


                }


            }

        } catch (FileNotFoundException e) {
            System.out.println("Your file is not found. Please try again");

        }
    }

    //Testing harness.
    public static void main(String[] args) throws IOException {
        //Testing createThemePark() method.
        System.out.println(createThemePark());
        Simulate(createThemePark());


    }


}

