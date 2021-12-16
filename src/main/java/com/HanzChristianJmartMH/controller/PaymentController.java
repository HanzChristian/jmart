package com.HanzChristianJmartMH.controller;

import com.HanzChristianJmartMH.*;
import com.HanzChristianJmartMH.dbjson.JsonAutowired;
import com.HanzChristianJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Merupakan sebuah class yang digunakan untuk melakukan modifikasi Payment
 */

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
    public static final long DELIVERED_LIMIT_MS = 10000;
    public static final long ON_DELIVERY_LIMIT_MS = 10000;
    public static final long ON_PROGRESS_LIMIT_MS = 10000;
    public static final long WAITING_CONF_LIMIT_MS = 10000;


    @JsonAutowired(value = Payment.class, filepath = "D:\\Perkuliahan\\Semester 5\\Praktikum\\OOP\\Modul 1\\Folder khusus\\jmart\\src\\GoldenSample\\payment.json")

    public static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread;

    /**
     * Merupakan pembentukan thread dengan routinenya yaitu timekeeper
     */
    static
    {
        poolThread = new ObjectPoolThread<Payment>("Thread", PaymentController::timekeeper);
        poolThread.start();
    }

    /**
     * Merupakan sebuah method yang digunakan untuk mengambil object Payment berdasarkan
     * Account Id dengan parameter id sesuai dengan account yang bersangkutan pada parameter
     * @param buyerId merupakan id account pembelinya
     * @return Memberikan list terhadap Payment yang ada pada accountId bersangkutan
     */
    @GetMapping("/getByAccountId")
    public List<Payment> getPaymentAccountid(@RequestParam int buyerId){
        List<Payment> listP = new ArrayList<>();
        for (Payment p : paymentTable){
            if(p.buyerId == buyerId){
                listP.add(p);
            }
        }
        return listP;
    }

    /**
     * Merupakan sebuah method yang digunakan untuk mengambil object Payment berdasarkan
     * Store Id dengan parameter sesuai dengan store yang bersangkutan pada parameter
     * @param storeId merupakan id store
     * @return Memberikan list terhadap payment yang bersangkutan pada store pada parameter
     */
    @GetMapping("/getByStoreId")
    public List<Payment> getPaymentStoreid(@RequestParam int storeId){
        List<Payment> listP = new ArrayList<>();
        for (Payment p : paymentTable){
            if(p.storeId == storeId){
                listP.add(p);
            }
        }
        return listP;
    }

    /**
     * Merupakan sebuah method untuk accpet terhadap Payment ketika melakukan pembelian
     * productnya
     * @param id id dari Payment
     * @return berupa boolean true ketika payment success, dan false ketika failed
     */
    @PostMapping("/{id}/accept")
    public boolean accept (@PathVariable int id){
        Payment payment = null;
        for(Payment p : paymentTable){
            if(p.id == id){
                payment = p;
            }
        }
        if(payment != null){
            int s = payment.history.size();
            Payment.Record lastRecord = payment.history.get(s - 1);
            if(lastRecord.status == Invoice.Status.WAITING_CONFIRMATION){
                Payment.Record record = new Payment.Record(Invoice.Status.ON_PROGRESS, "Payment Diterima");
                payment.history.add(record);
                poolThread.add(payment);
                return true;
            }
        }
        return false;
    }

    /**
     * Merupakan sebuah method yang digunakan untuk pembatalan Payment yang dibentuk
     * pada Account ataupun pada Store
     * @param id merupakan id Payment yang akan dibatalkan
     * @return berupa boolean true ketika success dibatalkan, dan false jika failed
     */
    @PostMapping("/{id}/cancel")
    public boolean cancel (@PathVariable int id){
        Payment payment = null;
        for(Payment p : paymentTable){
            if(p.id == id){
                payment = p;
            }
        }
        if(payment != null){
            int s = payment.history.size();
            Payment.Record lastRecord = payment.history.get(s - 1);
            if(lastRecord.status == Invoice.Status.WAITING_CONFIRMATION){
                Payment.Record record = new Payment.Record(Invoice.Status.CANCELLED, "Payment dibatalkan");
                payment.history.add(record);
                poolThread.add(payment);
                return true;
            }
        }
        return false;
    }

    /**
     * Merupakan sebuah method yang membetuk Payment berdasarkan parameter untuk pembelian
     * product oleh Account
     * @param buyerId id dari account bersangkutan
     * @param productId id dari product bersangkutan
     * @param productCount merupakan banyaknya product yang akan dibeli (quantity)
     * @param shipmentAddress merupakan address dimana barang akan dikirimkan
     * @param shipmentPlan merupakan jenis shipment yang dipilih dalam bentuk byte
     * @param storeId id dari store bersangkutan
     * @return merupakan payment yang berhasil terbentuk
     */
    @PostMapping("/create")
    public Payment create (@RequestParam int buyerId, @RequestParam int productId, @RequestParam int productCount, @RequestParam String shipmentAddress, @RequestParam byte shipmentPlan, @RequestParam int storeId){
        Account account = null;
        Product product = null;
        for(Account a : AccountController.accountTable){
            if(a.id == buyerId){
                account = a;
            }
        }

        for(Product p : ProductController.productTable){
            if(p.id == productId){
                product = p;
            }
        }
        if(account != null && product != null){
            Shipment shipment = new Shipment(shipmentAddress, 0, shipmentPlan, null);
            Payment payment = new Payment(buyerId, productId, productCount, shipment, storeId);
            double price = payment.getTotalPay(product);
            if(account.balance >= price){
                account.balance = account.balance - price;
                payment.history.add(new Payment.Record(Invoice.Status.WAITING_CONFIRMATION, "Payment sudah dibayar dan menunggu konfirmasi"));
                paymentTable.add(payment);
                poolThread.add(payment);
                return payment;
            }
        }
        return null;
    }

    public JsonTable<Payment> getJsonTable (){
        return paymentTable;
    }

    /**
     * Merupakan sebuah method untuk melakukan submit Payment yang sudah diaccept oleh Store
     * @param id id dari Payment
     * @param receipt receipt pada paymentnya
     * @return berupa boolean true ketika submit success, dan false ketika failed
     */
    @PostMapping("/{id}/submit")
    public boolean submit (@PathVariable int id, @RequestParam String receipt){
        Payment payment = null;
        for(Payment p : paymentTable){
            if(p.id == id){
                payment = p;
            }
        }
        if(payment != null){
            int s = payment.history.size();
            Payment.Record lastRecord = payment.history.get(s - 1);
            if(lastRecord.status == Invoice.Status.ON_PROGRESS && (!receipt.isBlank())){
                payment.shipment.receipt = receipt;
                Payment.Record record = new Payment.Record(Invoice.Status.ON_DELIVERY, "Payment telah diserahkan");
                payment.history.add(record);
                return true;
            }
        }
        return false;
    }

    /**
     * Merupakan sebuah method untuk cek waktu yang telah dilalui sebaai parameter pengecekkan
     * @param payment Payment yang akan dicek waktunya
     * @return berupa boolean true ketika melebihi batas waktunya, false ketika tidak ada perubahan
     */
    private static boolean timekeeper (Payment payment){
        Date timeNow = Calendar.getInstance().getTime();
        if(payment.history.size() != 0){
            Payment.Record lastRecord = payment.history.get(payment.history.size() - 1);
            long timePassed = timeNow.getTime() - lastRecord.date.getTime();
            if(lastRecord.status == Invoice.Status.WAITING_CONFIRMATION && (timePassed > WAITING_CONF_LIMIT_MS)){
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Waiting"));
                for (Account account : AccountController.accountTable){
                    if(account.id == payment.buyerId){
                        account.balance += gettotalpaymentPrice(payment);
                    }
                }
                return true;
            }
            else if((lastRecord.status == Invoice.Status.ON_PROGRESS) && (timePassed > ON_PROGRESS_LIMIT_MS)){
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Progress"));
                for (Account account : AccountController.accountTable){
                    if(account.id == payment.buyerId){
                        account.balance += gettotalpaymentPrice(payment);
                    }
                }
                return true;
            }
            else if(lastRecord.status == Invoice.Status.ON_DELIVERY && timePassed > ON_DELIVERY_LIMIT_MS){
                payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "Delivery"));
                for (Account account : AccountController.accountTable){
                    if(account.id == payment.buyerId){
                        account.balance += gettotalpaymentPrice(payment);
                    }
                }
                return true;
            }
            else if(lastRecord.status == Invoice.Status.DELIVERED && timePassed > DELIVERED_LIMIT_MS){
                payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "Finish"));
                for (Account account : AccountController.accountTable){
                    if(account.id == payment.buyerId){
                        account.balance += gettotalpaymentPrice(payment);
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static double gettotalpaymentPrice (Payment payment){
        for (Product p : ProductController.productTable){
            if(p.id == payment.productId){
                return payment.productCount * p.price * (p.discount/100);
            }
        }
        return 0.0;
    }

}
