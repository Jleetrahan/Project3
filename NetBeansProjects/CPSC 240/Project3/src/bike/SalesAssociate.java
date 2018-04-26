package bike;

import basicStuff.BikePart;
import basicStuff.LoginAccount;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Elizabeth Parsons, Jackson Trahan
 */
public class SalesAssociate extends LoginAccount {
    public String firstName;
    String lastName;
    String email;
    Warehouse warehouse;
    
    public SalesAssociate(String fn, String ln, String em, String un, String pw){
        firstName = fn;
        lastName = ln;
        email = em;
        username = un;
        password = pw;
    }
        public static List<WarehousePart>SalesVan = new ArrayList<>();
    public void addWarehouse(String name) {
        warehouse = WarehouseFactory.getWarehouse(this);
    }
    
    public void loadSalesVan(String salesVanName, String transferFileName) throws FileNotFoundException{
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter in a sales Van name:");
        salesVanName = scnr.nextLine();
        System.out.println("Loading Sales Van " + salesVanName);
        File file = new File(salesVanName);
        if (!file.exists()){
            System.out.println("Sales Van does not exist");
        }
        else{
            System.out.println("Enter in transfer file Name");
            transferFileName = scnr.nextLine();
            File transferFile = new File(transferFileName);
            if(!transferFile.exists()){
                System.out.println("Transfer File does not exist.");
            }
            else{
                Scanner sc = new Scanner(file);
                PrintWriter print = new PrintWriter(transferFile);
                while(sc.hasNextLine()){
                    String s = sc.nextLine();
                    print.write(s);
                }
                print.close();
                sc.close();
            }
        }
        
    }
    private WarehousePart findInventory(BikePart bp){
        for(WarehousePart i : SalesVan){
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
            SalesVan.add(new WarehousePart(bp, quantity));
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
        for(WarehousePart i: SalesVan){
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
        for(WarehousePart i : SalesVan){
            if(i.getBp().getName().equals(partName))
                return i;
            }
        return null;
        }
    public List<WarehousePart> getInventory(){
        return SalesVan;
    }
    public void saveWarehouse(String filename){
        try{
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            for(WarehousePart i : SalesVan){
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
    public SalesInvoice generateSalesInvoice(BikePart bp, int quantity) throws ParseException{
        System.out.println("Enter in Sales Associate name.");
        Scanner scnr = new Scanner(System.in);
        String salesAssociate = scnr.nextLine();
        
        SimpleDateFormat newFormat = new SimpleDateFormat("mm/dd/yyyy");
        
        System.out.println("Enter in Date of Sale(mm/dd/yyyy).");
        String userdate = scnr.nextLine();
        Date date = newFormat.parse(userdate);
        
        
        System.out.println("Enter in Customer Name.");
        String customer = scnr.nextLine();
        
        System.out.println("Enter in Employee who received parts.");
        String employee = scnr.nextLine();
        SalesInvoice salesInvoice= new SalesInvoice(date, customer, bp, employee, salesAssociate, bp.getPrice()*quantity);
        return salesInvoice;
    }
}