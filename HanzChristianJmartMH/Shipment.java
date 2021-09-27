package HanzChristianJmartMH;


public class Shipment implements FileParser {
    public String address;
    public int shipmentCost;
    public Duration duration;
    public String receipt;

    static class Duration
    {
        public static final Duration INSTANT = new Duration((byte) 1);
        public static final Duration SAME_DAY = new Duration((byte) (1<<1));
        public static final Duration NEXT_DAY = new Duration((byte) (1<<2));
        public static final Duration REGULER = new Duration((byte) (1<<3));
        public static final Duration KARGO = new Duration((byte) (1<<4));

        private final byte bit;

        private Duration(byte bit){
            this.bit = bit;
        }
    }

    static class MultiDuration {
        public byte bit;

        public MultiDuration(Duration... args){
            byte courier = args[0].bit;

            for(int i=1; i<args.length; i++){
                courier = (byte) (courier|args[i].bit);
            }

            bit = courier;
        }

        public boolean isDuration(Duration reference){
            return ( (this.bit & reference.bit) == reference.bit );
        }
    }

    public Shipment(String address, int shipmentCost, Duration duration, String receipt) {
        this.address = address;
        this.shipmentCost = shipmentCost;
        this.duration = duration;
        this.receipt = receipt;
    }

    public boolean read(String content) {
        return false;
    }
}
    
