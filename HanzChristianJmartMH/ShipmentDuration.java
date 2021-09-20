package HanzChristianJmartMH;

public class ShipmentDuration
{
    private final int bit;
    
    public static ShipmentDuration INSTANT = new ShipmentDuration(1<<0);
    public static ShipmentDuration SAME_DAY = new ShipmentDuration(1 << 1);
    public static ShipmentDuration NEXT_DAY = new ShipmentDuration(1 << 2);
    public static ShipmentDuration REGULER = new ShipmentDuration(1 << 3);
    public static ShipmentDuration KARGO = new ShipmentDuration(1 << 4);
    
    private ShipmentDuration(int bit){
        this.bit = bit;
    }
    
    public ShipmentDuration(ShipmentDuration... args){
         int temporary = args[0].bit;
         int i;
         for(i = 1;i<args.length;i++){
             temporary = temporary | args[i].bit;
         }
         this.bit = temporary;
        }
    
    public boolean isDuration(ShipmentDuration reference){
        return (this.bit & reference.bit) == reference.bit;
    }
}
