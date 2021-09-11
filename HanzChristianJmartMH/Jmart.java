package HanzChristianJmartMH;

public class Jmart
{
    public static void main(String args[]){
        System.out.println("getPromo : " + getPromo());
        System.out.println("getCustomer : " + getCustomer());
        System.out.println("getDiscountPercentage : " + getDiscountPercentage(1000,900));
        System.out.println("getDiscountedPrice : " + getDiscountedPrice(1000,100.0f));
        System.out.println("getOriginalPrice : " + getOriginalPrice(900,10.0f));
        System.out.println("getComissionMultiplier : " + getComissionMultiplier());
        System.out.println("getAdjustedPrice : " + getAdjustedPrice(1000));
        System.out.println("getAdminFee : " + getAdminFee(1000));
    }
    
    public static int getPromo(){
        return 0;
    }
    
    public static String getCustomer(){
        return "oop";
    }
    
    public static float getDiscountPercentage(int before, int after){
        if(before<after)
        {
            return 0.0f;
        }
        else
        {
            int selisih = before-after;
            float percentage = (selisih/before)*100;
            return percentage;
        }
    }
    
    public static int getDiscountedPrice(int price, float discountPercentage){
        if(discountPercentage > 100.0f)
        {
            return 0;
        }
        else{
            float harga = price;
            float discount = price - ((discountPercentage*harga)/100);
            int hargaakhir = (int) discount;
            return hargaakhir;
        }
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage){
        float discprice = discountedPrice;
        float hargaoriginal = (1/(1-(discountPercentage))) * discprice;
        int originalint = (int) hargaoriginal;
        return originalint;
    }
    
    public static float getComissionMultiplier(){
        return 0.05f;
    }
    
    public static int getAdjustedPrice(int price){
        float besarcomission = getComissionMultiplier();
        float adjustprice = price + (besarcomission*price);
        int adjustpriceint = (int) adjustprice;
        return adjustpriceint;
    }
    
    public static int getAdminFee(int price){
        float floatprice = price;
        float finalfee = floatprice*getComissionMultiplier();
        int adminfee = (int)finalfee;
        return adminfee;
    }
    
}
