package com.HanzChristianJmartMH.controller;

import com.HanzChristianJmartMH.Algorithm;
import com.HanzChristianJmartMH.dbjson.JsonTable;
import com.HanzChristianJmartMH.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Merupakan sebuah class yang akan berhubungan pada list object dan
 * memberikan paginate terhadap halaman yang ada pada object
 */

@RestController
public interface BasicGetController <T extends Serializable>{

    /**
     * Merupakan sebuah method yang digunakan untuk mengambil list object yang
     * terkaitkan pada id
     * @param id merupakan id dari objectnya
     * @return Memberikan objek yang diambil berdasarkan parameter idnya
     */

    @GetMapping("/id")
    default T getById  (int id){
        return Algorithm.<T>find(getJsonTable(),(e) -> e.id == id);
    }

    public abstract JsonTable<T> getJsonTable();

    /**
     * Merupakan sebuah method yang digunakan untuk paginate collection
     * berdasarkan parameter index dan besarnya page
     * @param page merupakan index pagenya (page keberapa)
     * @param pageSize merupakan banyaknya page yang akan ditampilkan
     */
    @GetMapping("/page")
    default List<T> getPage (int page,  int pageSize){
        return Algorithm.<T>paginate(getJsonTable(),page,pageSize,e -> true);
    }
}
