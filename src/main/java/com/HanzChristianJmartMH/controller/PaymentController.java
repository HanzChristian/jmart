package com.HanzChristianJmartMH.controller;

import com.HanzChristianJmartMH.Invoice;
import com.HanzChristianJmartMH.ObjectPoolThread;
import com.HanzChristianJmartMH.Payment;
import com.HanzChristianJmartMH.Shipment;
import com.HanzChristianJmartMH.controller.BasicGetController;
import com.HanzChristianJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.Date;

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
    public Payment create(@RequestParam int buyerId,@RequestParam int productId,@RequestParam int productCount,@RequestParam String shipmentAddress, @RequestParam byte shipmentPlan){
        Shipment shipment = new Shipment(shipmentAddress, 500, shipmentPlan, "");
        Payment payment = new Payment(buyerId, productId, productCount, shipment);

        paymentTable.add(payment);
        return payment;
    }

    public JsonTable<Payment> getJsonTable(){
        return null;
    }

    @PostMapping("/{id}/submit")
    public boolean submit(int id,String receipt){
        return true;
    }

    private static boolean timekeeper(Payment payment){
//        Date timeNow = Calendar.getInstance().getTime();
//        if(payment.history.size() != 0){
//            Payment.Record lastRecord = payment.history.get(payment.history.size() - 1);
//            long timePassed = timeNow.getTime() - lastRecord.date.getTime();
//            if(lastRecord.status == Invoice.Status.WAITING_CONFIRMATION && (timePassed > WAITING_CONF_LIMIT_MS)){
//                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Gagal"));
//                return true;
//            }
//            else if((lastRecord.status == Invoice.Status.ON_PROGRESS) && (timePassed > ON_PROGRESS_LIMIT_MS)){
//                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Gagal"));
//                return true;
//            }
//            else if(lastRecord.status == Invoice.Status.ON_DELIVERY && timePassed > ON_DELIVERY_LIMIT_MS){
//                payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "TERKIRIM"));
//                return true;
//            }
//            else if(lastRecord.status == Invoice.Status.DELIVERED && timePassed > DELIVERED_LIMIT_MS){
//                payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "SELESAI"));
//                return true;
//            }
//        }
        return false;
    }
}
