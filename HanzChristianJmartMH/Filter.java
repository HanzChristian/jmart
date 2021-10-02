package HanzChristianJmartMH;
import java.util.ArrayList;

public class Filter
{
    public static ArrayList<PriceTag> filterPriceTag(PriceTag[]list, 
    double value, boolean less){
        ArrayList<PriceTag> priceTags = new ArrayList<>();
        for (PriceTag x: list){
            if (less && x.getAdjustedPrice() < value || !less){
                if(x.getAdjustedPrice() >= value){
                    priceTags.add(x);
            }
        }
        }
        return priceTags;
    }
    
    public static void filterProductRating(ArrayList<ProductRating> list, 
    double value, boolean less){
        for (int i=0; i<list.size(); ++i){
            final ProductRating x = list.get(i);
            if (less && x.getAverage()<value || !less && x.getAverage()>= value){
                list.remove(i);
            }
        }
    }
}
