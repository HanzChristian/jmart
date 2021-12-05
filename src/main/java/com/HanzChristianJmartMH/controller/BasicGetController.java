package com.HanzChristianJmartMH.controller;

import com.HanzChristianJmartMH.Algorithm;
import com.HanzChristianJmartMH.dbjson.JsonTable;
import com.HanzChristianJmartMH.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface BasicGetController <T extends Serializable>{
    @GetMapping("/id")
    default T getById  (int id){
        return Algorithm.<T>find(getJsonTable(),(e) -> e.id == id);
    }

    public abstract JsonTable<T> getJsonTable();

    @GetMapping("/page")
    default List<T> getPage (int page,  int pageSize){
        return Algorithm.<T>paginate(getJsonTable(),page,pageSize,e -> true);
    }
}
