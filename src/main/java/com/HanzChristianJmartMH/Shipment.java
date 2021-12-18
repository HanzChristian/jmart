package com.HanzChristianJmartMH;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

/**
 * Merupakan Class Shipment
 * @author Hanz Christian
 * @version 18 Desember 2021
 */
public class Shipment {
    public String address;
    public int cost;
    public byte plan;
    public String receipt;
    public static final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat("EEE MMMM dd yyyy");
    public static Plan INSTANT = new Plan((byte) 1);
    public static Plan SAME_DAY = new Plan((byte) (1 << 1));
    public static Plan NEXT_DAY = new Plan((byte) (1 << 2));
    public static Plan REGULER = new Plan((byte) (1 << 3));
    public static Plan KARGO = new Plan((byte) (1 << 4));


    /**
     * Merupakan class sistem shipmentnya
     */
    static class Plan {

        public final byte bit;

        private Plan(byte bit) {
            this.bit = bit;
        }
    }

    /**
     * Constructor dari shipment sesuai dengan parameter
     *
     * @param address
     * @param cost
     * @param plan
     * @param receipt
     */
    public Shipment(String address, int cost, byte plan, String receipt) {
        this.address = address;
        this.cost = cost;
        this.plan = plan;
        this.receipt = receipt;
    }

    /**
     * Merupakan method untuk memberikan estimasi sampainya pesanan dari user
     * @param reference
     * @return estimasi dalam bentuk waktu
     */
    public String getEstimatedArrival(Date reference) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(reference);
        if (plan == NEXT_DAY.bit) {
            cal.add(Calendar.DATE, 1);
        } else if (plan == REGULER.bit) {
            cal.add(Calendar.DATE, 2);
        } else if (plan == KARGO.bit) {
            cal.add(Calendar.DATE, 5);
        }
        return ESTIMATION_FORMAT.format(cal.getTime());
    }

    /**
     * Method yang digunakan untuk pengecekkan shipment
     * @param reference
     * @return true jika pengecekkan sesuai, dan false jika tidak sesuai
     */
    public boolean isDuration(Plan reference) {
        return (this.plan & reference.bit) == reference.bit;
    }

    /**
     * Method yang digunakan untuk mengecek apakah shipment berupa byte
     * @param object
     * @param reference
     * @return true jika pengecekkan sesuai, dan false jika tidak sesuai
     */
    public static boolean isDuration(byte object, Plan reference) {
        return (object & reference.bit) == reference.bit;
    }
}
    
