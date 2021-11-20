package com.HanzChristianJmartMH;
import com.HanzChristianJmartMH.dbjson.Serializable;

import java.util.Date;
import java.util.ArrayList;

public abstract class Invoice extends Serializable
{
    public Date date;
    public int productId;
    public int complaintID;
    public Rating rating;
//    public Status status;
    public int buyerId;

    enum Rating{
        NONE, BAD, NEUTRAL, GOOD;
    }
    enum Status{
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED,FAILED,DELIVERED;
    }
    
    public ArrayList<Record> history = new ArrayList<Record>();
    class Record{
        public Date date;
        public String message;
        public Status status;
    }
    
    
    protected Invoice(int buyerId, int productId){
        this.date = new Date();
        this.buyerId = buyerId;
        this.productId = productId;
        this.rating = Rating.NONE;
        this.complaintID = -1;
//      this.status = Status.WAITING_CONFIRMATION;
    }

    
    public abstract double getTotalPay(Product product);

}