package HanzChristianJmartMH;

public class Coupon
{
    public final String name;
    public final int code;
    public final Type type;
    public final double cut;
    public final double minimum;
    private boolean used;
    
    public enum Type{
    DISCOUNT, REBATE
    }    

    public Coupon(String name, int code, Type type, double cut, double minimum){
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
        this.used = false;
    }
    
    public boolean isUsed(){
        return used;
    }
    
    public boolean canApply(PriceTag priceTag){
        if(priceTag.getAdjustedPrice() >= this.minimum && used == false){
            return true;
        }
        else{
            return false;
        }
    }
    
    public double apply(PriceTag priceTag){
        used = true;
        if(type == type.DISCOUNT){
            return (100-cut)/priceTag.getAdjustedPrice()*100;
        }
        else{
            return (priceTag.getAdjustedPrice()-priceTag.price);
        }
    }
    
    
}
