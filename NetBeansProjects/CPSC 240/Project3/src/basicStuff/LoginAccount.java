package basicStuff;

/**
 *
 * @author Elizabeth Parsons, Jackson Trahan
 */
public class LoginAccount {
    protected Person person;
    protected String username;
    protected String password;
    protected String email;
    
    public boolean validate(String un, String pw) {
        if (un.equals(username) && pw.equals(password)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public String getFirstName() {return person.getName();}
    
    public String getLastName() {return person.getLastName();}
    
    public String getEmail() {return email;}
    
    public String getUsername() {return username;}
    
    public String getPassword() {return password;}
    
    @Override
    public String toString() {return username;}

    public void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
