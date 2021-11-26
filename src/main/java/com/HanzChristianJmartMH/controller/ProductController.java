package com.HanzChristianJmartMH.controller;

import com.HanzChristianJmartMH.*;
import com.HanzChristianJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public class ProductController implements BasicGetController<Product>{
    public static JsonTable<Product> productTable;

    @PostMapping("/product/create")
    Product create(@RequestParam int accountId, @RequestParam String name, @RequestParam int weight, @RequestParam boolean conditionUsed, @RequestParam double price, @RequestParam double discount, @RequestParam ProductCategory category, @RequestParam byte shipmentPlans){
        for(Account acc : AccountController.accountTable){
            if (acc.store == null || acc.id != accountId) {
            } else {
                Product newProduct = new Product(accountId, discount, price, shipmentPlans, name, weight, conditionUsed, category);
                productTable.add(newProduct);
                return newProduct;
            }
        }
        return null;
    }

    @PostMapping("/product")
    public JsonTable<Product> getJsonTable(){
        return productTable;
    }

    @PostMapping("/product/{id}/store")
    List<Product> getProductByStore(int id, int page, int pageSize){
        return Algorithm.paginate(productTable, page, pageSize, pred->pred.accountId == id);
    }

    @GetMapping("/getFiltered")
    List<Product> getProductFiltered(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "0") int accountId, @RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "0.0") int minPrice, @RequestParam(defaultValue = "0.0") int maxPrice, @RequestParam(defaultValue = "") ProductCategory category){
        List<Product> listed = new ArrayList<>();
        if(accountId != 0){
            listed = Jmart.filterByAccountId(listed, accountId, page, pageSize);
        }
        if(!search.equals("")){
            listed = Jmart.filterByName(listed, search, page, pageSize);
        }
        if(minPrice != 0.0 || maxPrice != 0.0){
            listed = Jmart.filterByPrice(listed, minPrice, maxPrice);
        }
        if(category != null){
            listed = Jmart.filterByCategory(listed, category);
        }
        return listed;
    }

}
