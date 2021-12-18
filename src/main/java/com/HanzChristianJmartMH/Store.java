package com.HanzChristianJmartMH;
import com.HanzChristianJmartMH.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Merupakan Class Store
 * @author Hanz Christian
 * @version 18 Desember 2021
 */
public class Store extends Serializable
{
    public String name;
    public String address;
    public String phoneNumber;
    public Account account;
    public double balance;
    public static final String REGEX_NAME = "^[A-Z](?!.*(\\s)\1).{4,20}$";
    public static final String REGEX_PHONE = "^\\d{9,12}$";

    /**
     * Merupakan method untuk pembandingan nama dan nomor telpon sesuai dengan REGEX
     * @return berupa boolean true ketika kondisi REGEX terpenuhi, false jika tidak
     */
    public boolean validate(){
        Pattern validateName = Pattern.compile(REGEX_NAME);
        Pattern validatePhone = Pattern.compile(REGEX_PHONE);
        Matcher matchName = validateName.matcher(this.name);
        Matcher matchPhone = validatePhone.matcher(this.phoneNumber);
        boolean nameFound = matchName.find();
        boolean phoneFound = matchPhone.find();
        
        if(nameFound == true && phoneFound == true){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Merupakan constructor store sesuai dengan parameternya
     * @param name nama storenya
     * @param address alamat storenya
     * @param phoneNumber nomor telpon store
     * @param balance besarnya balance store
     */
    public Store(String name, String address, String phoneNumber, double balance){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    /**
     * Merupakan method untuk melakukan print terhadap nilai parameter yang diberikan
     * @return print nilai parameter
     */
    public String toString(){
        return "name: " + this.name + "\naddress: " + this.address + "\nphoneNumber: " + this.phoneNumber;
    }
}
