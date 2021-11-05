package HanzChristianJmartMH;

public class Recognizable implements Comparable<Recognizable>{
    public final int id;
    
    protected Recognizable()
    {
        this.id = 1;
    }

    @Override
    public int compareTo(Recognizable other) {
        if(id == other.id){
            return 1;
        }
        else{
            return 0;
        }
    }

    public static <T extends Recognizable> int getClosingId (Class<T> clazz){
        return 0;
    }

    public static <T extends Recognizable> int setClosingId (Class<T> clazz,int id){
        return 0;
    }

    public boolean equals(Object other){
          if(other instanceof Recognizable)
        {
            Recognizable recon = (Recognizable) other;
            return this.id == recon.id;
        }
        return false;
    }
    
    public boolean equals(Recognizable other){
        return other.id == this.id;
    }
}
    


