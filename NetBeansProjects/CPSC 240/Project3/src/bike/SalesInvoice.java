/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bike;

/**
 *
 * @author Elizabeth Parsons
 */
import java.util.Date;
import basicStuff.BikePart;

public class SalesInvoice {
    private Date dateOfSale;
    private BikePart bp;
    private String empReceived;
    private String salesAssociate;
    private double totalCost;
    private final String customer;
    
    SalesInvoice(Date dateOfSale, String customer, BikePart bp, String empReceived, String salesAssociate, double totalCost){
        this.bp = bp;
        this.dateOfSale = dateOfSale;
        this.customer = customer;
        this.empReceived = empReceived;
        this.salesAssociate = salesAssociate;
        this.totalCost = totalCost;
        
    }
    
    public Date getDateOfSale(){
        return dateOfSale;
    }
    
    public String getCustomer(){
        return customer;
    }
    
    public BikePart getBikePart(){
        return bp;
    }
    
    public String getEmpReceived(){
        return empReceived;
    }
    
    public String getSalesAssociate(){
        return salesAssociate;
    }
    
    public double getTotalCost(){
        return totalCost;
    }
    
    public String toString(){
        return "Sales Invoice: " + "Sales Associate=" + salesAssociate + ", Date of Sale=" + dateOfSale + ", Bike Part Employee Who Received Parts="
                + empReceived + ", Parts Sold=" + bp + ", Total Cost =" + totalCost + ".";
    }
}
