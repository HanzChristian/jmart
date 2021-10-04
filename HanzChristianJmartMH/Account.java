package HanzChristianJmartMH;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Recognizable implements FileParser
{
    public int id;
    public String name;
    public String email;
    public String password;
    
    public static final String REGEX_EMAIL= "^[a-zA-Z0-9&*_~]+(\\.[a-zA-Z0-9&*_~]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.* ).{8,}$";
    
    public boolean read(String content){
        return false;
    }
    
    public Object write(){
        return null;
    }
    
    public static Object newInstance(String content){
        return null;
    }
    
    Account(int id,String name, String email, String password){
        super(id);
        this.email = email;
        this.password = password;
    }
    
    public String toString(){
        return "name: " + this.name + "\nemail: " + this.name + "\npassword: " + this.password;
    }
    
    public boolean validate(){
        Pattern validateEmail = Pattern.compile(REGEX_EMAIL);
        Pattern validatePassword = Pattern.compile(REGEX_PASSWORD);
        Matcher matchEmail = validateEmail.matcher(this.email);
        Matcher matchPassword = validatePassword.matcher(this.password);
        boolean emailFound = matchEmail.find();
        boolean passwordFound = matchPassword.find();
        
        if(emailFound == true && passwordFound == true){
            return true;
        }
        else{
            return false;
        }
    }
}
