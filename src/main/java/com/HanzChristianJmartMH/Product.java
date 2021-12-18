package com.HanzChristianJmartMH;

import com.HanzChristianJmartMH.dbjson.Serializable;

/**
 * Merupakan Class Product
 * @author Hanz Christian
 * @version 18 Desember 2021
 */
public class Product extends Serializable
{
    public int accountId;
    public double discount;
    public double price;
    public byte shipmentPlans;
    public String name;
    public int weight;
    public boolean conditionUsed;
    public ProductCategory category;

    /**
     * Merupakan constructor untuk Product sesuai dengan parameter
     * @param accountId id dari akun yang membentuk product
     * @param discount besarnya discount pada product
     * @param price harga dari product
     * @param shipmentPlans tipe shipment product
     * @param name nama product
     * @param weight beratnya product
     * @param conditionUsed kondisi 'used' atau 'new' pada product
     * @param category kategori productnya
     */
    public Product(int accountId, double discount, double price, byte shipmentPlans, String name, int weight, boolean conditionUsed, ProductCategory category) {
        this.accountId = accountId;
        this.discount = discount;
        this.price = price;
        this.shipmentPlans = shipmentPlans;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.category = category;
    }

    /**
     * Melakukan print terhadap hasil parameter yang dimasukkan
     * @return print keseluruhan isi parameter
     */
    public String toString(){
        return "Name: " + this.name + "\nWeight: " + this.weight +"\nconditionUsed: " + this.conditionUsed +"\npriceTag: " + this.price +"\ncategory: " + this.category +"\nweight: " + this.weight +"\naccount ID: " + this.accountId;
    }
}
