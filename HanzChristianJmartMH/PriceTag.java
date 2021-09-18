package HanzChristianJmartMH;

public class PriceTag
{
    double price;
    double discount;
    public static final double COMMISSION_MULTIPLIER = 0.05;
    public static final double BOTTOM_PRICE = 20000.0;
    public static final double BOTTOM_FEE = 1000.0;
    
    public PriceTag(double price)
    {
        this.price = price;
        this.discount = 0.0f;
    }
    
    public PriceTag(double price,double discount)
    {
        this.price = price;
        this.discount = discount;
    }
    
        private double getDiscountedPrice()
    {
        if(discount >= 100.0){
            return 100.0;
        }
        
        if(discount==100.0){
            return 0.0;
        }
        
        return(price-(price*(discount/100)));
    }
    
    public double getAdminFee()
    {
        double discountprice = getDiscountedPrice();
        if(discountprice <= BOTTOM_PRICE){
            return BOTTOM_FEE;
        }
        else{
            discountprice = discountprice - (discountprice*COMMISSION_MULTIPLIER);
            return getDiscountedPrice();
        }
    }
    
    public double getAdjustedPrice(double price)
    {
        double discountprice = getDiscountedPrice() + getAdminFee();
        return discountprice;
    }
    
    
    
}
