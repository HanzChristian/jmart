package HanzChristianJmartMH;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;

public abstract class Invoice extends Recognizable
{
    public Date date;
    public int productId;
    public int complaintID;
    public Rating rating;
    public Status status;
    public int buyerId;

    enum Rating{
        NONE, BAD, NEUTRAL, GOOD;
    }
    enum Status{
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED,FAILED;
    }
    
    public ArrayList<Record> history = new ArrayList<Record>();
    class Record{
        public Date date;
        public String message;
        public Status status;
    }
    
    
    protected Invoice(int id, int buyerId, int productId){
        this.date = new Date();
        this.buyerId = buyerId;
        this.productId = productId;
        this.rating = Rating.NONE;
        this.status = Status.WAITING_CONFIRMATION;
    }

    
    public abstract double getTotalPay();
}
