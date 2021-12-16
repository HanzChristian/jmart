package com.HanzChristianJmartMH;

import com.HanzChristianJmartMH.dbjson.Serializable;

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

    public Coupon( String name, int code, Type type, double cut, double minimum) {

        this.used = false;
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
    }


    public boolean isUsed() {
        return used;
    }

    public boolean canApply (double price, double discount) {
        if (Treasury.getAdjustedPrice(price, discount)>=minimum && used == false) {
            return true;
        }
        else {
            return false;
        }
    }

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
