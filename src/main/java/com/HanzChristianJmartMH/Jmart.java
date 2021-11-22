package com.HanzChristianJmartMH;
import java.io.FileReader;
import java.util.*;

import com.HanzChristianJmartMH.dbjson.JsonDBEngine;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import java.io.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jmart
{

//    class Country{
//        public String name;
//        public int population;
//        public List<String> listOfStates;
//    }
    public static long DELIVERED_LIMIT_MS;
    public static long ON_DELIVERY_LIMIT_MS;
    public static long ON_PROGRESS_LIMIT_MS;
    public static long WAITING_CONF_LIMIT_MS;

    public static void main(String[] args) {
        SpringApplication.run(Jmart.class,args);
        JsonDBEngine.Run(Jmart.class);
        SpringApplication.run(Jmart.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() ->JsonDBEngine.join()));
//        try
//        {
//            // sesuaikan argument dibawah dengan lokasi resource file yang Anda unduh di EMAS!
//            JsonTable<Payment> table = new JsonTable<>(Payment.class, "D:/Perkuliahan/Semester 5/Praktikum/OOP/Modul 1/Folder khusus/jmart/src/GoldenSample/randomPaymentList.json");
//            // membuat thread untuk payment pool
//            ObjectPoolThread<Payment>paymentPool =new ObjectPoolThread<Payment>("Thread-pp", Jmart::paymentTimekeeper);
//            // menjalankan thread (ingat menggunakan start bukan run), run melakukan instruksi dalam current thread
//            paymentPool.start();
//            //tambahkan seluruh payment hasil baca ke dalam pool
//            table.forEach(payment ->paymentPool.add(payment));
//            // berikan sinyal untuk keluar dari routine apabila seluruh objek telah di proses
//            while (paymentPool.size() != 0);
//            paymentPool.exit();
//            // tunggu hingga thread selesai di eksekusi
//            while (paymentPool.isAlive());
//            // thread telah berhasil di selesaikan
//            System.out.println("Thread exited successfully");
//            // cek hasil output
//            Gson gson = new Gson();
//            table.forEach(payment -> {
//                String history = gson.toJson(payment.history);
//                System.out.println(history);
//            });
//        }
//        catch (Throwable t)
//        {
//            t.printStackTrace();
//        }

//        System.out.println("account id:" + new Account(null, null, null, -1).id);
//        System.out.println("account id:" + new Account(null, null, null, -1).id);
//        System.out.println("account id:" + new Account(null, null, null, -1).id);
//
//        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
//        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
//        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);

    /*
        try {
            List<Product>list = read("D:/Perkuliahan/Semester 5/Praktikum/OOP/Modul 1/Folder khusus/jmart/src/GoldenSample/randomProductList.json");
       //     List<Product>filtered = filterByPrice(list, 0.0, 20000.0);
       //     filtered.forEach(product -> System.out.println(product.price));
            List<Product> filteredName = filterByName(list, "GTX", 1, 5);
            filteredName.forEach(product -> System.out.println(product.name));
            List<Product> filteredId = filterByAccountId(list, 1, 0, 5);
            filteredId.forEach(product -> System.out.println(product.name));
        }
        catch (Throwable t){
            t.printStackTrace();
        }

     */
//        try{
//            String filepath = "D:/Perkuliahan/Semester 5/Praktikum/OOP/Modul 1/Folder khusus/jmart/src/GoldenSample/tes.json";
//            JsonTable<Account> tableAccount = new JsonTable<>(Account.class, filepath);
//            tableAccount.add(new Account("name", "email", "password",1000 ));
//            tableAccount.writeJson();
//
//            tableAccount = new JsonTable<>(Account.class, filepath);
//            tableAccount.forEach(account -> System.out.println(account.toString()));
//        }
//        catch(Throwable t)
//        {
//            t.printStackTrace();
//        }

//        String filepath = "D:/Perkuliahan/Semester 5/Praktikum/OOP/Modul 1/Folder khusus/jmart/src/city.json";
//        Gson gson = new Gson();
//        try
//        {
//            BufferedReader br = new BufferedReader(new FileReader(filepath));
//            Country input = gson.fromJson(br, Country.class);
//            System.out.println("name: " + input.name);
//            System.out.println("population: " + input.population);
//            System.out.println("states:");
//            input.listOfStates.forEach(state -> System.out.println(state));
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
    }

    public static boolean paymentTimekeeper(Payment payment){
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


    public static List<Product> read(String filepath) throws FileNotFoundException
    {
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(new FileReader(filepath));
        Product[] products = gson.fromJson(jsonReader, Product[].class);
        List<Product> list = new ArrayList<>();
        Collections.addAll(list, products);
        return list;
    }

//    public static List<Product> filterByCategory(List<Product> list, ProductCategory category)
//    {
//        List<Product> newList = new ArrayList<Product>();
//        for(Product product : list)
//        {
//            if(product.category == category)
//            {
//                newList.add(product);
//            }
//        }
//
//        for(Product product : list)
//        {
//            if(product.category == category)
//            {
//                newList.add(product);
//            }
//        }
//        return newList;
//    }
//
//    public static List<Product> filterByPrice(List<Product> list, double minPrice, double maxPrice)
//    {
//        List<Product> newList = new ArrayList<Product>();
//        if(minPrice != 0.0 && maxPrice != 0.0)
//        {
//            for(Product product : list)
//            {
//                double productPrice = product.price;
//                if(productPrice > minPrice && productPrice < maxPrice)
//                {
//                    newList.add(product);
//                }
//            }
//        }
//        else if(minPrice == 0.0)
//        {
//            for(Product product : list)
//            {
//                double productPrice = product.price;
//                if(productPrice < maxPrice)
//                {
//                    newList.add(product);
//                }
//            }
//        }
//        else if(maxPrice == 0.0)
//        {
//            for(Product product : list)
//            {
//                double productPrice = product.price;
//                if(productPrice > minPrice)
//                {
//                    newList.add(product);
//                }
//            }
//        }
//        return newList;
//    }

    private static List<Product> paginate(List<Product>list, int page, int pageSize, Predicate<Product> pred){
        List<Product> listed = new ArrayList<>();
        for(Product product : list)
        {
            if(pred.predicate(product))
            {
                listed.add(product);
            }
        }
        List<Product> paginatedList = new ArrayList<>();
        int startIndex = page * pageSize;
        for(int i = startIndex; i < startIndex + pageSize; i++)
        {
            paginatedList.add(listed.get(i));
        }
        return paginatedList;
    }


    public static List<Product> filterByAccountId(List<Product>list, int accountId, int page, int pageSize){
        Predicate<Product> predicate = product -> (product.accountId == accountId);
        return  paginate(list, page, pageSize, predicate);
    }

    public static List<Product> filterByName(List<Product>list, String search,int page, int pageSize){
        String searchLowercase = search.toLowerCase();
        Predicate<Product> predicate = product -> product.name.toLowerCase().contains(searchLowercase);
        return paginate(list, page, pageSize, predicate);
    }

}
/* public class Jmart
{
    public static void main(String args[]){
    }

        public static Product createProduct()
    {
        PriceTag priceTag = new PriceTag(500000,20);
        Product product = new Product("Keycaps Gundam",100,false,priceTag,ProductCategory.GADGET);
        return product;
    }

    public static Coupon createCoupun()
    {
       Coupon coupon = new Coupon("TGIF",40,Coupon.Type.DISCOUNT,20.0,10.0);
       return coupon;
    }

    public static ShipmentDuration createShipmentDuration (String args[])
    {
        return new ShipmentDuration(ShipmentDuration.INSTANT, ShipmentDuration.KARGO);
    }

} */
