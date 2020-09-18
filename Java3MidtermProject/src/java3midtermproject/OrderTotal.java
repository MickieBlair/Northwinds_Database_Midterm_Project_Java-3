/// Mickie Blair
// Java 3 - Midterm Project
// Northwind Database Query Tool
// Order Total Class 

package java3midtermproject;

import javafx.beans.property.SimpleStringProperty;

public class OrderTotal {
    private final SimpleStringProperty orderNumber;
    private final SimpleStringProperty total;
    
    /**
     * Customer Constructor - no args
     */
    OrderTotal(){
        this.orderNumber = new SimpleStringProperty();
        this.total = new SimpleStringProperty();
    }
    
    /**
     * Order Total Constructor
     * @param o order number
     * @param t total
     */
    OrderTotal(String o, String t){
        this.orderNumber = new SimpleStringProperty(o);
        this.total = new SimpleStringProperty(t);
    }
    
    /**
     * Set Order Number
     * @param o order number
     */
    public void setOrderNumber(String o){
        this.orderNumber.set(o);
    }
    
    /**
     * Set Total
     * @param t total 
     */
    public void setTotal(String t){
        this.total.set(t);
    }
    
    /**
     * Get Order Number
     * @return order number
     */
    public String getOrderNumber(){
        return this.orderNumber.get();
    }
    
    /**
     * Get Total
     * @return total
     */
    public String getTotal(){
        return this.total.get();
    }
    
    
}
