package HanzChristianJmartMH;

public class Account extends Recognizable implements FileParser
{
    public int id;
    public String name;
    public String email;
    public String password;
    
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
}
