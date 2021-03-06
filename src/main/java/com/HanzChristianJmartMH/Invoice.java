package com.HanzChristianJmartMH;
import com.HanzChristianJmartMH.dbjson.Serializable;

import java.util.Date;
import java.util.ArrayList;

/**
 * Merupakan Class Invoice
 * @author Hanz Christian
 * @version 18 Desember 2021
 */
public abstract class Invoice extends Serializable
{
    public Date date;
    public int productId;
    public int complaintID;
    public Rating rating;
//    public Status status;
    public int buyerId;


    public enum Rating{
        NONE, BAD, NEUTRAL, GOOD;
    }
    public enum Status{
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED,FAILED,DELIVERED;
    }

    /**
     * merupakan constructor invoice sesuai dengan parameter
     * @param buyerId id dari user pembeli
     * @param productId id dari product yang dibeli user
     */
    protected Invoice(int buyerId, int productId){
        this.date = new Date();
        this.buyerId = buyerId;
        this.productId = productId;
        this.rating = Rating.NONE;
        this.complaintID = -1;
//      this.status = Status.WAITING_CONFIRMATION;
    }

    /**
     * merupakan method yang digunakan untuk menghitung total biaya yang akan dibayar user
     * @param product
     * @return
     */
    public abstract double getTotalPay(Product product);

}
