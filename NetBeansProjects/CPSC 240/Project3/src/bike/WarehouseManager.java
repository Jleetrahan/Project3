package bike;

import basicStuff.LoginAccount;

/**
 *
 * @author Jackson Trahan
 */
public class WarehouseManager extends LoginAccount {
    String firstName;
    String lastName;
    String email;
    
    public WarehouseManager(String fn, String ln, String em, String un, String pw) {
        firstName = fn;
        lastName = ln;
        email = em;
        username = un;
        password = pw;
    }
}
