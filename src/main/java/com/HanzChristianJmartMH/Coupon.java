package com.HanzChristianJmartMH;

import com.HanzChristianJmartMH.dbjson.Serializable;

/**
 * Merupakan Class Coupon
 * @author Hanz Christian
 * @version 18 Desember 2021
 */
public class Coupon extends Serializable
{
    public  final int code;
    public final double cut;
    public final String name;
    public final double minimum;
    public boolean used = false;
    public final Type type;

    public enum Type {
        DISCOUNT,REBATE
    }

    /**
     * Merupakan constructor coupon sesuai dengan parameter
     * @param name nama dari coupon
     * @param code kode coupon
     * @param type tipe coupon
     * @param cut besarnya potongan coupon
     * @param minimum minimal coupon
     */
    public Coupon( String name, int code, Type type, double cut, double minimum) {

        this.used = false;
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
    }

    /**
     * Merupakan method untuk melakukan getter terhadap penggunaan couponnya
     * @return
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * merupakan method yang digunakan untuk cek kondisi coupon dapat digunakan
     * pada paymentnya atau tidak
     * @param price
     * @param discount
     * @return true jika sesuai dengan kondisi sehingga dapat digunakan, false jika gagal untuk di apply
     */
    public boolean canApply (double price, double discount) {
        if (Treasury.getAdjustedPrice(price, discount)>=minimum && used == false) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Method yang digunakan untuk melakukan pengecekkan apply terhadap couponnya
     * @param price
     * @param discount
     * @return
     */
    public double apply (double price, double discount) {
        this.used =  true;

        if (type == Type.DISCOUNT)
        {
            return Treasury.getAdjustedPrice(price, discount)* (1-(cut/100));
        }
        else //type == type.REBATE
        {
            return Treasury.getAdjustedPrice(price, discount)- price;
        }
    }
}
