package HanzChristianJmartMH;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.google.gson.*;

public class Jmart
{
    class Country{
        public String name;
        public int population;
        public List<String> listOfStates;
    }
    public static void main(String args[])
    {
        String filepath = "D:/Perkuliahan/Semester 5/Praktikum/OOP/Modul 1/Folder khusus/jmart/src/city.json";
        Gson gson = new Gson();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            Country input = gson.fromJson(br, Country.class);
            System.out.println("name: " + input.name);
            System.out.println("population: " + input.population);
            System.out.println("states:");
            input.listOfStates.forEach(state -> System.out.println(state));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
/* public class Jmart
{
    public static void main(String args[]){
    }
    
        public static Product createProduct()
    {
        PriceTag priceTag = new PriceTag(500000,20);
        Product product = new Product("Keycaps Gundam",100,false,priceTag,ProductCategory.GADGET);
        return product;
    }

    public static Coupon createCoupun()
    {
       Coupon coupon = new Coupon("TGIF",40,Coupon.Type.DISCOUNT,20.0,10.0);
       return coupon;
    }

    public static ShipmentDuration createShipmentDuration (String args[])
    {
        return new ShipmentDuration(ShipmentDuration.INSTANT, ShipmentDuration.KARGO);
    }
    
} */
