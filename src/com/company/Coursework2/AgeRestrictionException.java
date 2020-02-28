package com.company.Coursework2;

import org.w3c.dom.ls.LSOutput;

/******************************************************************************

 File        : AgeRestrictionException.java

 Date        : 24/02/2020

 Author      : Abena Serwaa Johene Amo

 Description : Class to throw an exception when there age is less than the age Limit.

 History     : v 0.01

 Copyright   : (c) Abena Serwaa Johene Amo

 ******************************************************************************/


public class AgeRestrictionException extends Exception {
    public AgeRestrictionException(){
        System.out.println("You are below age limit. Sorry for the inconvenience. Please go on the other available rides");
    }
}
