package HanzChristianJmartMH;

public class Store extends Recognizable implements FileParser
{
    public boolean read(String content){
        return false;
    }
    
    public Object write(){
        return null;
    }
    
    public static Object newInstance(String content){
        return null;
    }
    
    public String name;
    public String address;
    public String phoneNumber;
    public Account account;
    
    Store(int accountId, String name, String address, String phoneNumber){
        super(accountId);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    Store(Account account, String name, String address, String phoneNumber){
        super(account.id);
        this.account = account;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
