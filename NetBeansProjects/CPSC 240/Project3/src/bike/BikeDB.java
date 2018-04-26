
package bike;

import basicStuff.LoginAccount;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Elizabeth Parsons, Jackson Trahan
 */
public class BikeDB {
    static BikeDB bikeDB = null;
    WarehouseFactory whf;
    ArrayList<LoginAccount> users;
    LoginAccount currentUser;
    
    private BikeDB() {
        users = new ArrayList();
        Scanner in = null;
        try {
            in = new Scanner(new File("users.txt"));
        } catch (Exception e) {e.printStackTrace();}
        
        while (in.hasNextLine()) {
            String[] signIn = in.nextLine().split(", ");
            LoginAccount account = null;
            Warehouse wh = null;
            switch (signIn[0].toLowerCase()) {
                case "office manager":
                    account = new OfficeMan(signIn[1], signIn[2], signIn[3], signIn[4], signIn[5]);
                    break;
                case "warehouse manager":
                    account = new WarehouseManager(signIn[1], signIn[2], signIn[3], signIn[4], signIn[5]);
                    break;
                case "sales associate":
                    account = new SalesAssociate(signIn[1], signIn[2], signIn[3], signIn[4], signIn[5]);
                    break;
                case "system admin":
                    account = new SysAdmin(signIn[1], signIn[2], signIn[3], signIn[4], signIn[5]);
                    break;
            }
            users.add(account);
        }
    }
    
    public static BikeDB getDB() {
        if (bikeDB == null)
            bikeDB = new BikeDB();
        return bikeDB;
    }
    
    public void switchUser(LoginAccount account) {
        currentUser = account;
    }
    
    public LoginAccount getUser() {
        return currentUser;
    }
    
    public ArrayList<LoginAccount> getUsers() {
        return users;
    }
}
