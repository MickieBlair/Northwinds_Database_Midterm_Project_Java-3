/// Mickie Blair
// Java 3 - Midterm Project
// Northwind Database Query Tool
// Order Detail Class 

package java3midtermproject;

import javafx.beans.property.SimpleStringProperty;

public class OrderDetail {
    private final SimpleStringProperty product;
    private final SimpleStringProperty unitPrice;
    private final SimpleStringProperty quantity;
    private final SimpleStringProperty discount;
    
    /**
     * OrderDetail Constructor - no args
     */
    OrderDetail(){
        this.product = new SimpleStringProperty();
        this.unitPrice = new SimpleStringProperty();
        this.quantity = new SimpleStringProperty();
        this.discount = new SimpleStringProperty();
    }
    
    /**
     * OrderDetail Constructor
     * @param p product
     * @param uP unit price
     * @param q quantity
     * @param d discount
     */
    OrderDetail(String p, String uP, String q, String d){
        this.product = new SimpleStringProperty(p);
        this.unitPrice = new SimpleStringProperty(uP);
        this.quantity = new SimpleStringProperty(q);
        this.discount = new SimpleStringProperty(d);
    }
    
    /**
     * Set Product
     * @param p product
     */
    public void setProduct(String p){
        this.product.set(p);
    }
    
    /**
     * Set Unit Price
     * @param uP Unit Price
     */
    public void setUnitPrice(String uP){
        this.unitPrice.set(uP);
    }
    
    /**
     * Set Quantity
     * @param q Quantity
     */
    public void setQuantity(String q){
        this.quantity.set(q);
    }
    
    /**
     * Set Discount
     * @param d discount
     */
    public void setDiscount(String d){
        this.discount.set(d);
    }
    
    /**
     * Get Product
     * @return product
     */
    public String getProduct(){
        return this.product.get();
    }
    
    /**
     * Get Unit Price
     * @return unit Price
     */
    public String getUnitPrice(){
        return this.unitPrice.get();
    }
    
    /**
     * Get Quantity
     * @return quantity
     */
    public String getQuantity(){
        return this.quantity.get();
    }
    
    /**
     * Get Discount
     * @return Discount
     */
    public String getDiscount(){
        return this.discount.get();
    }
    
}
