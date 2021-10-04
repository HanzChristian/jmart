package HanzChristianJmartMH;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Complaint extends Recognizable implements FileParser
{
    public Date date;
    public String desc;
    
    public Complaint(int id, String desc){
        super(id);
        this.date = new Date();
        this.desc = desc;
    }
    
    public boolean read(String content){
        return false;
    }
    
    public String toString(){
       SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
       return "Complaint{date=" + formatDate.format(date) + ",desc='" + desc + "'}";
    }
}