package com.HanzChristianJmartMH.controller;

import com.HanzChristianJmartMH.*;
import com.HanzChristianJmartMH.dbjson.JsonAutowired;
import com.HanzChristianJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Merupakan sebuah class yang digunakan untuk melakukan modifikasi terhadap Product
 */

@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product> {
    @JsonAutowired(value = Product.class, filepath = "D:\\Perkuliahan\\Semester 5\\Praktikum\\OOP\\Modul 1\\Folder khusus\\jmart\\src\\GoldenSample\\store.json")
    public static JsonTable<Product> productTable;

    /**
     * Merupakan sebuah method untuk list Product yang ada pada file Jsonnya
     */
    public JsonTable<Product> getJsonTable(){
        return productTable;
    }

    /**
     * Merupakan sebuah method untuk mendapatan Product berdasarkan id
     * @param id merupakan id Product
     * @return Memberikan Product sesuai dengan idnya
     */
    @GetMapping("/{id}")
    Product getProductById(@PathVariable int id) { return getById(id); }

    /**
     * Merupakan sebuah method untuk mendapatkan list Product berdasarkan store
     * yang terdapat paginate
     * @param id merupakan id store
     * @param page merupakan index dari page
     * @param pageSize merupakan banyaknya page yang ada
     * @return berupa list dari Product berdasarkan id store dan halaman yang bersangkutan
     */
    @GetMapping("/{id}/store")
    List<Product> getProductByStore(@PathVariable int id, @RequestParam int page, @RequestParam int pageSize){
        return Algorithm.<Product>paginate(getJsonTable(), page, pageSize, product -> product.accountId == id);
    }

    /**
     * Merupakan sebuah method untuk membentuk Product berdasarkan parameter bersangkutan
     * @param accountId merupakan id dari storenya
     * @param name merupakan nama Product yang dibentuk
     * @param weight merupakan berat dari product
     * @param conditionUsed merupakan kondisi boolean antara new atau used
     * @param price merupakan harga Product
     * @param discount merupakan diskon dari Product
     * @param category merupakan jenis kategori dari Product
     * @param shipmentPlans merupakan jenis shipment dari Product
     * @return berupa pembentukan Product
     */
    @PostMapping("/create")
    Product create(@RequestParam int accountId, @RequestParam String name, @RequestParam int weight, @RequestParam boolean conditionUsed,
                   @RequestParam double price, @RequestParam double discount, @RequestParam ProductCategory category, @RequestParam byte shipmentPlans){
        for(Account account : AccountController.accountTable){
            if(account.id == accountId && account.store != null){
                Product p = new Product(accountId, discount, price, shipmentPlans, name, weight, conditionUsed, category);
                productTable.add(p);
                return p;
            }
        }
        return null;
    }

    /**
     * Merupakan sebuah method untuk memberikan list product yang sudah dilakukan filter
     * @param page merupakan index page
     * @param pageSize merupakan banyaknya halaman
     * @param accountId merupakan id dari store
     * @param search merupakan search terhadap Product
     * @param minPrice merupakan filter minimal harga yang ditetapkan
     * @param maxPrice merupakan filter maksimal harga yang ditetapkan
     * @param category merupakan jenis kategori yang difilter
     * @return membentuk list dari Product yang sudah difilter
     */
    @GetMapping("/getFiltered")
    List<Product> getProductFiltered(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "0") int accountId, @RequestParam(defaultValue = "") String search,
                                     @RequestParam(defaultValue = "0") int minPrice, @RequestParam(defaultValue = "0") int maxPrice, @RequestParam(defaultValue = "") ProductCategory category){
        String searchLowercase = search.toLowerCase();
        Predicate<Product> predicateSearch = product -> product.name.toLowerCase().contains(searchLowercase);
        return paginateProductListFilteredAll(page, pageSize, predicateSearch, minPrice, maxPrice, category);
    }

    /**
     * Merupakan sebuah method untuk memberikan list Product berdasarkan filter yang memenuhi parameter
     * @param page index pagenya
     * @param pageSize banyaknya page
     * @param predSearch Melakukan pengecekkan string searchnya
     * @param minPrice Minimal harga yang ditetapkan
     * @param maxPrice Maksimal harga yang ditetapkan
     * @param category merupakan jenis kategori
     * @return list Product yang sesuai dengan filter
     */

    public List<Product> paginateProductListFilteredAll(int page, int pageSize, Predicate<Product> predSearch, int minPrice, int maxPrice, ProductCategory category){
        List<Product> newList = new ArrayList<Product>();
        if(minPrice != 0.0 && maxPrice != 0.0)
        {
            for(Product p : getJsonTable())
            {
                double productPrice = p.price;
                if(productPrice > minPrice && productPrice < maxPrice && predSearch.predicate(p) && p.category == category)
                {
                    newList.add(p);
                }
            }
        }
        else if(minPrice == 0.0 && maxPrice != 0.0)
        {
            for(Product p : getJsonTable())
            {
                double productPrice = p.price;
                if(productPrice < maxPrice && predSearch.predicate(p) && p.category == category)
                {
                    newList.add(p);
                }
            }
        }
        else if(maxPrice == 0.0 && minPrice != 0.0)
        {
            for(Product p : getJsonTable())
            {
                double productPrice = p.price;
                if(productPrice > minPrice && predSearch.predicate(p) && p.category == category)
                {
                    newList.add(p);
                }
            }
        }
        else {
            for(Product p : getJsonTable())
            {
                double productPrice = p.price;
                if(predSearch.predicate(p) && p.category == category)
                {
                    newList.add(p);
                }
            }
        }
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;
        List<Product> paginatedList;
        if(endIndex > newList.size())
        {
            paginatedList = newList.subList(startIndex, newList.size());
        }
        else
        {
            paginatedList = newList.subList(startIndex, endIndex);
        }
        return paginatedList;
    }
}