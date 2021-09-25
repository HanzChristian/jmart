package HanzChristianJmartMH;

public class Coupon extends Recognizable implements FileParser
{
    public boolean read(String content){
        return false;
    }
    
    public Object write(){
        return null;
    }
    
    public static Object newInstance(String content){
        return null;
    }
    public final String name;
    public final int code;
    public final Type type;
    public final double cut;
    public final double minimum;
    private boolean used;
    
    public enum Type{
    DISCOUNT, REBATE
    }    

    public Coupon(int id,String name, int code, Type type, double cut, double minimum){
        super(id);
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
        else if(type == type.REBATE){
            return (priceTag.getAdjustedPrice()-priceTag.price);
        }
        else return 0.0;
    }
    
}
