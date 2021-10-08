package HanzChristianJmartMH;

public class Product extends Recognizable implements FileParser
{
    public boolean read(String content){
        return false;
    }

    private static int idCounter = 0;
    public String name;
    public int weight;
    public boolean conditionUsed;
    public PriceTag priceTag;
    public ProductCategory category;
    public ProductRating rating;
    public int storeId;
    
    public Store store;
    public Shipment.MultiDuration multiDuration;
    
    public Product(int id, int storeId, String name, int weight, boolean conditionUsed, PriceTag priceTag, ProductCategory category,Shipment.MultiDuration multiDuration){
        super(id);
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category = category;
        this.rating = rating;
        this.storeId = storeId;
        this.multiDuration = multiDuration;
    }
    
    public String toString(){
        return "Name: " + this.name + "\nWeight: " + this.weight +"\nconditionUsed: " + this.conditionUsed +"\npriceTag: " + this.priceTag +"\ncategory: " + this.category +"\nrating: " + this.rating +"\nstoreId: " + this.storeId;
    }
}
