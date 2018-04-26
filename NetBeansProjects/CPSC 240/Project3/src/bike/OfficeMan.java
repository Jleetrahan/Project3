package bike;

import basicStuff.LoginAccount;
import java.util.ArrayList;

/**
 *
 * @author Elizabeth Parsons, Jackson Trahan
 */
public class OfficeMan extends LoginAccount {
    String firstName; 
    String lastName;
    String email;
    
    public OfficeMan(String fn, String ln, String em, String un, String pw){
        firstName = fn;
        lastName = ln;
        email = em;
        username = un;
        password = pw;
    }
    
    public WarehousePart[] getPartsByName(String name) {
        ArrayList<WarehousePart> parts = new ArrayList();
        Warehouse wh = WarehouseFactory.getWarehouse(this);
        for (WarehousePart whp : WarehouseFactory.getWarehouse(this).whDb.getInventory()) {
            if (whp.getBp().getName().startsWith(name)) {
                parts.add(whp);
            }
        }
        return parts.toArray(new WarehousePart[] {});
    }
    
    public WarehousePart[] getPartsByNum(String num) {
        ArrayList<WarehousePart> parts = new ArrayList();
        Warehouse wh = WarehouseFactory.getWarehouse(this);
        for (WarehousePart whp : WarehouseFactory.getWarehouse(this).whDb.getInventory()) {
            if (String.valueOf(whp.getBp().getNumber()).startsWith(num)) {
                parts.add(whp);
            }
        }
        return parts.toArray(new WarehousePart[] {});
    }
}
