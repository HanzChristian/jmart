package com.HanzChristianJmartMH;

/**
 * Merupakan Class PhoneTopUp
 * @author Hanz Christian
 * @version 18 Desember 2021
 */
public class PhoneTopUp extends Invoice {
    public String phoneNumber;
    public Status status;

    /**
     * Merupakan constructor untuk PhoneTopUp sesuai dengan parameter
     * @param buyerID id dari pembeli
     * @param productId id dari product yang dibeli pembeli
     * @param phoneNumber nomor telpon pembeli
     */
    public PhoneTopUp (int buyerID,int productId, String phoneNumber){
        super(buyerID,productId);
        this.phoneNumber = phoneNumber;
    }

    /**
     * Mendapatkan total biaya
     * @param product
     * @return total biaya berdasarkan price pada productnya masing-masing
     */
    @Override
    public double getTotalPay (Product product){
        return product.price;
    }
}
