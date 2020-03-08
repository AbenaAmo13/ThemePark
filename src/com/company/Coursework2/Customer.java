package com.company.Coursework2;

import java.awt.event.WindowFocusListener;
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

 THINGS TO DO:
 1. USE ATTRACTION.
 2. OVERLOAD
 3.ADD FUNDS
 4. GO THROUGH METHODS AND SEE WHETHER THE READING OF FILES SHOULD BE DONE HERE

 ******************************************************************************/
public class Customer {
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
    double personalDiscount;
    public double getPersonalDiscount(){
        return personalDiscount;
    }
    public void setPersonalDiscount(int personalDiscount) {
        this.personalDiscount = personalDiscount;
    }
    int accountBalance;
    public int getAccountBalance() {
        return accountBalance;
    }
    String personalDiscountType;
    public String getPersonalDiscountType() {
        return personalDiscountType;
    }
    public void setPersonalDiscount(){}

    //Method to add funds for customer
    public void addFunds(int amountToAdd){
        //Get Customer
        // Add Funds to customer
        }
    //First useAttraction
    public void useAttraction(int attractionPrice ) throws InsufficientBalanceException{ }

    //Method to return available discount Info.
    public static String getAvailableDiscount(){
        String infoDiscount="";
        return infoDiscount;}
    //toString method to be able to print the things in the object instead reference.
    public String toString(){
        return name +" " + age + " " + accountNumber + " " + personalDiscountType +" "+ accountBalance;
    }
    //Creating Constructors for Customers
    public Customer(int accountNumber, String name, int age, int accountBalance, String personalDiscountType){
        this.accountNumber=accountNumber;
        this.name=name;
        this.age=age;
        this.accountBalance=accountBalance;
        this.personalDiscountType=personalDiscountType;
    }
    //Test harness
    public static void main(String[] args){
        //Testing constructor, acessors and mutators.
        Customer Destiny = new Customer(100288, "Destiny",20, 200, "Family" );
       int destinysBalance= Destiny.getAccountBalance();

        System.out.println(Destiny+"\n"+"Her account Balance is: "+destinysBalance);

    }







}
