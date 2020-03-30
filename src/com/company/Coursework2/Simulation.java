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
                newPark.AddCustomers(accountNo, customerName, age, accountBalance, discountType);
            }

            while ((attractionLine = attractionInput.readLine()) != null) {
                //Reading attraction details
                String[] attractionDetails;
                attractionDetails = attractionLine.split("@");
                int numberofElementAttractions = attractionDetails.length;
                typeOfAttraction = attractionDetails[2];
                switch (typeOfAttraction) {
                    case "TRA":
                        attractionName = attractionDetails[0].trim();
                        basePrice = Integer.parseInt(attractionDetails[1]);
                        typeOfAttraction = attractionDetails[2];
                        distance = Integer.parseInt(attractionDetails[3]);
                        newPark.AddTransportAttraction(attractionName, basePrice, typeOfAttraction, distance);
                        break;
                    case "GEN":
                        attractionName = attractionDetails[0].trim();
                        basePrice = Integer.parseInt(attractionDetails[1]);
                        typeOfAttraction = attractionDetails[2];
                        capacity = Integer.parseInt(attractionDetails[3]);
                        newPark.AddGentleAttraction(attractionName, basePrice, typeOfAttraction, capacity);
                        break;
                    case "ROL":
                        for (int i = 0; i < attractionDetails.length; i++) {
                            RollerCoaster[i] = attractionDetails[i];
                        }
                        attractionName = attractionDetails[0].trim();
                        basePrice = Integer.parseInt(attractionDetails[1]);
                        typeOfAttraction = attractionDetails[2];
                        minAge = Integer.parseInt(attractionDetails[3]);
                        topSpeed = Float.parseFloat(attractionDetails[4]);
                        newPark.AddRollerCoasterRides(attractionName, basePrice, typeOfAttraction, minAge, topSpeed);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File is not Found.Please put the right file.");
        }
        return newPark;
    }


    //Simulate method
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
                //Execute the transactions.
                switch (instruction) {
                    case "USE_ATTRACTION":
                        System.out.println("The transaction is: " + transactions);
                        typeOfPrice = specificTransactionScanner.next();
                        accountNumber = specificTransactionScanner.next();
                        rideName = specificTransactionScanner.next();

                        transactionAttraction = newThemePark.getAttraction(rideName);
                        basePrice = transactionAttraction.getBasePrice();
                        transactionCustomer = newThemePark.getCustomer(accountNumber);
                        beforeTransactionBalance = transactionCustomer.getAccountBalance();
                        personalDiscount = transactionCustomer.getPersonalDiscount();
                        typeOfRide = transactionAttraction.getTypeOfAttraction();
                        if (typeOfPrice.equals("STANDARD_PRICE")) {
                            switch (personalDiscount) {
                                case "None":
                                    basePrice = basePrice;
                                    if (typeOfRide.equals("ROL")) {
                                        RollerCoaster rol = (RollerCoaster) transactionAttraction;
                                        minAge = rol.getMinAge();
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
                                    basePrice = (int) (0.9 * basePrice);
                                    if (typeOfRide.equals("ROL")) {
                                        RollerCoaster rol = (RollerCoaster) transactionAttraction;
                                        minAge = rol.getMinAge();
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
                                    basePrice = (int) (0.85 * basePrice);
                                    if (typeOfRide.equals("ROL")) {
                                        RollerCoaster rol = (RollerCoaster) transactionAttraction;
                                        minAge = rol.getMinAge();
                                        transactionCustomer.useAttraction(basePrice, minAge);
                                    } else {
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
                            //Get the personal Discount type
                            //For off Peak times
                        } else {
                            if (typeOfRide.equals("ROL")) {
                                RollerCoaster rol = (RollerCoaster) transactionAttraction;
                                basePrice = rol.getOffPeakPrice();

                                switch (personalDiscount) {
                                    case "None":
                                        basePrice = basePrice;
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
                                    case "STUDENT":
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
                    case "ADD_FUNDS":
                        System.out.println("The transaction is: " + transactions);
                        accountNumber = specificTransactionScanner.next();
                        amountToAdd = specificTransactionScanner.nextInt();
                        transactionCustomer = newThemePark.getCustomer(accountNumber);
                        transactionCustomer.addFunds(amountToAdd);
                        System.out.println("\n");

                        break;

                    case "NEW_CUSTOMER":
                        System.out.println("The transaction is: " + transactions);
                        accountNumber = specificTransactionScanner.next();
                        customerName = specificTransactionScanner.next();
                        age = specificTransactionScanner.nextInt();
                        accountBalance = specificTransactionScanner.nextInt();
                        if (specificTransactionScanner.hasNext()) {
                            personalDiscount = specificTransactionScanner.next();
                            newThemePark.AddCustomers(accountNumber, customerName, age, accountBalance, personalDiscount);
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

