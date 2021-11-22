package com.HanzChristianJmartMH.controller;

import com.HanzChristianJmartMH.Account;
import com.HanzChristianJmartMH.Algorithm;
import com.HanzChristianJmartMH.Product;
import com.HanzChristianJmartMH.ProductCategory;
import com.HanzChristianJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class ProductController implements BasicGetController<Product>{
    public static JsonTable<Product> productTable;

    @PostMapping("/product/create")
    Product create(@RequestParam int accountId, @RequestParam String name, @RequestParam int weight, @RequestParam boolean conditionUsed, @RequestParam double price, @RequestParam double discount, @RequestParam ProductCategory category, @RequestParam byte shipmentPlans){
        for(Account acc : AccountController.accountTable){
            if (acc.store == null || acc.id != accountId) {
            } else {
                Product newProduct = new Product(accountId, discount, price, shipmentPlans, name, weight, conditionUsed, category));
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

    @PostMapping("/product/getFiltered")
    List<Product> getProductByFiltered(@RequestParam int page, @RequestParam int pageSize, @RequestParam int accountId, @RequestParam String search, @RequestParam int minPrice, @RequestParam int maxPrice, @RequestParam ProductCategory category){
//        List<Product> tempProduct = null;
//        for (Product each : productTable) {
//            if (each.accountId == accountId)
//                if (each.name.contains(search))
//                    if (minPrice <= each.price)
//                        if (maxPrice >= each.price)
//                            if (each.category == category)
//                                tempProduct.add(each);
//        }
//        return tempProduct;
    }

}
