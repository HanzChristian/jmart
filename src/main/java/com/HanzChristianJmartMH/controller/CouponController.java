package com.HanzChristianJmartMH.controller;

import com.HanzChristianJmartMH.Algorithm;
import com.HanzChristianJmartMH.Coupon;
import com.HanzChristianJmartMH.dbjson.JsonAutowired;
import com.HanzChristianJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


/**
 * Merupakan sebuah class yang digunakan untuk melakukan modifikasi terhadap Coupon
 */

public class CouponController implements BasicGetController<Coupon> {
    @JsonAutowired(value = Coupon.class, filepath = "Coupon.json")
    public static JsonTable<Coupon> couponTable;

    /**
     * Merupakan sebuah method yang digunakan untuk melakukan pengecekkan terhadap Coupon
     * berdasarkan parameter idnya apakah dapat diapply atau tidak
     * @param id merupakan id terhadap Coupon yang akan diapply
     * @param price  merupakan harga dari productnya
     * @param discount  merupakan discount dari productnya
     * @return berupa boolean true ketika coupon applied dan false ketika coupon gagal
     */

    @GetMapping("/{id}/canApply")
    boolean canApply(@PathVariable int id, @RequestParam double price, @RequestParam double discount){
        for(Coupon c : couponTable){
            if(c.id == id){
                return c.canApply(price, discount);
            }
        }
        return false;
    }

    /**
     * Merupakan sebuah method yang digunakan untuk mencari Coupon yang available
     * dan menampilkan coupon berdasarkan paginasi parameter index dan besarnya page
     * @param page merupakan index pagenya
     * @param pageSize merupakan besarnya page yang diakses
     */
    @GetMapping("/getAvailable")
    List<Coupon> getAvailable(@RequestParam int page, @RequestParam int pageSize){
        ArrayList<Coupon> List = new ArrayList<>();
        for(Coupon c : couponTable){
            if(!c.isUsed()){
                List.add(c);
            }
        }
        return Algorithm.<Coupon>paginate(List, page, pageSize, c -> true);
    }

    /**
     * Merupakan sebuah method yang digunakan untuk list terhadap Coupon yang terdapat
     * pada file Jsonnya
     * @return berupa list yang berisi Coupon pada file Json
     */

    public JsonTable<Coupon> getJsonTable(){
        return couponTable;
    }

    /**
     * Merupakan sebuah method yang menentukan apakah Coupon tersebut sudah used atau belom
     * @param id merupakan id dari coupon yang ingin dicek
     * @return berupa boolean true ketika pernah digunakan dan false ketika belum
     */

    @GetMapping("/{id}/isUsed")
    boolean isUsed(@PathVariable int id){
        for(Coupon c : couponTable){
            if(c.id == id){
                return c.isUsed();
            }
        }
        return false;
    }
}
