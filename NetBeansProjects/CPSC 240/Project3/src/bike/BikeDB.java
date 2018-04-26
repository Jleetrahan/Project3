
package bike;

import basicStuff.LoginAccount;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
        users.add(new SysAdmin(null, null, null, "Admin", "mindA"));
        Scanner in = null;
        try {
            in = new Scanner(new File("users.txt"));
        } catch (Exception e) {e.printStackTrace();}
        
        while (in.hasNextLine()) {
            String[] signIn = in.nextLine().split(", ");
            LoginAccount account = null;
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
            if (account != null)
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

    public void addUser(LoginAccount la) throws Exception {
        users.add(la);
        FileWriter w = new FileWriter(new File("users.txt"), true);
        if (OfficeMan.class.isInstance(la)) {
            w.append("Office Manager, " + la.getFirstName() + ", " + la.getLastName() + ", " + la.getEmail() + ", " + la.getUsername() + ", " + la.getPassword() + "\n");
        } else if (WarehouseManager.class.isInstance(la)) {
            w.append("Warehouse Manager, " + la.getFirstName() + ", " + la.getLastName() + ", " + la.getEmail() + ", " + la.getUsername() + ", " + la.getPassword() + "\n");
        } else if (SalesAssociate.class.isInstance(la)) {
            w.append("Sales Associate, " + la.getFirstName() + ", " + la.getLastName() + ", " + la.getEmail() + ", " + la.getUsername() + ", " + la.getPassword() + "\n");
        } else if (SysAdmin.class.isInstance(la)) {
            w.append("System Admin, " + la.getFirstName() + ", " + la.getLastName() + ", " + la.getEmail() + ", " + la.getUsername() + ", " + la.getPassword() + "\n");
        }
        w.close();
    }
    
    public void removeUser(LoginAccount la) throws Exception {
        users.remove(la);
        
        File inputFile = new File("users.txt");
        File tempFile = new File("myTempFile.txt");

        Scanner reader = new Scanner(inputFile);
        FileWriter writer = new FileWriter(tempFile);

        while(reader.hasNextLine()) {
            String currentLine = reader.nextLine();
            String[] data = currentLine.split(", ");
            if (data[4].equals(la.getUsername())) continue;
            writer.write(currentLine);
        }
        writer.close();
        boolean successful = tempFile.renameTo(inputFile);
    }
}
