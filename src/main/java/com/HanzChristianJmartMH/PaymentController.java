package com.HanzChristianJmartMH;

import com.HanzChristianJmartMH.Invoice;
import com.HanzChristianJmartMH.ObjectPoolThread;
import com.HanzChristianJmartMH.Payment;
import com.HanzChristianJmartMH.Shipment;
import com.HanzChristianJmartMH.controller.AccountController;
import com.HanzChristianJmartMH.controller.BasicGetController;
import com.HanzChristianJmartMH.controller.ProductController;
import com.HanzChristianJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PathVariable;
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
        Payment payment = null;
        for(Payment pay : paymentTable){
            if(pay.id == id){
                payment = pay;
            }
        }
        if(payment != null){
            int size = payment.history.size();
            Payment.Record lastRecord = payment.history.get(size - 1);
            if(lastRecord.status == Invoice.Status.WAITING_CONFIRMATION){
                Payment.Record record = new Payment.Record(Invoice.Status.ON_PROGRESS, "Payment accepted, success!");
                payment.history.add(record);
                return true;
            }
        }
        return false;
    }

    @PostMapping("/{id}/cancel")
    public boolean cancel(int id){
        Payment payment = null;
        for(Payment pay : paymentTable){
            if(pay.id == id){
                payment = pay;
            }
        }
        if(payment != null){
            int size = payment.history.size();
            Payment.Record lastRecord = payment.history.get(size - 1);
            if(lastRecord.status == Invoice.Status.WAITING_CONFIRMATION){
                Payment.Record record = new Payment.Record(Invoice.Status.CANCELLED, "Payment cancelled, failed!");
                payment.history.add(record);
                return true;
            }
        }
        return false;
    }

    @PostMapping("/create")
    public Payment create(@RequestParam int buyerId, @RequestParam int productId, @RequestParam int productCount, @RequestParam String shipmentAddress, @RequestParam byte shipmentPlan){
        Account account = null;
        Product product = null;
        for(Account acc : AccountController.accountTable){
            if(acc.id == buyerId){
                account = acc;
            }
        }

        for(Product prod : ProductController.productTable){
            if(prod.id == productId){
                product = prod;
            }
        }
        if(account != null && product != null){
            Shipment shipment = new Shipment(shipmentAddress, 0, shipmentPlan, null);
            Payment payment = new Payment(buyerId, productId, productCount, shipment);
            double price = payment.getTotalPay(product);
            if(account.balance >= price){
                account.balance = account.balance - price;
                payment.history.add(new Payment.Record(Invoice.Status.WAITING_CONFIRMATION, "Paid & Waiting for confirmation!"));
                paymentTable.add(payment);
                poolThread.add(payment);
                return payment;
            }
        }
        return null;
    }

    public JsonTable<Payment> getJsonTable(){
        return paymentTable;
    }

    @PostMapping("/{id}/submit")
    public boolean submit(@PathVariable int id, @RequestParam String receipt){
        Payment payment = null;
        for(Payment pay : paymentTable){
            if(pay.id == id){
                payment = pay;
            }
        }
        if(payment != null){
            int size = payment.history.size();
            Payment.Record lastRecord = payment.history.get(size - 1);
            if(lastRecord.status == Invoice.Status.ON_PROGRESS && (!receipt.isBlank())){
                payment.shipment.receipt = receipt;
                Payment.Record record = new Payment.Record(Invoice.Status.ON_DELIVERY, "Payment Submitted!");
                payment.history.add(record);
                return true;
            }
        }
        return false;
    }

    private static boolean timekeeper(Payment payment){
        Date timeNow = Calendar.getInstance().getTime();
        if(payment.history.size() != 0){
            Payment.Record lastRecord = payment.history.get(payment.history.size() - 1);
            long timePassed = timeNow.getTime() - lastRecord.date.getTime();
            if(lastRecord.status == Invoice.Status.WAITING_CONFIRMATION && (timePassed > WAITING_CONF_LIMIT_MS)){
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Gagal"));
                return true;
            }
            else if((lastRecord.status == Invoice.Status.ON_PROGRESS) && (timePassed > ON_PROGRESS_LIMIT_MS)){
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Gagal"));
                return true;
            }
            else if(lastRecord.status == Invoice.Status.ON_DELIVERY && timePassed > ON_DELIVERY_LIMIT_MS){
                payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "TERKIRIM"));
                return true;
            }
            else if(lastRecord.status == Invoice.Status.DELIVERED && timePassed > DELIVERED_LIMIT_MS){
                payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "SELESAI"));
                return true;
            }
        }
        return false;
    }
}
