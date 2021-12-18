package com.HanzChristianJmartMH;
import com.HanzChristianJmartMH.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Merupakan Class Account
 * @author Hanz Christian
 * @version 18 Desember 2021
 */
public class Account extends Serializable
{
    public String name;
    public String email;
    public String password;
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+(\\.[a-zA-Z0-9&*_~]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.* ).{8,}$";
    public double balance;
    public Store store;

    /**
     * Constructor untuk parameter pada class Account
     * @param name nama akun
     * @param email nama email
     * @param password password yang dimasukkan
     * @param balance besarnya balance akun
     */
    public Account(String name,String email,String password,double balance)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    /**
     * Merupakan method untuk melakukan validasi terhadap email dan password yang dimasukkan sesuai dengan
     * REGEX yang tersedia
     * @return memberikan boolean true ketika email dan password yang dimasukkan match, false jika salah satu/keduanya tidak match
     */
    public boolean validate(){
        Pattern patternEmail = Pattern.compile(REGEX_EMAIL);
        Matcher matcherEmail = patternEmail.matcher(this.email);
        Pattern patternPass = Pattern.compile(REGEX_PASSWORD);
        Matcher matcherPass = patternPass.matcher(this.password);
        boolean matchEmail = matcherEmail.find();
        boolean matchPass = matcherPass.find();

        if (matchEmail == true && matchPass==true){
            return true;
        }
        else {
            return false;
        }
    }
}
