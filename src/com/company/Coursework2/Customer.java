package com.company.Coursework2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/******************************************************************************

     File        : Customer.java

 Date        : 12/02/2020

 Author      : Abena Serwaa Johene Amo

 Description : Customer class to store information about Customers

 History     : v 0.01

 Copyright   : (c) Abena Serwaa Johene Amo

 ******************************************************************************/

public class Customer {

    ArrayList customer = new ArrayList();
    //Fields with their accessors and mutators.
    String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    int accountNumber;
    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    int age;
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    int personalDiscount;
    public int getPersonalDiscount(){
        return personalDiscount;
    }
    public void setPersonalDiscount(int personalDiscount) {
        this.personalDiscount = personalDiscount;
    }
    int accountBalance;
    public int getAccountBalance() {
        return accountBalance;
    }
    //Constructor to create customer object.
    public Customer(String name, int age, int accountNumber, int personalDiscount, int accountBalance){
        this.name=name;
        if(age>=0) {
            this.age = age;
        }
        this.accountNumber=accountNumber;
        this.personalDiscount=personalDiscount;
        if(accountBalance>=0) {
            this.accountBalance = accountBalance;
        }
    }
    //Method to add funds for customer
    public int addFunds(int amountToAdd){
        String topUp;
        System.out.println("Do you want to top up your account?.Type yes/no");
        Scanner scanner = new Scanner(System.in);
        topUp= scanner.next();
        if(topUp.equalsIgnoreCase("yes")){
            System.out.println("Add this amount to by balance:");
            amountToAdd= scanner.nextInt();
            accountBalance +=amountToAdd;

        }else{
            System.out.println("Thank you for your time");
        }
        return accountBalance;
    }
    //Method to use attraction
    //Variable to store the age limit of a ride.
    int ageLimit;

    public int useAttraction(int attractionPrice, int ageLimit ) throws InsufficientBalanceException, AgeRestrictionException {
        int customerBalance = getAccountBalance();
        try {

            int age = getAge();
            if (customerBalance <= 0) {
                throw new InsufficientBalanceException();

            } else if (age < ageLimit) {
                throw new AgeRestrictionException();
            } //
            else {
                //Read files and simulate the
                try {
                    BufferedReader input = new BufferedReader(new FileReader("transactions.txt"));
                    if (!input.ready()) {
                        throw new IOException();
                    }
                    String instruction;

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            }
        catch (Exception e) {
            System.out.println(e);
            System.out.print("Your available balance is: ");


        }
        return customerBalance;
    }

    //Method to return available discount Info.
    public static String getAvailableDiscount(){
        String discountType;
        String discountInfo;
        System.out.println("Do you want information on personal or off peak discount. Type'personal' or 'offpeak')");
        Scanner scanner = new Scanner(System.in);
        discountType= scanner.next();
        if(discountType.equalsIgnoreCase("personal")){
             discountInfo = "Students receive an additional 10% off and family members of park employees receive 15% off";
            System.out.println(discountInfo);
        }
        else
            if (discountType.equalsIgnoreCase("offpeak")){
                discountInfo = "Transport Attraction is 50% off during off peak times" + "\n"+
                        "Gentle attractions are 80% off during off peak times"+"\n" +
                        "Roller Coasters do not have discounts during off peak times";
            System.out.println(discountInfo);
            }
        else{
            discountInfo= "Please type personal  information on personal discounts or offpeak for off peak information";
            System.out.println(discountInfo);
            }

        return discountInfo;
    }
    //toString method to be able to print the things in the object instead reference.
    public String toString(){
        return name +" " + age + " " + accountNumber + " " + personalDiscount +" "+ accountBalance;
    }

    //Test harness to test if methods work.
    public static void main(String[] args) throws InsufficientBalanceException, AgeRestrictionException {
        ArrayList customer = new ArrayList();
      Customer newCustomer = new Customer("Destiny", 16, 100201, 10 ,10);
      //Testing accessor methods
      String name = newCustomer.getName();
      System.out.println("Initial name was : " + name);
      //Testing mutator methods.
       newCustomer.setName("Lila");
       //Name should now be Lila when printed out in the array.
       customer.add(newCustomer);
      System.out.println(customer);
      int balance = newCustomer.useAttraction(20, 12);
      System.out.println(balance);



    }





}
