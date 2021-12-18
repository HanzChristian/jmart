package com.HanzChristianJmartMH;

/**
 * Merupakan Class ProductRating
 * @author Hanz Christian
 * @version 18 Desember 2021
 */
public class ProductRating
{
    private long total;
    private long count;


    public ProductRating(){
        this.total=0;
        this.count=0;
    }
    
    public void insert(int rating){
        this.total += rating;
        this.total++;
    }

    /**
     * Merupakan method untuk mendapatkan rata-rata dari rating tiap product
     * @return merupakan perhitungan rata-rata
     */
    public double getAverage(){
        return ((double)total/count);
    }

    /**
     * Merupakan method untuk mendapatkan jumlah ratingnya
     * @return jumlah rating
     */
    public long getCount(){
        return count;
    }

    /**
     * Merupakan method untuk mendapatkan total rating
     * @return total rating
     */
    public long getTotal(){
        return total;
    }
  
}
