package basicStuff;

/**
 *
 * @author Elizabeth Parsons, Jackson Trahan
 */
public class LoginAccount {
    public String username;
    public String password;
    
    public boolean validate(String un, String pw) {
        if (un.equals(username) && pw.equals(password)){
            return true;
        }
        else{
            return false;
        }
    }
}
