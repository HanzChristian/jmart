package HanzChristianJmartMH;

public class Complaint extends Recognizable implements FileParser
{
    public String date;
    public String desc;
    
    public Complaint(int id, String desc){
        super(id);
        this.date = "ilhumers";
        this.desc = desc;
    }
    
    public boolean read(String content){
        return false;
    }
}

