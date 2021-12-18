package com.HanzChristianJmartMH;

/**
 * Merupakan Class Treasury
 * @author Hanz Christian
 * @version 18 Desember 2021
 */
public class Treasury
{
    public static final double COMMISSION_MULTIPLIER = 0.05;
    public static final double BOTTOM_PRICE = 20000.0;
    public static final double BOTTOM_FEE = 1000.0;

    /**
     * Merupakan method untuk mendapatkan harga yang sudah dilakukan diskon
     * @param price harga aslinya
     * @param discount besarnya diskon
     * @return nilai harga yang sudah diskon
     */
    private static double getDiscountedPrice(double price, double discount)
    {
        if(discount >= 100.0){
            return 100.0;
        }

        if(discount==100.0){
            return 0.0;
        }

        return(price-(price*(discount/100)));
    }

    /**
     * Merupakan method untuk mendapatkan biaya administrasinya
     * @param price harga aslinya
     * @param discount besarnya diskon
     * @return nilai biaya administrasi
     */
    public static double getAdminFee(double price, double discount)
    {
        double discountprice = getDiscountedPrice(price,discount);
        if(discountprice <= BOTTOM_PRICE){
            return BOTTOM_FEE;
        }
        else{
            discountprice = discountprice - (discountprice*COMMISSION_MULTIPLIER);
            return getDiscountedPrice(price,discount);
        }
    }

    /**
     * Merupakan method untuk mendapatkan harga yang sudah didiskon dan disesuaikan dengan biaya administrasi juga
     * @param price
     * @param discount
     * @return biaya yang sudah adjusted
     */
    public static double getAdjustedPrice(double price, double discount)
    {
        double discountprice = getDiscountedPrice(price,discount) + getAdminFee(price,discount);
        return discountprice;
    }

}

