package HanzChristianJmartMH;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static final String REGEX_NAME = "^[A-Z](?!.*(\\s)\1).{4,20}$";
    public static final String REGEX_PHONE = "^\\d{9,12}$";
    
    public boolean validate(){
        Pattern validateName = Pattern.compile(REGEX_NAME);
        Pattern validatePhone = Pattern.compile(REGEX_PHONE);
        Matcher matchName = validateName.matcher(this.name);
        Matcher matchPhone = validatePhone.matcher(this.phoneNumber);
        boolean nameFound = matchName.find();
        boolean phoneFound = matchPhone.find();
        
        if(nameFound == true && phoneFound == true){
            return true;
        }
        else{
            return false;
        }
    }
    
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
    
    public String toString(){
        return "name: " + this.name + "\naddress: " + this.address + "\nphoneNumber: " + this.phoneNumber;
    }
}
