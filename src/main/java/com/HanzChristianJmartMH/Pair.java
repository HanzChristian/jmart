package com.HanzChristianJmartMH;

/**
 * Merupakan Class Pair
 * @author Hanz Christian
 * @version 18 Desember 2021
 */
public class Pair <T,U>{

    public T first;
    public U second;

    /**
     * Merupakan kumpulan getter dan setter untuk class pair yang mereturn first dan second
     */
    public Pair(){
        this.first = null;
        this.second = null;
    }

    /**
     * Merupakan kumpulan getter dan setter untuk class pair yang mereturn first dan second
     */
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

}
