package com.HanzChristianJmartMH;
import com.HanzChristianJmartMH.dbjson.Serializable;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Merupakan Class Complaint
 * @author Hanz Christian
 * @version 18 Desember 2021
 */
public class Complaint extends Serializable
{
    public Date date;
    public String desc;

    /**
     * merupakan constructor complaint sesuai parameter
     * @param desc
     */
    public Complaint(String desc){
        this.date = new Date();
        this.desc = desc;
    }

    /**
     * Merupakan method untuk melakukan print terhadap tanggal dan descruption di Complaint
     * @return
     */
    public String toString(){
       SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
       return "Complaint{date=" + formatDate.format(date) + ",desc='" + desc + "'}";
    }
}
