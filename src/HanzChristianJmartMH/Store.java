package HanzChristianJmartMH;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Store extends Recognizable
{
    public String name;
    public String address;
    public String phoneNumber;
    public Account account;
    public double balance;
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

    Store(String name, String address, String phoneNumber,double balance){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }
    
    public String toString(){
        return "name: " + this.name + "\naddress: " + this.address + "\nphoneNumber: " + this.phoneNumber;
    }
}
