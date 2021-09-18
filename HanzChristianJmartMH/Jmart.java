package HanzChristianJmartMH;

public class Jmart
{
    public static void main(String args[]){
    }
    
    public static Product create(){
        PriceTag priceTag = new PriceTag(500000);
        Product product = new Product("Keycaps Gundam",100,false,priceTag,ProductCategory.GADGET);
        return product;
    }
    
    
}
