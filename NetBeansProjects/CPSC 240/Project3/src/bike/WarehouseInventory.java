package bike;

import basicStuff.BikePart;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Elizabeth Parsons
 */
public class WarehouseInventory implements Iterable {
    private List<WarehousePart> warehouseDB;
    
    public WarehouseInventory() {
        warehouseDB = new ArrayList<WarehousePart>();
    }
    
    private WarehousePart findInventory(BikePart bp){
        for(WarehousePart i : warehouseDB){
            if (i.getBp().equals(bp))
                return i;
            
        }
        return null;
    }
    
    private void updateInventory(WarehousePart i, BikePart b, int quantity){
        i.getBp().setPrice(b.getPrice());
        i.getBp().setSalesPrice(b.getSalesPrice());
        i.getBp().setOnSale(b.getOnSale());
        i.setCount(i.getCount() + quantity);
    }
    
    public void addInventory(BikePart bp, int quantity){
        WarehousePart i = findInventory(bp);
        if (i != null)
            updateInventory(i, bp, quantity);
        else
            warehouseDB.add(new WarehousePart(bp, quantity));
    }
    public void updateWareHouseDB(String fileName) throws FileNotFoundException{
        File file = new File(fileName);
        Scanner read = new Scanner(file);
        while (read.hasNextLine()) {
            String line = read.nextLine();
            String regExp = "\\s*(\\s|,)\\s*";
            String[] bci = line.split(regExp);
            BikePart bc;
            bc = new BikePart(bci[0],Integer.parseInt(bci[1]),Double.parseDouble(bci[2]),Double.parseDouble(bci[3]), bci[4].equals("true"), Integer.parseInt(bci[5]));
            int quantity = Integer.parseInt(bci[5]);
            addInventory(bc, quantity);

        }
    }
    
    public WarehousePart sellParts(int partNum){
        WarehousePart f = null;
        for(WarehousePart i: warehouseDB){
            if(i.getBp().getNumber() == partNum){
                f = i;
                break;
            }
        }
        if (f != null)
            updateInventory(f,f.getBp(), -1);
        return f;
    }
    public WarehousePart findPartByName(String partName){
        for(WarehousePart i : warehouseDB){
            if(i.getBp().getName().equals(partName))
                return i;
            }
        return null;
        }
    public List<WarehousePart> getInventory(){
        return warehouseDB;
    }
    public void saveWarehouse(String filename){
        try{
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            for(WarehousePart i : warehouseDB){
                writer.println(i.getBp().getName() + ","
                + i.getBp().getNumber() + ","
                + i.getBp().getPrice() + ","
                + i.getBp().getSalesPrice() + ","
                + i.getBp().getOnSale() + ","
                + i.getCount());
                writer.close();
            }
        }catch(IOException e){
            System.out.println("file error!");
            e.printStackTrace();
            
            }
        }

    @Override
    public Iterator<WarehousePart> iterator() {
        return warehouseDB.iterator();
    }
}
