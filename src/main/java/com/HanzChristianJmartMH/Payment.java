package com.HanzChristianJmartMH;

import com.HanzChristianJmartMH.Invoice;
import com.HanzChristianJmartMH.Product;
import com.HanzChristianJmartMH.Shipment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Merupakan Class Payment
 * @author Hanz Christian
 * @version 18 Desember 2021
 */
public class Payment extends Invoice
{
    public Shipment shipment;
    public int productCount;
    public ArrayList<Record> history = new ArrayList<>();
    public int storeId;

    /**
     * Merupakan class untuk Record
     */
    public static class Record{
        public Status status;
        public final Date date;
        public String message;

        /**
         * Merupakan constructor untuk Record sesuai dengan parameter
         * @param status merupakan status pengiriman
         * @param message merupakan pesan yang diberikan sesuai dengan statusnya
         */
        public Record(Status status, String message){
            this.status = status;
            this.message = message;
            this.date = Calendar.getInstance().getTime();
        }
    }

    /**
     * Merupakan constructor untuk Payment sesuai dengan parameter
     * @param buyerId
     * @param productId
     * @param productCount
     * @param shipment
     * @param storeId
     */
    public Payment(int buyerId, int productId, int productCount, Shipment shipment,int storeId){
        super(buyerId,productId);
        this.productCount = productCount;
        this.shipment = shipment;
        this.storeId = storeId;
    }

    /**
     * Merupakan method untuk mendapatkan total harga
     * @param product
     * @return perhitungan dari total harga
     */
    public double getTotalPay(Product product) {
        return ((productCount * product.price) + shipment.cost);
    }

}

