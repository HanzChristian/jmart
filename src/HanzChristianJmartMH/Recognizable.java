package HanzChristianJmartMH;

public class Recognizable
{
    public final int id;
    
    protected Recognizable(int id)
    {
        this.id = id;
    }
    
    public boolean equals(Object object){
          if(object instanceof Recognizable)
        {
            Recognizable recon = (Recognizable) object;
            if(this.id == recon.id){
                return true;
            }
        }
        return false;
    }
    
    public boolean equals(Recognizable recognizable){
        if(recognizable.id == this.id){
            return true;
        }
        else{
            return false;
        }
    }
}
    


