package HanzChristianJmartMH;

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
    
    public double getAverange(){
        return ((double)total/count);
    }
    
    public long getCount(){
        return count;
    }
    
    public long getTotal(){
        return total;
    }
  
}
