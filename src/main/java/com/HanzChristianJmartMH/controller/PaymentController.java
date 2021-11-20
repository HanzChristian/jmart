package com.HanzChristianJmartMH.controller;

import com.HanzChristianJmartMH.ObjectPoolThread;
import com.HanzChristianJmartMH.Payment;
import com.HanzChristianJmartMH.controller.BasicGetController;
import com.HanzChristianJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;

public class PaymentController implements BasicGetController<Payment> {
    public static final long DELIVERED_LIMIT_MS = 6;
    public static final long ON_DELIVERY_LIMIT_MS = 9;
    public static final long ON_PROGRESS_LIMIT_MS = 6;
    public static final long WAITING_CONF_LIMIT_MS = 9;

    public static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread;

    @PostMapping("/{id}/accept")
    public boolean accept(int id){
        return false;
    }

    @PostMapping("/{id}/cancel")
    public boolean cancel(int id){
        return false;
    }

    @PostMapping("/create")
    public Payment create(int buyerId, int productId, int productCount, String shipmentAddress, byte shipmentPlan){
        return null;
    }

    public JsonTable<Payment> getJsonTable(){
        return null;
    }

    @PostMapping("/{id}/submit")
    public boolean submit(int id,String receipt){
        return false;
    }

    private static boolean timekeeper(Payment payment){
        return false;
    }
}
