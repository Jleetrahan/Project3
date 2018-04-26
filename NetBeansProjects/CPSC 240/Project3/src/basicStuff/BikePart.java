package basicStuff;

/**
 *
 * @author Elizabeth Parsons, Jackson Trahan
 */
public class BikePart {
   private String name;
   private int number;
   private double price;
   private double salesPrice;
   private boolean onSale;
   private int quantity;
   /**@param name is part name.
      @param number is part number
      @param price is part price
      @param salesPrice is the price of the part on sale
      @param onSale indicates whether the part is on sale
   */
   
   public BikePart(String name, int number, double price, double salesPrice, boolean onSale, int quantity){
      this.name = name;
      this.number = number;
      this.price = price;
      this.salesPrice = salesPrice;
      this.onSale = onSale;
      this.quantity = quantity;
      }
   
   public String getName()
     {return name;}
   
   public int getNumber(){
       return number;
   }
   
   public double getPrice(){
       return price;
   }
   
   public double getSalesPrice(){
       return salesPrice;
   }
   
   public boolean getOnSale(){
       return onSale;
   }
   
   public int getQuantity(){
       return quantity;
   }
   
   public void setName(String name){
       this.name = name;
   }
   
   public void setNumber(int number){
       this.number = number;
   }
   
   public void setPrice(double price){
       this.price = price;
   }
   
   public void setSalesPrice(double salesPrice){
       this.salesPrice = salesPrice;
   }
   
   public void setOnSale(boolean onSale){
       this.onSale = onSale;
   }
   
   public void setQuantity(int quantity){
       this.quantity = quantity;
   }
   
   public double totalPriceCalc(double price, double salesPrice, boolean onSale, int quantity){
       if(onSale==true){
           double totalPrice = salesPrice * quantity;
           return totalPrice;
       }
       else{
           double totalPrice = price * quantity;
           return totalPrice;
       }
   }
}
