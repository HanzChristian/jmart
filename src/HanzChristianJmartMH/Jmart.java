package HanzChristianJmartMH;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Jmart
{
    class Country{
        public String name;
        public int population;
        public List<String> listOfStates;
    }
    public static void main(String[] args) {
        System.out.println("account id:" + new Account(null, null, null, -1).id);
        System.out.println("account id:" + new Account(null, null, null, -1).id);
        System.out.println("account id:" + new Account(null, null, null, -1).id);

        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);
        System.out.println("payment id:" + new Payment(-1, -1, -1, null).id);

        try {
            List<Product>list = read("D:/Perkuliahan/Semester 5/Praktikum/OOP/Modul 1/Folder khusus/jmart/src/GoldenSample/randomProductList.json");
            List<Product>filtered = filterByPrice(list, 0.0, 20000.0);
            filtered.forEach(product -> System.out.println(product.price));
        }
        catch (Throwable t){
            t.printStackTrace();
        }
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


    public static List<Product> read(String filepath) throws FileNotFoundException
    {
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(new FileReader(filepath));
        Product[] products = gson.fromJson(jsonReader, Product[].class);
        List<Product> list = new ArrayList<>();
        Collections.addAll(list, products);
        return list;
    }

    public static List<Product> filterByCategory(List<Product> list, ProductCategory category)
    {
        List<Product> newList = new ArrayList<Product>();
        for(Product product : list)
        {
            if(product.category == category)
            {
                newList.add(product);
            }
        }

        for(Product product : list)
        {
            if(product.category == category)
            {
                newList.add(product);
            }
        }
        return newList;
    }

    public static List<Product> filterByPrice(List<Product> list, double minPrice, double maxPrice)
    {
        List<Product> newList = new ArrayList<Product>();
        if(minPrice != 0.0 && maxPrice != 0.0)
        {
            for(Product product : list)
            {
                double productPrice = product.price;
                if(productPrice > minPrice && productPrice < maxPrice)
                {
                    newList.add(product);
                }
            }
        }
        else if(minPrice == 0.0)
        {
            for(Product product : list)
            {
                double productPrice = product.price;
                if(productPrice < maxPrice)
                {
                    newList.add(product);
                }
            }
        }
        else if(maxPrice == 0.0)
        {
            for(Product product : list)
            {
                double productPrice = product.price;
                if(productPrice > minPrice)
                {
                    newList.add(product);
                }
            }
        }
        return newList;
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
