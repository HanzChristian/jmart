package HanzChristianJmartMH;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Complaint extends Recognizable
{
    public Date date;
    public String desc;
    
    public Complaint(String desc){
        this.date = new Date();
        this.desc = desc;
    }
    
    public String toString(){
       SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
       return "Complaint{date=" + formatDate.format(date) + ",desc='" + desc + "'}";
    }
}
