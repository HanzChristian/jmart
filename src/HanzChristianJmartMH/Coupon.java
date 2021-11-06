package HanzChristianJmartMH;

public class Coupon extends Serializable
{

    public static Object newInstance(String content){
        return null;
    }
    public final String name;
    public final int code;
    public final Type type;
    public final double cut;
    public final double minimum;
    private boolean used;
    double price = 10000;
    double discount = 10;

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
    
    public boolean canApply(Treasury priceTag){
        if(priceTag.getAdjustedPrice(price,discount) >= this.minimum && used == false){
            return true;
        }
        else{
            return false;
        }
    }
    
    public double apply(Treasury priceTag){
        used = true;
        if(type == type.DISCOUNT){
            return (100-cut)/priceTag.getAdjustedPrice(price,discount)*100;
        }
        else if(type == type.REBATE){
            return (priceTag.getAdjustedPrice(price,discount)-price);
        }
        else return 0.0;
    }
    
}
