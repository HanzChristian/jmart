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

public class CouponController implements BasicGetController<Coupon> {
    @JsonAutowired(value = Coupon.class, filepath = "Coupon.json")
    public static JsonTable<Coupon> couponTable;

    @GetMapping("/{id}/canApply")
    boolean canApply(@PathVariable int id, @RequestParam double price, @RequestParam double discount){
        for(Coupon c : couponTable){
            if(c.id == id){
                return c.canApply(price, discount);
            }
        }
        return false;
    }

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

    public JsonTable<Coupon> getJsonTable(){
        return couponTable;
    }

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
