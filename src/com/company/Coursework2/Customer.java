package com.company.Coursework2;

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
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    private String accountNumber;
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    private int age;
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
     private String personalDiscount;

    private int accountBalance;
    public int getAccountBalance() {
        return accountBalance;
    }
    public String getPersonalDiscount() {
        return personalDiscount;
    }
    public void setPersonalDiscount(String personalDiscount){
        this.personalDiscount=personalDiscount;

}

    //Method to add funds for customer
    public void addFunds(int amountToAdd) {
        if (amountToAdd >= 0) {
           accountBalance += amountToAdd;
            System.out.println("New account balance is " + accountBalance);
        }else{
            System.out.println("Amount added should be greater than 0.");
        }
    }

    //First useAttraction
    public void useAttraction(int attractionPrice ){
        try{
            if(attractionPrice < accountBalance){
                accountBalance=accountBalance-attractionPrice;
            }
            else{
                throw new InsufficientBalanceException();
            }
            System.out.println("Your account after use Attraction: "+ accountBalance) ;

        }
        catch(Exception e){}
    }
    //Overload use Attraction
    public void useAttraction(int attractionPrice, int ageLimit){
        try{
            if(ageLimit>age){
                throw new AgeRestrictionException();
            }
            else if(attractionPrice < accountBalance){
                accountBalance=accountBalance-attractionPrice;
            }
            else{
                throw new InsufficientBalanceException();
            }
            System.out.println("Your account after use Attraction: "+ accountBalance) ;

        }
        catch(Exception e){}
    }
    //Method to return available discount Info.
    public static String getAvailableDiscount(){
        String infoDiscount="Students receive an additional 10% off and family members of park employees receive 15% off";
        return infoDiscount;}
    //toString method to be able to print the things in the object instead reference.
    public String toString(){
        return name +" " + age + " " + accountNumber + " " + personalDiscount +" "+ accountBalance;
    }
    //Creating Constructors for Customers
    public Customer(String accountNumber, String name, int age, int accountBalance, String personalDiscount){
        this.accountNumber=accountNumber;
        this.name=name;
        this.age=age;
        this.accountBalance=accountBalance;
        this.personalDiscount=personalDiscount;
    }
    //Test harness
    public static void main(String[] args){
        //Testing constructor, acessors and mutators.
        Customer Destiny = new Customer("100288", "Destiny",20, 200, "Family" );
        int destinysBalance= Destiny.getAccountBalance();
        System.out.println(Destiny+"\n"+"Her account Balance is: "+destinysBalance);
        //Testing add Funds.
        Destiny.addFunds(100);
        //Testing use Attraction.
        Destiny.useAttraction(2);
        //Testing use Attraction overLoad.
        Destiny.useAttraction(12, 16);

         //Testing add Funds part 2
        Destiny.addFunds(300);
        //Set the personal discount type
        Destiny.setPersonalDiscount("Student");


    }







}
